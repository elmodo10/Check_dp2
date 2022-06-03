/*
 * AuthenticatedProviderCreateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.any.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyItemShowService implements AbstractShowService<Any, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyItemRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository configRepository;

	@Override
	public Item findOne(final Request<Item> request) {
		final Integer id = request.getModel().getInteger("id");
		return this.repository.findItemById(id);
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "name", "code", "description" , "link", "technology", "retailPrice", "type");
		
		final String defaultCurrency = this.configRepository.getDefaultCurrency();
		final MoneyExchange me = new MoneyExchange(entity.getRetailPrice(), defaultCurrency);
		model.setAttribute("moneyExchange", me.getExchange());
	}

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		return true;
	}

}
