package acme.features.inventor.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorItemListOwnComponentService implements AbstractListService<Inventor, Item> {

	@Autowired
	protected InventorItemRepository componentRepository;
	
	@Autowired
	protected AdministratorConfigurationRepository configRepository;
	
	@Override
	public List<Item> findMany(final Request<Item> request) {
		assert request != null;
		
		List<Item> res;
		final int id = request.getPrincipal().getActiveRoleId();
		res= this.componentRepository.findComponentsByInventorId(id);
		
		return res;
	}
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		final int id = request.getPrincipal().getAccountId();
		final Inventor inventor = this.componentRepository.findInventorByUserAccountId(id);
		
		return inventor!=null;
	}


	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final String defaultCurrency = this.configRepository.getDefaultCurrency();
		final MoneyExchange me = new MoneyExchange(entity.getRetailPrice(), defaultCurrency);
		model.setAttribute("moneyExchange", me.getExchange());
		
		request.unbind(entity, model, "name","description", "retailPrice", "type");
		
	}
	
	

}
