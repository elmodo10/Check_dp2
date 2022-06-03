package acme.testing.inventor.toolkit;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitPublishTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list-ownPositivo3.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveToolkitPublish(final int recordIndex, final String title, final String descripcion, final String assemblyNotes,
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
		
		super.clickOnSubmit("Publish");
		super.checkNotErrorsExist();

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list-ownNegativo3.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeToolkitPublish(final int recordIndex, final String title, final String descripcion, final String assemblyNotes,
		final String link) {
		
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List Own Toolkits");
		super.checkListingExists();
		super.sortListing(0, "desc");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, descripcion);
		super.checkColumnHasValue(recordIndex, 2, link);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkNotSubmitExists("Publish");
		
		super.signOut();
	}

	
}
	