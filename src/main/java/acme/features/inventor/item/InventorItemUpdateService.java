package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.item.Item;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import lib.SpamLib;

@Service
public class InventorItemUpdateService implements AbstractUpdateService<Inventor, Item>{

	@Autowired
	protected InventorItemRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository	configurationRepository;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		final int itemId = request.getModel().getInteger("id");
		final int inventorId = request.getPrincipal().getActiveRoleId();
		final Item item = this.repository.findOneItemById(itemId);	
		return item.getInventor().getId() == inventorId && item.getStatus()==acme.entities.item.Status.NON_PUBLISHED;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors,  "name", "code", "technology", "description", "retailPrice", "link", "type");
		
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link", "status", "type");
		
	}

	
	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Item res = this.repository.findItemById(id);
		return res;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		
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
		
		final Item item = this.repository.findItemByCode(entity.getCode());
		
		if(item != null) {
			errors.state(request, item.getId() == entity.getId(), "code", "inventor.item.title.codeNotUnique");
		}
		
		if(entity.getType() == acme.entities.item.ItemType.COMPONENT) {
			errors.state(request, entity.getRetailPrice().getAmount() > 0.00, "retailPrice", "authenticated.patron.patronage.list.label.priceGreatherZero");
		} else {
			errors.state(request, entity.getRetailPrice().getAmount() >= 0.00, "retailPrice", "inventor.item.title.minPrice");
		}
		
		errors.state(request, this.configurationRepository.findConfiguration().getAcceptedCurr().contains(entity.getRetailPrice().getCurrency().toString()), "retailPrice", "administrator.configuration.currency.notExist");
	}
		

	@Override
	public void update(final Request<Item> request, final Item entity) {
		this.repository.save(entity);
		
	}

}
