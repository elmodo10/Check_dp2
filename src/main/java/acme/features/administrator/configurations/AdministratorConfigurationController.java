package acme.features.administrator.configurations;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.configuration.Configuration;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
//@RequestMapping("/administrator/configuration/")
public class AdministratorConfigurationController extends AbstractController<Administrator, Configuration> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorConfigurationListService	listService;

	@Autowired
	protected AdministratorConfigurationShowService	showService;

	@Autowired
	protected AdministratorConfigurationUpdateService updateService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("update", this.updateService);
	}

}
