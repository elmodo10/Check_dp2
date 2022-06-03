package acme.features.patron.patronage;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.patronage.Patronage;
import acme.enums.PublishedStatus;
import acme.enums.Status;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.roles.Patron;
import lib.SpamLib;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage> {

	@Autowired
	protected PatronPatronageRepository repository;
	@Autowired
	protected AdministratorConfigurationRepository configurationRepository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setPublishedStatus(PublishedStatus.NONE_PUBLISHED);
		entity.setInventor(this.repository.findInventorByUsername(request.getModel().getString("inventorUN")));
		request.bind(entity, errors, "code", "legalStuff", "budget", "startsAt", "finishesAt","link");
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("inventors", this.repository.findInventors());
		request.unbind(entity, model, "code", "legalStuff", "budget", "startsAt", "finishesAt","link");
		
	}

	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;

		final Patronage result;
		Date creationTime;
		Date startTime;
		Date finishedTime;
		final Inventor inventor = new Inventor();
		
		final int id = request.getPrincipal().getActiveRoleId();
		creationTime = new Date(System.currentTimeMillis());
		startTime= DateUtils.addMonths( creationTime,1);
		startTime= DateUtils.addMinutes(creationTime, 1);
		finishedTime= DateUtils.addMonths( startTime,1);
		finishedTime= DateUtils.addMinutes(startTime, 1);
		
		final Money money = new Money();
		money.setAmount(0.0);
		money.setCurrency("EUR");
		
		result = new Patronage();
		result.setStatus(Status.proposed);
		result.setCode(this.generateCode());
		result.setLegalStuff("");
		result.setStartsAt(startTime);
		result.setFinishesAt(finishedTime);
		
		result.setBudget(money);
		result.setLink("");
		result.setPublishedStatus(PublishedStatus.NONE_PUBLISHED);
		result.setInventor(inventor);
		result.setPatron(this.repository.findPatronById(id));
		result.setCreationTime(creationTime);

		return result;
	}
	
	private String generateCode() {
		String code = "";
		final List<String> alphabet = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
		
		for(int i=0; i<3; i++) {
			code += alphabet.get(ThreadLocalRandom.current().nextInt(0, alphabet.size()));
		}
		code += "-";
		for(int i=0; i<3; i++) {
			code += Integer.toString(ThreadLocalRandom.current().nextInt(0, 9));
		}
		code += "-";
		code += alphabet.get(ThreadLocalRandom.current().nextInt(0, alphabet.size()));
		
		return code;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		

		final Configuration config = this.configurationRepository.findConfiguration();
		
		final SpamLib spam = new SpamLib(config.getWeakSpamWords(), config.getStrongSpamWords(), config.getWeakSpamThreshold(), config.getStrongSpamThreshold());
		
		errors.state(request, !spam.isSpamStrong(entity.getLegalStuff()), "legalStuff","administrator.announcement.strongspam");
		errors.state(request, !spam.isSpamWeak(entity.getLegalStuff()), "legalStuff","administrator.announcement.weakspam");
		errors.state(request, !spam.isSpamStrong(entity.getLink()), "link","administrator.announcement.strongspam");
		errors.state(request, !spam.isSpamWeak(entity.getLink()), "link","administrator.announcement.weakspam");
		
    	final Patronage patronage = this.repository.findPatronageByCode(entity.getCode());
		
		if(patronage != null) {
			errors.state(request, patronage.getId() == entity.getId(), "code", "inventor.item.title.codeNotUnique");
		}
 
		errors.state(request, entity.getBudget().getAmount() > 0.00, "budget", "authenticated.patron.patronage.list.label.priceGreatherZero");

		final Date minimumStartAt= DateUtils.addMonths(entity.getCreationTime(),1);
		errors.state(request,entity.getStartsAt().after(minimumStartAt), "startsAt", "patron.patronage.error.minimumStartAt");
		
		final Date minimumFinishesAt=DateUtils.addMonths(entity.getStartsAt(), 1);
		errors.state(request,entity.getFinishesAt().after(minimumFinishesAt), "finishesAt", "patron.patronage.error.minimumFinishesAt");
		
		errors.state(request, this.configurationRepository.findConfiguration().getAcceptedCurr().contains(entity.getBudget().getCurrency()), "budget", "administrator.configuration.currency.notExist");
	}

	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
