package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorItemShowService implements AbstractShowService<Inventor, Item>{

	@Autowired
	protected InventorItemRepository itemRepository;
	
	@Autowired
	protected AdministratorConfigurationRepository configRepository;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		final int itemId = request.getModel().getInteger("id");
		final int inventorId = request.getPrincipal().getActiveRoleId();
		final Item item = this.itemRepository.findOneItemById(itemId);	
		return item.getInventor().getId() == inventorId;
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		
		return this.itemRepository.findItemById(id);
		
	
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final String defaultCurrency = this.configRepository.getDefaultCurrency();
		final MoneyExchange me = new MoneyExchange(entity.getRetailPrice(), defaultCurrency);
		model.setAttribute("moneyExchange", me.getExchange());
		
		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link", "status", "type");
		
	}

}
