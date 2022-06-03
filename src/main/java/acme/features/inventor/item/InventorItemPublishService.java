package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.item.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorItemPublishService implements AbstractUpdateService<Inventor, Item>  {

	@Autowired
	protected InventorItemRepository repository;
	
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
		
		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model);
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
		
	}

	@Override
	public void update(final Request<Item> request, final Item entity) {
		entity.setStatus(Status.PUBLISHED);
		this.repository.save(entity);
	}
	

}
