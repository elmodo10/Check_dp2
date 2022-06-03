package acme.features.inventor.quantity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.item.ItemType;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorQuantityCreateService implements AbstractCreateService<Inventor, Quantity>{

	@Autowired
	protected InventorQuantityRepository repository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Toolkit toolkit = this.repository.findOneToolkitById(id);
		final Inventor i = this.repository.findInventorByUserId(request.getPrincipal().getAccountId());
		
		return toolkit.getInventor().getId() == i.getId();
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setItem(this.repository.findOneItemById(Integer.valueOf(request.getModel().getAttribute("item").toString())));
		request.bind(entity, errors, "number");
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		//final int id = request.getModel().getInteger("id");
		//model.setAttribute("id", id);
		
		final List<Item> items = this.repository.findManyItem();
		model.setAttribute("items", items);
		
		request.unbind(entity, model, "number");
		
	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;
		final Quantity res = new Quantity();
		res.setNumber(0);
		final int id = request.getModel().getInteger("id");
		final Toolkit toolkit = this.repository.findOneToolkitById(id);
		res.setToolkit(toolkit);
		return res;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Item i = entity.getItem();
		errors.state(request, !(i.getType()==ItemType.TOOL && entity.getNumber()>1), "number", "inventor.quantity.number.tool");
		errors.state(request, !(entity.getToolkit().getStatus().toString().equals("PUBLISHED")), "number", "inventor.quantity.toolkit.noPublished");
		
		final Collection<Quantity> items = this.repository.findQuantityByToolkitId(entity.getToolkit().getId());
		final List<Item> aux = new ArrayList<>();
		for(final Quantity q : items) {
			aux.add(q.getItem());
		} 
		errors.state(request, !aux.contains(entity.getItem()), "number", "inventor.quantity.item.exist");
		
	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		

		this.repository.save(entity);
		
	}
}
