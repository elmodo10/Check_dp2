package acme.features.inventor.item;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.item.Item;
import acme.entities.item.ItemType;
import acme.entities.item.Status;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import lib.SpamLib;

@Service
public class InventorItemCreateService implements AbstractCreateService<Inventor, Item> {

	@Autowired 
	protected InventorItemRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository	configurationRepository;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
			
		request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "link", "type");

	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
			
		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link", "type", "status");
			
	}

	@Override
	public Item instantiate(final Request<Item> request) {
		assert request != null;
			
		final Item result = new Item();
		final Inventor inventor = this.repository.findInventorByUserAccountId(request.getPrincipal().getAccountId());
		
		final Money money = new Money();
		money.setCurrency("EUR");
		money.setAmount(0.00);
			
		result.setName("");
		result.setCode(this.generateCode());
		result.setTechnology("");
		result.setDescription("");
		result.setRetailPrice(money);
		result.setLink("");
		result.setType(ItemType.COMPONENT);
		result.setStatus(Status.NON_PUBLISHED);
		result.setInventor(inventor);
			
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
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Configuration config = this.configurationRepository.findConfiguration();
		
		final SpamLib spam = new SpamLib(config.getWeakSpamWords(), config.getStrongSpamWords(), config.getWeakSpamThreshold(), config.getStrongSpamThreshold());
		
		errors.state(request, !spam.isSpamStrong(entity.getName()), "name","inventor.item.strongspam");
		errors.state(request, !spam.isSpamWeak(entity.getName()), "name","inventor.item.weakspam");
		errors.state(request, !spam.isSpamStrong(entity.getCode()), "code","inventor.item.strongspam");
		errors.state(request, !spam.isSpamWeak(entity.getCode()), "code","inventor.item.weakspam");
		errors.state(request, !spam.isSpamStrong(entity.getTechnology()), "technology","inventor.item.strongspam");
		errors.state(request, !spam.isSpamWeak(entity.getTechnology()), "technology","inventor.item.weakspam");
		errors.state(request, !spam.isSpamStrong(entity.getDescription()), "description","inventor.item.strongspam");
		errors.state(request, !spam.isSpamWeak(entity.getDescription()), "description","inventor.item.weakspam");
		errors.state(request, !spam.isSpamStrong(entity.getLink()), "link","inventor.item.strongspam");
		errors.state(request, !spam.isSpamWeak(entity.getLink()), "link","inventor.item.weakspam");
		
		errors.state(request, this.repository.findItemByCode(entity.getCode()) == null, "code", "inventor.item.title.codeNotUnique");
		
		
		if(entity.getType() == acme.entities.item.ItemType.COMPONENT) {
			errors.state(request, entity.getRetailPrice().getAmount() > 0.00, "retailPrice", "authenticated.patron.patronage.list.label.priceGreatherZero");
		} else {
			errors.state(request, entity.getRetailPrice().getAmount() >= 0.00, "retailPrice", "inventor.item.title.minPrice");
		}
		
		errors.state(request, this.configurationRepository.findConfiguration().getAcceptedCurr().contains(entity.getRetailPrice().getCurrency().toString()), "retailPrice", "administrator.configuration.currency.notExist");
		
	}

	@Override
	public void create(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;
			 
		entity.setInventor(this.repository.findInventorByUserAccountId(request.getPrincipal().getAccountId()));
		
		entity.setStatus(Status.NON_PUBLISHED);
		this.repository.save(entity);
	}

}
