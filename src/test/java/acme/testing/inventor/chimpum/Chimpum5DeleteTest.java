package acme.testing.inventor.chimpum;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class Chimpum5DeleteTest extends TestHarness{

	// Test cases -------------------------------------------------------------------------------------
	

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/chimpum.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void InventorUpdateChimpumPositiveTest(final int recordIndex, final String code, final String creation,
		final String title, final String description, final String startsAt, final String finishesAt, final String budget, 
		final String link) {

	super.signIn("administrator", "administrator");
	
	super.clickOnMenu("Inventor", "List Own Components");
	super.checkListingExists();
	super.clickOnListingRecord(recordIndex);

	super.clickOnButton("List Chimpum");
	super.checkListingExists();
	super.checkColumnHasValue(recordIndex, 2, title);
	super.checkColumnHasValue(recordIndex, 3, description);
	super.checkColumnHasValue(recordIndex, 4, startsAt);
	super.checkColumnHasValue(recordIndex, 5, finishesAt);
	super.checkColumnHasValue(recordIndex, 6, budget);
	super.checkColumnHasValue(recordIndex, 7, link);
	
	super.clickOnListingRecord(recordIndex);
	
	super.checkInputBoxHasValue("code", code);
	super.checkInputBoxHasValue("creation", creation);
	super.checkInputBoxHasValue("title", title);
	super.checkInputBoxHasValue("description", description);
	super.checkInputBoxHasValue("startsAt", startsAt);
	super.checkInputBoxHasValue("finishesAt", finishesAt);
	super.checkInputBoxHasValue("budget", budget);
	super.checkInputBoxHasValue("link", link);
	
	super.clickOnSubmit("Delete");
	super.checkNotErrorsExist();
	super.checkListingEmpty();


	super.signOut();
	}
}