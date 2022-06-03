package acme.features.inventor.toolkit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorToolkitListOwnService implements AbstractListService<Inventor, Toolkit>{

	@Autowired
	protected InventorToolkitRepository repository;
	
	@Override
	public List<Toolkit> findMany(final Request<Toolkit> request){
		assert request != null;

		final int id = request.getPrincipal().getActiveRoleId();
		return this.repository.findOwnToolkits(id);
	}
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "descripcion", "link");
	}
}
