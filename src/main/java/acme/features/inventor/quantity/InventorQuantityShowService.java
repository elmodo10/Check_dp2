package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorQuantityShowService implements AbstractShowService<Inventor, Quantity>{

	@Autowired
	protected InventorQuantityRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository configRepository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		final Quantity q = this.repository.findOneQuantityById(request.getModel().getInteger("id"));
		final Inventor i = this.repository.findInventorByUserId(request.getPrincipal().getAccountId());
		
		return q.getToolkit().getInventor().getId() == i.getId();
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Quantity res = this.repository.findOneQuantityById(id);
		
		return res;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "number", "item.name", "item.code", "item.technology", "item.description", "item.retailPrice", "item.link", "item.status", "item.type");
		
		final String defaultCurrency = this.configRepository.getDefaultCurrency();
		final MoneyExchange me = new MoneyExchange(entity.getItem().getRetailPrice(), defaultCurrency);
		model.setAttribute("moneyExchange", me.getExchange());
		
		model.setAttribute("item", entity.getItem());
		model.setAttribute("toolkit", entity.getToolkit());
		
		//model.setAttribute("inventorId", this.repository.findUsersInventorId(request.getPrincipal().getAccountId()));
		model.setAttribute("inventorId", request.getPrincipal().getActiveRoleId());
	}

}
