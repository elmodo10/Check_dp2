/*
 * FavouriteLinkTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.patron.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;


public class PatronDashboardTest extends TestHarness {
	
	private final String patronPath = "/patron/patron-dashboard/show";
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@Test
	@Order(0)
	public void anonymousAccess() {		
		this.checkXpathNotExists("//*[@id='mainMenu']/ul[1]/li[2]/div/a[3][normalize-space() = 'Administrator']");
		
		this.navigate(this.patronPath);
		this.checkPanicExists();
	}
	
	@Test
	@Order(1)
	public void checkDashboard() {
		this.signIn("administrator", "administrator");
		
		// check that an patron user has patron dashboard link on menu
		this.checkXpathContains("//*[@id=\"mainMenu\"]/ul[1]/li[6]/div/a[2]", "Show Dashboard");
		
		// check that an patron user can access to patron dashboard
		this.navigate(this.patronPath);
		this.checkNotPanicExists();
		
		
	}
	
	@Test
	@Order(2)
	public void checkDashboardInputs() {
		
		this.signIn("administrator", "administrator");
		this.navigate(this.patronPath);
		
		final Map<String, String> inputsMap = new HashMap<String, String>();
		
		// Total proposed, accepted & denied patronages
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[2]/div[1]/div/div/label/input", "6");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[2]/div[2]/div/div/label/input", "6");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[2]/div[3]/div/div/label/input", "6");
		
		//patronages
		// Proposed in EUR
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[3]/div[1]/div/div/label/input", "325.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[3]/div[2]/div/div/label/input", "250.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[3]/div[3]/div/div/label/input", "287.5");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[3]/div[4]/div/div/label/input", "27.95084971874737");
		
		// Proposed in GBP
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[4]/div[1]/div/div/label/input", "326.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[4]/div[2]/div/div/label/input", "326.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[4]/div[3]/div/div/label/input", "326.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[4]/div[4]/div/div/label/input", "0.0");
				
		// Proposed in USD
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[5]/div[1]/div/div/label/input", "326.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[5]/div[2]/div/div/label/input", "326.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[5]/div[3]/div/div/label/input", "326.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[5]/div[4]/div/div/label/input", "0.0");
				
		
		// Acepted in EUR
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[6]/div[1]/div/div/label/input", "125.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[6]/div[2]/div/div/label/input", "50.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[6]/div[3]/div/div/label/input", "87.5");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[6]/div[4]/div/div/label/input", "27.95084971874737");
		
		
		// Acepted in GBP
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[7]/div[1]/div/div/label/input", "226.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[7]/div[2]/div/div/label/input", "226.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[7]/div[3]/div/div/label/input", "226.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[7]/div[4]/div/div/label/input", "0.0");
		
		// Acepted in USD
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[8]/div[1]/div/div/label/input", "266.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[8]/div[2]/div/div/label/input", "266.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[8]/div[3]/div/div/label/input", "266.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[8]/div[4]/div/div/label/input", "0.0");
		
		
		// Denied in EUR
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[9]/div[1]/div/div/label/input", "225.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[9]/div[2]/div/div/label/input", "150.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[9]/div[3]/div/div/label/input", "187.5");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[9]/div[4]/div/div/label/input", "27.95084971874737");
		
		// Denied in GBP
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[10]/div[1]/div/div/label/input", "126.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[10]/div[2]/div/div/label/input", "126.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[10]/div[3]/div/div/label/input", "126.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[10]/div[4]/div/div/label/input", "0.0");
				
		// Denied in USD
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[11]/div[1]/div/div/label/input", "300.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[11]/div[2]/div/div/label/input", "300.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[11]/div[3]/div/div/label/input", "300.0");
		inputsMap.put("/html/body/div[2]/div/div[1]/div/div[11]/div[4]/div/div/label/input", "0.0");
		
		
		
		
		

		
		for(final Map.Entry<String, String> entry : inputsMap.entrySet()) {
			this.checkXpathInputValue(entry.getKey(), entry.getValue());
		}
		
	}
	
	// Ancillary methods ------------------------------------------------------ 
	
}
