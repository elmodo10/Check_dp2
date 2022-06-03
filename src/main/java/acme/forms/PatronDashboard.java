
package acme.forms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import acme.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	// Attributes -------------------------------------------------------------
	/*
	Double	totalNumberPatronagesPropose;
	Double	totalNumberPatronagesAccepted;
	Double	totalNumberPatronagesDenied;
	
	Double	averageBudgetPatronagesProposedCurr1;
	Double	averageBudgetPatronagesAcceptedCurr1;
	Double	averageBudgetPatronagesDeniedCurr1;
	
	Double	averageBudgetPatronagesProposedCurr2;
	Double	averageBudgetPatronagesAcceptedCurr2;
	Double	averageBudgetPatronagesDeniedCurr2;
	
	Double	averageBudgetPatronagesProposedCurr3;
	Double	averageBudgetPatronagesAcceptedCurr3;
	Double	averageBudgetPatronagesDeniedCurr3;
	
	Double	deviationBudgetPatronagesProposedCurr1;
	Double	deviationBudgetPatronagesAcceptedCurr1;
	Double	deviationBudgetPatronagesDeniedCurr1;
	
	Double	deviationBudgetPatronagesProposedCurr2;
	Double	deviationBudgetPatronagesAcceptedCurr2;
	Double	deviationBudgetPatronagesDeniedCurr2;
	
	Double	deviationBudgetPatronagesProposedCurr3;
	Double	deviationBudgetPatronagesAcceptedCurr3;
	Double	deviationBudgetPatronagesDeniedCurr3;
	
	Double	minimunBudgetPatronagesProposedCurr1;
	Double	minimunBudgetPatronagesAcceptedCurr1;
	Double	minimunBudgetPatronagesDeniedCurr1;
	
	Double	minimunBudgetPatronagesProposedCurr2;
	Double	minimunBudgetPatronagesAcceptedCurr2;
	Double	minimunBudgetPatronagesDeniedCurr2;
	
	Double	minimunBudgetPatronagesProposedCurr3;
	Double	minimunBudgetPatronagesAcceptedCurr3;
	Double	minimunBudgetPatronagesDeniedCurr3;
	
	Double	maximunBudgetPatronagesProposedCurr1;
	Double	maximunBudgetPatronagesAcceptedCurr1;
	Double	maximunBudgetPatronagesDeniedCurr1;
	
	Double	maximunBudgetPatronagesProposedCurr2;
	Double	maximunBudgetPatronagesAcceptedCurr2;
	Double	maximunBudgetPatronagesDeniedCurr2;
	
	Double	maximunBudgetPatronagesProposedCurr3;
	Double	maximunBudgetPatronagesAcceptedCurr3;
	Double	maximunBudgetPatronagesDeniedCurr3;
*/
	
	public List<String> getTotalscurre() {
		return new ArrayList<String>(Arrays.asList("EUR", "USD", "GBP"));
	}
	
	public List<String> getDataKeys() {
		return new ArrayList<String>(Arrays.asList("Min", "Max", "Avg", "Dev"));
	}
	
	
	Map<Status,  Integer> totalNumberPatronage;
	
	Map<Status, Map<String, Map<String, Double>>> PatronagesBudgets;
	

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
