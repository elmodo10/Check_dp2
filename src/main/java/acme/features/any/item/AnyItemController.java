package acme.features.any.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyItemController extends AbstractController<Any, Item>{
		
		@Autowired
		protected AnyItemtListComponentService listService;
		
		@Autowired
		protected AnyItemShowService showService;
		
		@Autowired
		protected AnyToolListService listToolService;
		
		@Autowired
		protected AnyItemListByToolkitService listByToolkitService;
		
		
		@PostConstruct
		protected void initialise() {
			super.addCommand("list-component", "list", this.listService);
			super.addCommand("list-tool", "list", this.listToolService);
			super.addCommand("show", this.showService);
			super.addCommand("list-by-toolkit", "list", this.listByToolkitService);
		}
		
		

	}


