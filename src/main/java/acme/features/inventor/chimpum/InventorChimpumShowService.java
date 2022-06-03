package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor, Chimpum>{

	@Autowired
	protected InventorChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;

		boolean res;
		int chimpumId;
		Chimpum chimpum;

		chimpumId = request.getModel().getInteger("id");
		chimpum = this.repository.findOneChimpumById(chimpumId);
		res = chimpum != null && chimpum.getArtefact().getInventor().getId() == request.getPrincipal().getActiveRoleId();
		//boolean result3;
		//result3= item.getType().equals(ItemType.TOOL);
		return res;
	}
	
	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;

		Chimpum res;
		int chimpumId;

		chimpumId = request.getModel().getInteger("id");
		res = this.repository.findOneChimpumById(chimpumId);

		return res;
	}
	
	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "creation", "title", "description", "startsAt","finishesAt","budget","link","artefact.name","artefact.code","artefact.technology","artefact.description","artefact.retailPrice","artefact.link","artefact.status","artefact.type");
		
	}
}
