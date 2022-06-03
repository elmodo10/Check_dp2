package acme.features.inventor.chimpum;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Status;
import acme.entities.patronage.Patronage;
import acme.entities.patronagereport.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;
import acme.roles.Patron;


@Service
public class InventorChimpumDeleteService implements AbstractDeleteService<Inventor, Chimpum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorChimpumRepository repository;

	// AbstractDeleteService<Employer, Duty> interface -------------------------


	@Override
	public boolean authorise(final Request<Chimpum> request) {
		boolean res;
		int chimpumId;
		Chimpum chimpum;

		chimpumId = request.getModel().getInteger("id");
		chimpum = this.repository.findOneChimpumById(chimpumId);
		res = chimpum != null && chimpum.getArtefact().getInventor().getId() == request.getPrincipal().getActiveRoleId();
		boolean result2;
		result2 = (res) && chimpum.getArtefact().getStatus().equals(Status.NON_PUBLISHED);
		//boolean result3;
		//result3= chimpum.getArtefact().getType().equals(ItemType.TOOL);
		return result2;
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;

		Chimpum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneChimpumById(id);

		return result;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		

		request.bind(entity, errors, "code","creation", "title", "description", "startsAt", "finishesAt", "budget", "link");
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model, "code","creation", "title", "description", "startsAt", "finishesAt", "budget", "link");
		

	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);
	}

}

