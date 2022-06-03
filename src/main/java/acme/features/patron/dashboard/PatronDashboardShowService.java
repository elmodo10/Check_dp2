package acme.features.patron.dashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.ItemType;
import acme.enums.Status;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronDashboardRepository repository;

	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;
		
		final PatronDashboard result = new PatronDashboard();
		int id = request.getPrincipal().getActiveRoleId();
		result.setPatronagesBudgets(this.getPatronagesBudgets(result.getDataKeys(),id));
		
		result.setTotalNumberPatronage(this.getTotals(id));
		
		return result;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "totalNumberPatronage", "patronagesBudgets");
	}
	
	private Map<Status, Integer> getTotals(final int id) {
		
		
		final Map<Status, Integer> totals = new HashMap<Status, Integer>();
		for(final Status key : Status.values()) {
			
			totals.put(key, this.repository.getPatronageTotalsByStatus(key, id));	
		}
		return totals;
	}
	
	
	
	


	private Map<Status, Map<String, Map<String, Double>>> getPatronagesBudgets(final List<String> dataKeys , int id) {
		final Map<Status, Map<String, Map<String, Double>>> patronageBudgets = new HashMap<Status, Map<String, Map<String, Double>>>();
		
		for(final Status status : Status.values()) {
			final Map<String, Map<String, Double>> it = new HashMap<String, Map<String, Double>>();
			
			final List<String> budgepatron = this.repository.getPatronageBudgetByStatus(status,id);
			for(final String i : budgepatron) {
				final String[] item = i.split(",");
				
				final Map<String, Double> im = new HashMap<String, Double>();
				im.put(dataKeys.get(0), Double.valueOf(item[1]));
				im.put(dataKeys.get(1), Double.valueOf(item[2]));
				im.put(dataKeys.get(2), Double.valueOf(item[3]));
				im.put(dataKeys.get(3), Double.valueOf(item[4]));
				
				it.put(item[0], im);
				
			}
			patronageBudgets.put(status, it);
		}
		
		return patronageBudgets;
	}

	
	
}
