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

package acme.testing.administrator.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;


public class AdministratorDashboardTest extends TestHarness {
	
	private final String adminPath = "/administrator/dashboard/show";
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@Test
	@Order(0)
	public void anonymousAccess() {		
		this.checkXpathNotExists("//*[@id='mainMenu']/ul[1]/li[2]/div/a[3][normalize-space() = 'Administrator']");
		
		this.navigate(this.adminPath);
		this.checkPanicExists();
	}
	
	@Test
	@Order(1)
	public void checkDashboard() {
		this.signIn("administrator", "administrator");
		
		// check that an admin user has admin dashboard link on menu
		this.checkXpathContains("//*[@id='mainMenu']/ul[1]/li[2]/div/a[3]", "Dashboard");
		
		// check that an admin user can access to admin dashboard
		this.navigate(this.adminPath);
		this.checkNotPanicExists();
		
		// check admin dashboard input exists
		this.checkXpathExists("//*[@id=\"totals\"]/div[1]/div[1]/div/div/label/input");
	}
	
	@Test
	@Order(2)
	public void checkDashboardInputs() {
		
		this.signIn("administrator", "administrator");
		this.navigate(this.adminPath);
		
		final Map<String, String> inputsMap = new HashMap<String, String>();
		
		// Total items & patronages
		inputsMap.put("//*[@id=\"totals\"]/div[1]/div[1]/div/div/label/input", "3");
		inputsMap.put("//*[@id=\"totals\"]/div[1]/div[2]/div/div/label/input", "3");
		
		// Total proposed, accepted & denied patronages
		inputsMap.put("//*[@id=\"totals\"]/div[2]/div[1]/div/div/label/input", "6");
		inputsMap.put("//*[@id=\"totals\"]/div[2]/div[2]/div/div/label/input", "6");
		inputsMap.put("//*[@id=\"totals\"]/div[2]/div[3]/div/div/label/input", "6");
		
		// Budgets
		// Proposed
		inputsMap.put("//*[@id=\"budgets\"]/div[1]/div[1]/div/div/label/input", "326.0");
		inputsMap.put("//*[@id=\"budgets\"]/div[1]/div[2]/div/div/label/input", "250.0");
		inputsMap.put("//*[@id=\"budgets\"]/div[1]/div[3]/div/div/label/input", "300.3333333333333");
		inputsMap.put("//*[@id=\"budgets\"]/div[1]/div[4]/div/div/label/input", "29.15857030483871");
		
		// Acepted
		inputsMap.put("//*[@id=\"budgets\"]/div[2]/div[1]/div/div/label/input", "266.0");
		inputsMap.put("//*[@id=\"budgets\"]/div[2]/div[2]/div/div/label/input", "50.0");
		inputsMap.put("//*[@id=\"budgets\"]/div[2]/div[3]/div/div/label/input", "140.33333333333334");
		inputsMap.put("//*[@id=\"budgets\"]/div[2]/div[4]/div/div/label/input", "78.97397602304756");
		
		// Denied
		inputsMap.put("//*[@id=\"budgets\"]/div[3]/div[1]/div/div/label/input", "300.0");
		inputsMap.put("//*[@id=\"budgets\"]/div[3]/div[2]/div/div/label/input", "126.0");
		inputsMap.put("//*[@id=\"budgets\"]/div[3]/div[3]/div/div/label/input", "196.0");
		inputsMap.put("//*[@id=\"budgets\"]/div[3]/div[4]/div/div/label/input", "56.46532859492924");
		
		// Components data by tech & currency
		// Server in USD
		inputsMap.put("//*[@id=\"components\"]/div[1]/div[1]/div/div/label/input", "10.0");
		inputsMap.put("//*[@id=\"components\"]/div[1]/div[2]/div/div/label/input", "10.0");
		inputsMap.put("//*[@id=\"components\"]/div[1]/div[3]/div/div/label/input", "10.0");
		inputsMap.put("//*[@id=\"components\"]/div[1]/div[4]/div/div/label/input", "0.0");
		
		// Server in GBP
		inputsMap.put("//*[@id=\"components\"]/div[2]/div[1]/div/div/label/input", "125.0");
		inputsMap.put("//*[@id=\"components\"]/div[2]/div[2]/div/div/label/input", "122.0");
		inputsMap.put("//*[@id=\"components\"]/div[2]/div[3]/div/div/label/input", "123.5");
		inputsMap.put("//*[@id=\"components\"]/div[2]/div[4]/div/div/label/input", "1.5");
		
		// Total items and patronages
		// Component -> currency GBP
		inputsMap.put("//*[@id=\"items\"]/div[1]/div[1]/div/div/label/input", "125.0");
		inputsMap.put("//*[@id=\"items\"]/div[1]/div[2]/div/div/label/input", "122.0");
		inputsMap.put("//*[@id=\"items\"]/div[1]/div[3]/div/div/label/input", "123.5");
		inputsMap.put("//*[@id=\"items\"]/div[1]/div[4]/div/div/label/input", "1.5");
		
		// Component -> currency USD
		inputsMap.put("//*[@id=\"items\"]/div[2]/div[1]/div/div/label/input", "10.0");
		inputsMap.put("//*[@id=\"items\"]/div[2]/div[2]/div/div/label/input", "10.0");
		inputsMap.put("//*[@id=\"items\"]/div[2]/div[3]/div/div/label/input", "10.0");
		inputsMap.put("//*[@id=\"items\"]/div[2]/div[4]/div/div/label/input", "0.0");
		
		// Tool -> currency eur
		inputsMap.put("//*[@id=\"items\"]/div[3]/div[1]/div/div/label/input", "186.0");
		inputsMap.put("//*[@id=\"items\"]/div[3]/div[2]/div/div/label/input", "100.0");
		inputsMap.put("//*[@id=\"items\"]/div[3]/div[3]/div/div/label/input", "143.0");
		inputsMap.put("//*[@id=\"items\"]/div[3]/div[4]/div/div/label/input", "43.0");
		
		// Tool -> currency eur
		inputsMap.put("//*[@id=\"items\"]/div[4]/div[1]/div/div/label/input", "980.0");
		inputsMap.put("//*[@id=\"items\"]/div[4]/div[2]/div/div/label/input", "980.0");
		inputsMap.put("//*[@id=\"items\"]/div[4]/div[3]/div/div/label/input", "980.0");
		inputsMap.put("//*[@id=\"items\"]/div[4]/div[4]/div/div/label/input", "0.0");
		
		for(final Map.Entry<String, String> entry : inputsMap.entrySet()) {
			this.checkXpathInputValue(entry.getKey(), entry.getValue());
		}
		
	}
	
	// Ancillary methods ------------------------------------------------------ 
	
}
