package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitCreateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list-ownPositivo.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveToolkit(final int recordIndex, final String code, final String title, final String descripcion, final String assemblyNotes,
		final String link) {
		
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List Own Toolkits");
		super.checkListingExists();
		
		super.clickOnButton("Create a toolkit");
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("descripcion", descripcion);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
		
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, descripcion);
		super.checkColumnHasValue(recordIndex, 2, link);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("descripcion", descripcion);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);
		super.clickOnButton("Items");
		super.checkListingExists();
		super.checkListingEmpty();

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list-ownNegativo.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeToolkit(final int recordIndex, final String code, final String title, final String descripcion, final String assemblyNotes,
		final String link) {
		
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List Own Toolkits");
		super.checkListingExists();
		
		super.clickOnButton("Create a toolkit");
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("descripcion", descripcion);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		super.signOut();
	}
	
	@Test
    @Order(30)
    public void hackingTest() {
        super.checkNotLinkExists("Account");
        super.navigate("/inventor/toolkit/create");
        super.checkPanicExists();

        super.signIn("patron1", "patron1");
        super.navigate("/inventor/toolkit/create");
        super.checkPanicExists();
        super.signOut();


    }
}
