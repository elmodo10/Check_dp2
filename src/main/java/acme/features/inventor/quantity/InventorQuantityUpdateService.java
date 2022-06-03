package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.item.Item;
import acme.entities.item.ItemType;
import acme.entities.quantity.Quantity;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorQuantityUpdateService implements AbstractUpdateService<Inventor, Quantity>{

	@Autowired
	protected InventorQuantityRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository confRepository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		final Inventor i = this.repository.findInventorByUserId(request.getPrincipal().getAccountId());
		final Quantity q = this.repository.findOneQuantityById(request.getModel().getInteger("id"));
		
		return q.getToolkit().getInventor().getId()==i.getId();
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "number", "item.name", "item.code", "item.technology", "item.description", "item.retailPrice", "item.link", "item.type");
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "number", "item.name", "item.code", "item.technology", "item.description", "item.retailPrice", "item.link", "item.status", "item.type");
		
		model.setAttribute("item", entity.getItem());
		model.setAttribute("toolkit", entity.getToolkit());
		
		//model.setAttribute("inventorId", this.repository.findUsersInventorId(request.getPrincipal().getAccountId()));
		model.setAttribute("inventorId", request.getPrincipal().getActiveRoleId());
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		
		return this.repository.findOneQuantityById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Item i = entity.getItem();
		errors.state(request, !(i.getType()==ItemType.TOOL && entity.getNumber()>1), "number", "inventor.quantity.number.tool");
		errors.state(request, !(entity.getToolkit().getStatus().toString().equals("PUBLISHED")), "number", "inventor.quantity.toolkit.noPublished");
		//errors.state(request, !(entity.getItem().getStatus()==Status.PUBLISHED), "item.status", "inventor.quantity.item.status");
		
		final Collection<Configuration> conf = this.confRepository.findConfigurations();
		for(final Configuration c : conf) {
			errors.state(request, !c.isSpamStrong(i.getCode()), "item.code", "inventor.quantity.item.code.strongSpam");
			errors.state(request, !c.isSpamStrong(i.getDescription()), "item.description", "inventor.quantity.item.description.strongSpam");
			errors.state(request, !c.isSpamStrong(i.getLink()), "item.link", "inventor.quantity.item.link.strongSpam");
			errors.state(request, !c.isSpamStrong(i.getName()), "item.name", "inventor.quantity.item.name.strongSpam");
			errors.state(request, !c.isSpamStrong(i.getTechnology()), "item.technology", "inventor.quantity.item.technology.strongSpam");
			
			errors.state(request, !c.isSpamWeak(i.getCode()), "item.code", "inventor.quantity.item.code.weakSpam");
			errors.state(request, !c.isSpamWeak(i.getDescription()), "item.description", "inventor.quantity.item.description.weakSpam");
			errors.state(request, !c.isSpamWeak(i.getLink()), "item.link", "inventor.quantity.item.link.weakSpam");
			errors.state(request, !c.isSpamWeak(i.getName()), "item.name", "inventor.quantity.item.name.weakSpam");
			errors.state(request, !c.isSpamWeak(i.getTechnology()), "item.technology", "inventor.quantity.item.technology.weakSpam");
			
		}
		
		final Item item = this.repository.findItemByCode(entity.getItem().getCode());
		
		if(item != null) {
			errors.state(request, item.getId() == entity.getItem().getId(), "item.code", "inventor.item.title.codeNotUnique");
		}
		
		errors.state(request, entity.getItem().getRetailPrice().getAmount() >= 0.00, "item.retailPrice", "inventor.item.title.minPrice");
		
	}

	@Override
	public void update(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		
		final Item i = entity.getItem();
		
		if(entity.getItem().getType().equals(acme.entities.item.ItemType.TOOL) && entity.getNumber() > 1) {
			entity.setNumber(1);
		}
		
		this.repository.save(i);
		this.repository.save(entity);
		
		
		
		
	}

}
