package acme.features.inventor.chimpum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.chimpum.Chimpum;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorChimpumController extends AbstractController<Inventor, Chimpum>{
	
	@Autowired
	protected InventorChimpumListByItemsService listByItemService;
	
	@Autowired
	protected InventorChimpumShowService showService;
	
	@Autowired
	protected InventorChimpumCreateService createService;
	@Autowired
	protected InventorChimpumDeleteService deleteService;
	@Autowired
	protected InventorChimpumUpdateService updateService;
	
	

	
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list-by-item","list", this.listByItemService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("update", this.updateService);

	}

}
