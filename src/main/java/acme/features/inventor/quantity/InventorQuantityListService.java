package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorQuantityListService implements AbstractListService<Inventor, Quantity>{

	@Autowired
	protected InventorQuantityRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository configRepository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		final Toolkit t = this.repository.findOneToolkitById(request.getModel().getInteger("id"));
		final Inventor i = this.repository.findInventorByUserId(request.getPrincipal().getAccountId());
		
		
		return t.getInventor().getId()==i.getId();
	}

	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		return this.repository.findQuantityByToolkitId(id);
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Toolkit t = this.repository.findOneToolkitById(request.getModel().getInteger("id"));
		model.setAttribute("tStatus", t.getStatus());
		
		final String defaultCurrency = this.configRepository.getDefaultCurrency();
		final MoneyExchange me = new MoneyExchange(entity.getItem().getRetailPrice(), defaultCurrency);
		model.setAttribute("moneyExchange", me.getExchange());
		
		request.unbind(entity, model, "number", "item.name", "item.code", "item.retailPrice", "item.status");
		
		
		
	}

}
