package acme.features.administrator.configurations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorConfigurationShowService implements AbstractShowService<Administrator, Configuration> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorConfigurationRepository repository;

	@Override
	public boolean authorise(final Request<Configuration> request) {
		assert request != null;

		return true;
	}

	@Override
	public Configuration findOne(final Request<Configuration> request) {
		assert request != null;
		
		final Optional<Configuration> config = this.repository.findConfigurations().stream().findFirst();
		
		return config.isPresent() ? config.get() : null;
	}

	@Override
	public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "weakSpamWords", "weakSpamThreshold", "strongSpamWords", "strongSpamThreshold", "acceptedCurr", "defaultCurr");
	}
	
}
