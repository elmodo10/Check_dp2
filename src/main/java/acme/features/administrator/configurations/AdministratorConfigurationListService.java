package acme.features.administrator.configurations;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorConfigurationListService implements AbstractListService<Administrator, Configuration> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorConfigurationRepository repository;

	@Override
	public Collection<Configuration> findMany(final Request<Configuration> request) {
		assert request != null;
		
		return this.repository.findConfigurations();
	}

	@Override
	public boolean authorise(final Request<Configuration> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "weakSpamWords", "weakSpamThreshold", "strongSpamWords", "strongSpamThreshold", "acceptedCurr", "defaultCurr");
	}

}
