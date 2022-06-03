package acme.features.patron.patronage;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronagereport.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Patron;


@Service
public class PatronPatronageDeleteService implements AbstractDeleteService<Patron, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageRepository repository;

	// AbstractDeleteService<Employer, Duty> interface -------------------------


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
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findPatronageById(id);

		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		

		request.bind(entity, errors, "code", "legalStuff", "budget", "startsAt", "finishesAt","link","publishedStatus");
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model, "code", "legalStuff", "budget", "startsAt", "finishesAt","link","publishedStatus");
		

	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		final List<PatronageReport> patronagereports = this.repository.findPatronageReportBypatronageId(entity.getId());
		for(final PatronageReport p : patronagereports) {
			this.repository.delete(p);
		}
		this.repository.delete(entity);
	}

}

