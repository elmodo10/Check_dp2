package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.features.inventor.chimpum.InventorChimpumRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorItemDeleteService implements AbstractDeleteService<Inventor, Item> {

	@Autowired
	protected InventorItemRepository repository;
	@Autowired
	protected InventorChimpumRepository chimpumRepository;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		final int itemId = request.getModel().getInteger("id");
		final int inventorId = request.getPrincipal().getActiveRoleId();
		final Item item = this.repository.findOneItemById(itemId);	
		return item.getInventor().getId() == inventorId;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "link", "status", "type");
		
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
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;
		final int id = request.getModel().getInteger("id");
		
		Chimpum chim = this.repository.findchimpumbyartifactId(id);
		if(chim != null) {
			this.chimpumRepository.delete(chim);
			
		}
		this.repository.delete(entity);
	}
	
	
}