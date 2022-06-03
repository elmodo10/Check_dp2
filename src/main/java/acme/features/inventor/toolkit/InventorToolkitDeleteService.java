package acme.features.inventor.toolkit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorToolkitDeleteService implements AbstractDeleteService<Inventor, Toolkit>{

	@Autowired
	protected InventorToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Toolkit t = this.repository.findToolkitById(id);
		final Inventor i = this.repository.findInventorByUserAccountId(request.getPrincipal().getAccountId());
		
		return t.getInventor().getId()== i.getId();
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "title", "descripcion", "assemblyNotes", "status", "link");
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "descripcion", "assemblyNotes", "status", "link");
		
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Toolkit res = this.repository.findToolkitById(id);
		return res;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
	}

	@Override
	public void delete(final Request<Toolkit> request, final Toolkit entity) {
		final List<Quantity> quantities = entity.getQuantity();
		for(final Quantity q: quantities) {
			this.repository.delete(q);
		}
		this.repository.delete(entity);
		
	}

}
