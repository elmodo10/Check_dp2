package acme.features.inventor.toolkit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkit.Toolkit;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitShowService implements AbstractShowService<Inventor, Toolkit>{
	
	@Autowired
	protected InventorToolkitRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository configRepository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		final int toolkitId = request.getModel().getInteger("id");
		final int inventorId = request.getPrincipal().getActiveRoleId();
		
		final List<Toolkit> toolkits = this.repository.findOwnToolkits(inventorId);
		final Toolkit toolkit = this.repository.findToolkitById(toolkitId);
		
		return toolkits.contains(toolkit);
	}
	
	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		
		final Toolkit res = this.repository.findToolkitById(id);

		return res;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final int id = request.getModel().getInteger("id");
		
		final String defaultCurrency = this.configRepository.getDefaultCurrency();
		model.setAttribute("retailPrice", entity.getRetailPrice(defaultCurrency));
		model.setAttribute("id", id);
		request.unbind(entity, model, "code", "title", "descripcion", "assemblyNotes", "link", "status");
		
	}

}
