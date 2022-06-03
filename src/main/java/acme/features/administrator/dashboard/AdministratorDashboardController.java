package acme.features.administrator.dashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.forms.Dashboard;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
//@RequestMapping("/administrator/dashboard/")
public class AdministratorDashboardController extends AbstractController<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardShowService	showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
	}

}
