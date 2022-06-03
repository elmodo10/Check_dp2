package acme.features.inventor.patronageReports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronagereport.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportShowService implements AbstractShowService<Inventor, PatronageReport>{
	
		@Autowired
		protected InventorPatronageReportRepository repository;
			
		
		@Override
		public boolean authorise(final Request<PatronageReport> request) {
			assert request != null;

			boolean res;
			int patronageReportId;
			PatronageReport patronageReport;

			patronageReportId = request.getModel().getInteger("id");
			patronageReport = this.repository.findOnePatronageReportById(patronageReportId);
			res = patronageReport != null && patronageReport.getPatronage().getInventor().getId() == request.getPrincipal().getActiveRoleId();

			return res;
		}
		
		@Override
		public PatronageReport findOne(final Request<PatronageReport> request) {
			assert request != null;

			PatronageReport res;
			int patronageReportId;

			patronageReportId = request.getModel().getInteger("id");
			res = this.repository.findOnePatronageReportById(patronageReportId);

			return res;
		}
		
		@Override
		public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "seqNumber", "createdAt", "memorandum", "link", "patronage.status", "patronage.code", "patronage.legalStuff", "patronage.budget", "patronage.startsAt", "patronage.finishesAt", "patronage.link");
			
		}

		

}