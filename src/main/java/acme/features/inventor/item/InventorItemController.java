package acme.features.inventor.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorItemController extends AbstractController<Inventor, Item>{
	
	@Autowired
	protected InventorItemListOwnComponentService listByComponentService;
	
	@Autowired
	protected InventorItemShowService showItemService;
	
	@Autowired
	protected InventorItemListOwnToolService listByToolService;
	
	@Autowired
	protected InventorItemCreateService createItemService;
	
	@Autowired
	protected InventorItemDeleteService deleteItemService;
	
	@Autowired
	protected InventorItemUpdateService updateItemService;
	
	@Autowired
	protected InventorItemPublishService publishItemService;
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("list-component", "list", this.listByComponentService);
		super.addCommand("list-tool", "list", this.listByToolService);
		super.addCommand("show", this.showItemService);
		super.addCommand("create", this.createItemService);
		super.addCommand("delete", this.deleteItemService);
		super.addCommand("update", this.updateItemService);
		super.addCommand("publish","update", this.publishItemService);

	}
	
}
