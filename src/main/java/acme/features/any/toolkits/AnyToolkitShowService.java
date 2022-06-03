package acme.features.any.toolkits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkit.Toolkit;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any, Toolkit> {
	
	@Autowired
	protected AnyToolkitRepository repository;

	@Autowired
	protected AdministratorConfigurationRepository configRepository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
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
		
		request.unbind(entity, model,"code", "title", "descripcion","assemblyNotes", "link");
		model.setAttribute("retailPrice", entity.getRetailPrice(this.configRepository.getDefaultCurrency()));
		
	}
}
