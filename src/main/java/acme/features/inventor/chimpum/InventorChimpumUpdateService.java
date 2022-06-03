package acme.features.inventor.chimpum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.configuration.Configuration;
import acme.entities.item.Item;
import acme.entities.item.Status;
import acme.entities.patronage.Patronage;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.features.patron.patronage.PatronPatronageRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import lib.SpamLib;

@Service
public class InventorChimpumUpdateService implements AbstractUpdateService<Inventor, Chimpum> {

	
	
	@Autowired
	protected InventorChimpumRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository configurationRepository;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		
		
		boolean res;
		int chimpumId;
		Chimpum chimpum;

		chimpumId = request.getModel().getInteger("id");
		chimpum = this.repository.findOneChimpumById(chimpumId);
		res = chimpum != null && chimpum.getArtefact().getInventor().getId() == request.getPrincipal().getActiveRoleId();
		boolean result2;
		result2 = (res) && chimpum.getArtefact().getStatus().equals(Status.NON_PUBLISHED);
		//boolean result3;
		//result3= chimpum.getArtefact().getType().equals(ItemType.TOOL);
		
		return result2;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "title", "description", "startsAt", "finishesAt", "budget", "link");
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model,"code","creation", "title", "description", "startsAt", "finishesAt", "budget", "link");
		model.setAttribute("id", request.getModel().getAttribute("id"));
		
		
	}

	
	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		

 
		errors.state(request, entity.getBudget().getAmount() > 0.00, "budget", "authenticated.patron.patronage.list.label.priceGreatherZero");

		final Date minimumStartAt= DateUtils.addMonths(entity.getCreation(),1);
		errors.state(request,entity.getStartsAt().after(minimumStartAt), "startsAt", "patron.patronage.error.minimumStartAt");
		
		final long dateInDays= ((((entity.getFinishesAt().getTime() - entity.getStartsAt().getTime())/1000)/60)/60)/24 ;
		if(dateInDays <7 ) {
		errors.state(request,false, "finishesAt", "patron.patronage.error.menos-semana");
		}
		errors.state(request, this.configurationRepository.findConfiguration().getAcceptedCurr().contains(entity.getBudget().getCurrency()), "budget", "administrator.configuration.currency.notExist");
		
	

		
	}

	

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;

		Chimpum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneChimpumById(id);

		return result;
	}

	@Override
	public void update(final Request<Chimpum> request, final Chimpum entity) {
		// TODO Auto-generated method stub
		this.repository.save(entity);
	}

}
