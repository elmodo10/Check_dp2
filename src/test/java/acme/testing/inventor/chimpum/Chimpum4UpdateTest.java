package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class Chimpum4UpdateTest extends TestHarness{

	// Test cases -------------------------------------------------------------------------------------
	

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/chimpumUpdate.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void InventorUpdateChimpumPositiveTest(final int recordIndex,final String code,
		final String title, final String description, final String startsAt, final String finishesAt, final String budget, 
		final String link) {

	super.signIn("administrator", "administrator");
	
	super.clickOnMenu("Inventor", "List Own Components");
	super.checkListingExists();
	super.clickOnListingRecord(recordIndex);

	super.clickOnButton("List Chimpum");
	
	
	super.clickOnListingRecord(recordIndex);
	
	super.clickOnButton("Update");


	super.fillInputBoxIn("code", code);
	super.fillInputBoxIn("title", title);
	super.fillInputBoxIn("description", description);
	super.fillInputBoxIn("startsAt", startsAt);
	super.fillInputBoxIn("finishesAt", finishesAt);
	super.fillInputBoxIn("budget", budget);
	super.fillInputBoxIn("link", link);
	
	super.clickOnSubmit("Update");
	super.clickOnButton("Return");
	super.checkListingExists();
	super.checkColumnHasValue(recordIndex, 0, code);
	super.checkColumnHasValue(recordIndex, 2, title);
	super.checkColumnHasValue(recordIndex, 3, description);
	super.checkColumnHasValue(recordIndex, 4, startsAt);
	super.checkColumnHasValue(recordIndex, 5, finishesAt);
	super.checkColumnHasValue(recordIndex, 6, budget);
	super.checkColumnHasValue(recordIndex, 7, link);


	super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/chimpumUpdateN.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void InventorUpdateChimpumNegativeTest(final int recordIndex,final String code,
		final String title, final String description, final String startsAt, final String finishesAt, final String budget, 
		final String link) {

	super.signIn("administrator", "administrator");
	
	super.clickOnMenu("Inventor", "List Own Components");
	super.checkListingExists();
	
	super.clickOnListingRecord(recordIndex);

	super.clickOnButton("List Chimpum");
	super.clickOnListingRecord(recordIndex);
	super.clickOnButton("Update");


	
	super.fillInputBoxIn("title", title);
	super.fillInputBoxIn("description", description);
	super.fillInputBoxIn("startsAt", startsAt);
	super.fillInputBoxIn("finishesAt", finishesAt);
	super.fillInputBoxIn("budget", budget);
	super.fillInputBoxIn("link", link);
	
	super.clickOnSubmit("Update");
	super.checkErrorsExist();

	super.signOut();
	}
	
}