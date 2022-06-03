package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkit.Status;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitPublishService implements AbstractUpdateService<Inventor, Toolkit>{

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
		
		request.bind(entity, errors, "code", "title", "descripcion", "assemblyNotes", "link");
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "descripcion", "assemblyNotes", "link", "status");
		
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
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		entity.setStatus(Status.PUBLISHED);
		this.repository.save(entity);
		
	}

}
