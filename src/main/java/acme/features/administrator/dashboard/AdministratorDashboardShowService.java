package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.item.ItemType;
import acme.enums.Status;
import acme.forms.Dashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;
		
		final Dashboard result = new Dashboard();

		result.setTotalsData(this.getTotals(result.getTotalsDataKeys()));
		result.setPatronagesBudgets(this.getPatronagesBudgets(result.getDataKeys()));
		
		result.setItemsRetailPrice(this.getItemsData(result.getDataKeys()));
		result.setComponentsRetailPrice(this.getComponentsData(result.getDataKeys()));
		
		//System.out.println(this.getItemsData(result.getDataKeys()).toString());
		
		//result.setComponentsData(this.getComponentsData(result.getDataKeys()));
		
		result.setChimpumBudgets(this.getChimpumBudgets(result.getDataKeys()));
		
		result.setRatio(Double.valueOf(this.repository.getAllChimpum())/Double.valueOf(this.repository.getAllArtefacts()));
		
		return result;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "totalsData", "patronagesBudgets", "itemsRetailPrice", "componentsRetailPrice","chimpumBudgets","ratio");
	}
	
	private Map<String, Integer> getTotals(final List<String> totalsKeys) {
		final Map<String, Integer> totals = new HashMap<String, Integer>();
		for(final String key : totalsKeys) {
			switch(key) {
				case "Component":
				case "Tool":
					totals.put(key, this.repository.getItemTotalsByType(ItemType.valueOf(key.toUpperCase())));
					break;
				case "Proposed":
				case "Accepted":
				case "Denied":
					totals.put(key, this.repository.getPatronageTotalsByStatus(Status.valueOf(key.toLowerCase())));
					break;
				default:
					break;
			}
		}
		return totals;
	}
	
	private Map<Status, Map<String, Double>> getPatronagesBudgets(final List<String> budgetKeys) {
		final Map<Status, Map<String, Double>> patronageBudgets = new HashMap<Status, Map<String, Double>>();
		for(final Status status : Status.values()) {
			// TODO it's been tried to have a List<Double> but it only returns 1 index instead of 4 indexes-list. Maybe there is a better way
			final String budgetData = this.repository.getPatronageBudgetByStatus(status);
			final String[] budget = budgetData.split(",");
			
			final List<Double> budgetDbl = new ArrayList<Double>();
			for(final String b : budget) {
				budgetDbl.add(Double.valueOf(b));
			}
			
			final Map<String, Double> bd = new HashMap<String, Double>();
			for(int i=0; i<budgetDbl.size(); i++) {
				bd.put(budgetKeys.get(i), budgetDbl.get(i));
			}
			
			patronageBudgets.put(status, bd);
		}
		return patronageBudgets;
	}
	
	private Map<ItemType, Map<String, Map<String, Double>>> getItemsData(final List<String> dataKeys) {
		final Map<ItemType, Map<String, Map<String, Double>>> componentsData = new HashMap<ItemType, Map<String, Map<String, Double>>>();
		
		for(final ItemType type : ItemType.values()) {
			final Map<String, Map<String, Double>> it = new HashMap<String, Map<String, Double>>();
			
			final List<String> itemsData = this.repository.getItemsByType(type);
			for(final String i : itemsData) {
				final String[] item = i.split(",");
				
				final Map<String, Double> im = new HashMap<String, Double>();
				im.put(dataKeys.get(0), Double.valueOf(item[1]));
				im.put(dataKeys.get(1), Double.valueOf(item[2]));
				im.put(dataKeys.get(2), Double.valueOf(item[3]));
				im.put(dataKeys.get(3), Double.valueOf(item[4]));
				
				it.put(item[0], im);
				
			}
			componentsData.put(type, it);
		}
		
		return componentsData;
	}
	
	private Map<Pair<String, String>, Map<String, Double>> getComponentsData(final List<String> dataKeys) {
		final Map<Pair<String, String>, Map<String, Double>> componentsData = new HashMap<Pair<String, String>, Map<String, Double>>();
		
		final List<String> itemsData = this.repository.getComponentsInCurrencies();
		for(final String i : itemsData) {
			final String[] item = i.split(",");
			
			final Map<String, Double> im = new HashMap<String, Double>();
			im.put(dataKeys.get(0), Double.valueOf(item[2]));
			im.put(dataKeys.get(1), Double.valueOf(item[3]));
			im.put(dataKeys.get(2), Double.valueOf(item[4]));
			im.put(dataKeys.get(3), Double.valueOf(item[5]));
			
			componentsData.put(Pair.of(item[0], item[1]), im);
			
		}
		
		return componentsData;
	}
	
	private Map<String, Map<String, Double>> getChimpumBudgets(final List<String> dataKeys ) {
		final Map<String, Map<String, Double>> chimpumBudgets = new HashMap<String, Map<String, Double>>();
		
		
			
			final List<String> budgepatron = this.repository.getChimpumBudget();
			for(final String i : budgepatron) {
				final String[] item = i.split(",");
				
				final Map<String, Double> im = new HashMap<String, Double>();
				im.put(dataKeys.get(0), Double.valueOf(item[1]));
				im.put(dataKeys.get(1), Double.valueOf(item[2]));
				im.put(dataKeys.get(2), Double.valueOf(item[3]));
				im.put(dataKeys.get(3), Double.valueOf(item[4]));
				
				chimpumBudgets.put(item[0], im);
				
			}
			
		
		
		return chimpumBudgets;
	}
}
