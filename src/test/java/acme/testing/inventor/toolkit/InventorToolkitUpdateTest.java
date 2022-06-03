package acme.testing.inventor.toolkit;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitUpdateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list-ownPositivo2.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveToolkitUpdate(final int recordIndex, final String code, final String title, final String descripcion, final String assemblyNotes,
		final String link, final String title2, final String descripcion2) {
		
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List Own Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");

		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, descripcion);
		super.checkColumnHasValue(recordIndex, 2, link);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		
		super.fillInputBoxIn("title", title2);
		super.fillInputBoxIn("descripcion", descripcion2);
		
		super.clickOnSubmit("Update");
		
		super.checkColumnHasValue(recordIndex, 0, title2);
		super.checkColumnHasValue(recordIndex, 1, descripcion2);
		super.checkColumnHasValue(recordIndex, 2, link);
		
		super.signOut();
	}
	

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list-ownNegativo2.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeToolkitUpdate(final int recordIndex, final String code, final String title, final String descripcion, final String assemblyNotes,
		final String link) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List Own Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("descripcion", descripcion);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmit("Update");
		super.checkErrorsExist();

		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list-ownNegativo2-5.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeToolkitUpdate2(final int recordIndex, final String title, final String descripcion, final String assemblyNotes,
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
		
		super.checkNotSubmitExists("Update");

		
		super.signOut();
	}
}