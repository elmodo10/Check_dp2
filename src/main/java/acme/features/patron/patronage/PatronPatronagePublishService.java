package acme.features.patron.patronage;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.enums.PublishedStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class PatronPatronagePublishService implements AbstractUpdateService<Patron, Patronage> {

	@Autowired
	protected PatronPatronageRepository repository;
	
	
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
		
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
	}

	
	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		
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
		entity.setPublishedStatus(PublishedStatus.PUBLISHED);
		this.repository.save(entity);
	}

	
	

}
