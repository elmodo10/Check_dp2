package acme.features.patron.patronage;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.patronage.Patronage;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;
import lib.SpamLib;

@Service
public class PatronPatronageUpdateService implements AbstractUpdateService<Patron, Patronage> {
	
	@Autowired
	protected PatronPatronageRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository configurationRepository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		final int id = request.getPrincipal().getActiveRoleId();
		final Collection<Patronage> patronages = this.repository.findAllPatronagesByPatronId(id);
		final int patronage_id = request.getModel().getInteger("id");
		final Patronage patronage = this.repository.findPatronageById(patronage_id);
		return patronages.contains(patronage);
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		entity.setInventor(this.repository.findInventorByUsername(request.getModel().getString("inventorUN")));
		request.bind(entity, errors, "code", "legalStuff", "budget", "startsAt", "finishesAt","link");
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		model.setAttribute("inventors", this.repository.findInventors());
		
		
		request.unbind(entity, model, "status","code", "legalStuff", "budget", "startsAt", "finishesAt","link");
		
	}

	
	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Patronage patronage = this.repository.findPatronageByCode(entity.getCode());
		
		final Configuration config = this.configurationRepository.findConfiguration();
		
		final SpamLib spam = new SpamLib(config.getWeakSpamWords(), config.getStrongSpamWords(), config.getWeakSpamThreshold(), config.getStrongSpamThreshold());
		
		errors.state(request, !spam.isSpamStrong(entity.getLegalStuff()), "legalStuff","administrator.announcement.strongspam");
		errors.state(request, !spam.isSpamWeak(entity.getLegalStuff()), "legalStuff","administrator.announcement.weakspam");
		errors.state(request, !spam.isSpamStrong(entity.getLink()), "link","administrator.announcement.strongspam");
		errors.state(request, !spam.isSpamWeak(entity.getLink()), "link","administrator.announcement.weakspam");
		
		if(patronage != null) {
			errors.state(request, patronage.getId() == entity.getId(), "code", "inventor.item.title.codeNotUnique");
		}
		
		errors.state(request, entity.getBudget().getAmount() > 0.00, "budget", "authenticated.patron.patronage.list.label.priceGreatherZero");
		
		errors.state(request, this.configurationRepository.findConfiguration().getAcceptedCurr().contains(entity.getBudget().getCurrency()), "budget", "administrator.configuration.currency.notExist");

		final Date minimumStartAt= DateUtils.addMonths(entity.getCreationTime(),1);
		errors.state(request,entity.getStartsAt().after(minimumStartAt), "startsAt", "patron.patronage.error.minimumStartAt");
		
		final Date minimumFinishesAt=DateUtils.addMonths(entity.getStartsAt(), 1);
		errors.state(request,entity.getFinishesAt().after(minimumFinishesAt), "finishesAt", "patron.patronage.error.minimumFinishesAt");
	}

	

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findPatronageById(id);

		return result;
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		// TODO Auto-generated method stub
		this.repository.save(entity);
	}

}
