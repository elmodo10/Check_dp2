package acme.features.any.chirp;


import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chirp.Chirp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp>{
	
	@Autowired
	protected AnyChirpRepository repository;
	
	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;
		return true;
	}
	
	@Override
	public Chirp instantiate(final Request<Chirp> request) {
		assert request != null;
		Chirp res;
		res = new Chirp();
		return res;
	}
	
	@Override
	public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Date creationTime;
		Calendar calendar;
		creationTime = new Date();
		calendar = Calendar.getInstance();
		creationTime = calendar.getTime();
		request.bind(entity, errors, "author", "body", "email", "title");
		entity.setCreationTime(creationTime);
		
	}
	
	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final boolean confirm = request.getModel().getBoolean("confirm");
		errors.state(request, confirm, "confirm", "any.chirp.accept.error");

	}
	
	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "body", "email", "title");
		model.setAttribute("confirm", false);
	}

	@Override
	public void create(final Request<Chirp> request, final Chirp entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}


}
