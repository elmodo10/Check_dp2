package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitDeleteTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list-ownPositivo3.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveToolkitDelete(final int recordIndex, final String title, final String descripcion, final String assemblyNotes,
		final String link) {
		
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List Own Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, descripcion);
		super.checkColumnHasValue(recordIndex, 2, link);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("descripcion", descripcion);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list-ownNegativo3.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeToolkitDelete(final int recordIndex, final String title, final String descripcion, final String assemblyNotes,
		final String link) {
		
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List Own Toolkits");
		super.checkListingExists();
		super.sortListing(0, "desc");

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("descripcion", descripcion);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);
		
		super.checkNotSubmitExists("Delete");
		super.signOut();
	}
	

	
}