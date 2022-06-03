package acme.features.inventor.patronage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorPatronageListOwnService implements AbstractListService<Inventor, Patronage>{
	
	@Autowired
	protected InventorPatronageRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository configRepository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public List<Patronage> findMany(final Request<Patronage> request) {
		assert request != null;

		List<Patronage> result;
		final int id;

		id = request.getPrincipal().getActiveRoleId();
		result = this.repository.findPatronagesByInventorId(id);

		return result;
	}
	
	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final String defaultCurrency = this.configRepository.getDefaultCurrency();
		final MoneyExchange me = new MoneyExchange(entity.getBudget(), defaultCurrency);
		model.setAttribute("moneyExchange", me.getExchange());
		
		request.unbind(entity, model, "status", "code", "budget");
	}

}
