package acme.features.inventor.patronage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageShowService implements AbstractShowService<Inventor, Patronage>{

	@Autowired
	protected InventorPatronageRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository configRepository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		final int inventorId = request.getPrincipal().getActiveRoleId();
		final int patronageId = request.getModel().getInteger("id");
		
		final List<Patronage> patronages = this.repository.findPatronagesByInventorId(inventorId);
		final Patronage requested = this.repository.findById(patronageId);

		return patronages.contains(requested);
	}
	
	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		
		final Patronage res = this.repository.findById(id);

		return res;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final String defaultCurrency = this.configRepository.getDefaultCurrency();
		final MoneyExchange me = new MoneyExchange(entity.getBudget(), defaultCurrency);
		model.setAttribute("moneyExchange", me.getExchange());
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "startsAt", "finishesAt", "link", "patron.company", "patron.statement");
		
	}
	
}
