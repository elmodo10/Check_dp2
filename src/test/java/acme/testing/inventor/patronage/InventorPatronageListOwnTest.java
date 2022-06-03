package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageListOwnTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/list-own.csv", encoding = "utf-8" ,numLinesToSkip = 1)
	@Order(10)
	public void positiveCase(final int recordIndex, final String status, final String code, final String legalStuff, 
		final String budget, final String startsAt, final String finishesAt, final String link) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "Patronages");
		
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, budget);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("startsAt", startsAt);
		super.checkInputBoxHasValue("finishesAt", finishesAt);
		super.checkInputBoxHasValue("link", link);
		
		//super.signOut();
	}
}
