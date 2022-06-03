package acme.features.authenticated.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedConfigurationShowService implements AbstractShowService<Authenticated, Configuration>{
	
	@Autowired
	protected AuthenticatedConfigurationRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository configRepository;
	
	
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
		
		request.unbind(entity, model,"defaultCurr", "acceptedCurr");
		
		final Money money = new Money();
		money.setAmount(1.00);
		money.setCurrency("EUR");
		
		final String defaultCurrency = this.configRepository.getDefaultCurrency();
		final MoneyExchange me = new MoneyExchange(money, defaultCurrency);
		model.setAttribute("moneyExchange", me.getExchange());
	}
	
	@Override
	public Configuration findOne(final Request<Configuration> request) {
		assert request != null;

		Configuration result;

		result = this.repository.findMany().get(0);

		return result;
	}

}
