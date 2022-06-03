package acme.features.inventor.patronageReports;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronagereport.PatronageReport;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorPatronageReportController extends AbstractController<Inventor, PatronageReport> {
	
		@Autowired
		protected InventorPatronageReportListByPatronageService listByPatronageService;
		
		@Autowired
		protected InventorPatronageReportShowService showService;
		
		@Autowired
		protected InventorPatronageReportCreateService createService;
		
		
		@PostConstruct
		protected void initialise() {
			super.addCommand("list-by-patronage","list", this.listByPatronageService);
			super.addCommand("show", this.showService);
			super.addCommand("create", this.createService);

		}

}