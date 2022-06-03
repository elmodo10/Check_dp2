package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PublishItemServiceTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/toolPositivo.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name,final String code,final String technology, 
		final String description, final String retailPrice, final String link, final String status, final String type) {

		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List Own Tools");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Publish");

		super.clickOnMenu("Inventor", "List Own Tools");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("type", type);
		super.signOut();
	}
}
