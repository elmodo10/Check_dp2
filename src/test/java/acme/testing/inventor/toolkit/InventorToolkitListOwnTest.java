package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitListOwnTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list-own.csv", encoding = "utf-8" ,numLinesToSkip = 1)
	@Order(10)
	public void positiveCase(final int recordIndex, final String code, final String title, final String descripcion, final String assemblyNotes, final String retailPrice, final String link, 
		final String name, final String code2,final String retailPrice2, final String status) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List Own Toolkits");
		
		super.checkListingExists();
		super.sortListing(0, "desc");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, descripcion);
		super.checkColumnHasValue(recordIndex, 2, link);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("descripcion", descripcion);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		
		super.clickOnButton("Items");
		
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, code2);
		super.checkColumnHasValue(recordIndex, 3, retailPrice2);
		super.checkColumnHasValue(recordIndex, 5, status);
		
		super.signOut();
	}

}
