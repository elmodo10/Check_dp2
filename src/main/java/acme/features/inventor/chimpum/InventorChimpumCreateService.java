package acme.features.inventor.chimpum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.configuration.Configuration;
import acme.entities.item.Item;
import acme.entities.item.ItemType;
import acme.entities.item.Status;
import acme.entities.patronage.Patronage;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import lib.SpamLib;

@Service
public class InventorChimpumCreateService implements AbstractCreateService<Inventor, Chimpum> {

	
	@Autowired
	protected InventorChimpumRepository repository;
	
	
	@Autowired
	protected AdministratorConfigurationRepository configurationRepository;


	private Calendar calendar2;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;

		boolean result;
		
		int itemId;
		Item item;

		itemId = request.getModel().getInteger("id");
		item = this.repository.findItemById(itemId);
		result = request.getPrincipal().getActiveRoleId() == item.getInventor().getId() ;
		boolean result2;
		result2 = (result) && item.getStatus().equals(Status.NON_PUBLISHED);
		//boolean result3;
		//result3= item.getType().equals(ItemType.TOOL);
		return result2; 
	}
	
	@Override
	public Chimpum instantiate(final Request<Chimpum> request) {
		assert request != null;

		final Chimpum result;
		result = new Chimpum();
		Date creationTime;
		Date startTime;
		Date finishedTime;
		
		creationTime = new Date(System.currentTimeMillis());
		startTime= DateUtils.addMonths( creationTime,1);
		startTime= DateUtils.addMinutes(creationTime, 1);
		finishedTime= DateUtils.addWeeks( startTime,1);
		finishedTime= DateUtils.addMinutes(startTime, 1);
		
		int itemId = request.getModel().getInteger("id");
		
		Item item = this.repository.findItemById(itemId);
		
		
		final Money money = new Money();
		money.setAmount(0.0);
		money.setCurrency("EUR");
		
	
		
		
		result.setCode(this.generateCode());
		result.setArtefact(item);
		
		result.setCreation(creationTime);
		result.setStartsAt(startTime);
		result.setFinishesAt(finishedTime);
		
		result.setTitle("");
		result.setDescription("");
		result.setBudget(money);
		result.setLink("");
	
		
		
		
		
		

		return result;
	}
	
	private String generateCode() {
		String code = "";
		final List<String> alphabet = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
		
		for(int i=0; i<3; i++) {
			code += alphabet.get(ThreadLocalRandom.current().nextInt(0, alphabet.size()));
		}
		code += "-";
		for(int i=0; i<3; i++) {
			code += Integer.toString(ThreadLocalRandom.current().nextInt(0, 9));
		}
		code += "-";
		code += alphabet.get(ThreadLocalRandom.current().nextInt(0, alphabet.size()));
		
		return code;
	}
	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "startsAt", "finishesAt", "budget", "link");
		
	}
	
	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		final List<Item> items = this.repository.findItemsWithChimpum();
        for(final Item i:items) {
            if(i.getId()==entity.getArtefact().getId()) {
                errors.state(request, false, "title", "inventor.messages.form.error.duplicate");
            }
        }

		
		
    	final Chimpum chimpum = this.repository.findChimpumByCode(entity.getCode());
		
		if(chimpum != null) {
			errors.state(request, chimpum.getId() == entity.getId(), "code", "inventor.item.title.codeNotUnique");
		}
 
		errors.state(request, entity.getBudget().getAmount() > 0.00, "budget", "authenticated.patron.patronage.list.label.priceGreatherZero");

		final Date minimumStartAt= DateUtils.addMonths(entity.getCreation(),1);
		errors.state(request,entity.getStartsAt().after(minimumStartAt), "startsAt", "patron.patronage.error.minimumStartAt");
		
		final long dateInDays= ((((entity.getFinishesAt().getTime() - entity.getStartsAt().getTime())/1000)/60)/60)/24 ;
		if(dateInDays <7 ) {
		errors.state(request,false, "finishesAt", "patron.patronage.error.menos-semana");
		}
		errors.state(request, this.configurationRepository.findConfiguration().getAcceptedCurr().contains(entity.getBudget().getCurrency()), "budget", "administrator.configuration.currency.notExist");
		
		
		//if(entity.getCreation()!=null) {
            final Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(entity.getCreation());
            final String day= String.format("%02d" , calendar2.get(Calendar.DAY_OF_MONTH));
            final String month= String.format("%02d" , calendar2.get(Calendar.MONTH));
            final String year = String.valueOf(calendar2.get(Calendar.YEAR)).substring(2);


       // }
        
   
	}
	
	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"code", "title", "description", "startsAt", "finishesAt", "budget", "link");
		model.setAttribute("id", request.getModel().getAttribute("id"));
		
	}
	
	@Override
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		

		this.repository.save(entity);
		
	}
}
