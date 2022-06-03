package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageUpdateService implements AbstractUpdateService<Inventor, Patronage>{

	@Autowired
	protected InventorPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		final Inventor i = this.repository.findInventorByUserAccountId(request.getPrincipal().getAccountId());
		
		final Patronage p = this.repository.findPatronageById(request.getModel().getInteger("id"));
		return p.getInventor().getId()==i.getId();
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "status");
		
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status");
		
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		return this.repository.findPatronageById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	
		
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		
		this.repository.save(entity);
	}
	
	

}
