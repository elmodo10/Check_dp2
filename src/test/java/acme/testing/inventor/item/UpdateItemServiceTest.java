package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class UpdateItemServiceTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/toolPositivo.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String code,
		final String technology, final String description, final String retailPrice, final String link) {

		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List Own Tools");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);
		super.clickOnButton("Update");
		super.checkFormExists();
		super.fillInputBoxIn("name",name);
		super.fillInputBoxIn("technology",technology);
		super.fillInputBoxIn("description",description);
		super.fillInputBoxIn("retailPrice",retailPrice);
		super.fillInputBoxIn("link", link);
	
		super.clickOnSubmit("Update");

		super.clickOnMenu("Inventor", "List Own Tools");

		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, retailPrice);
		
		super.signOut();

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/toolNegativo.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String name, final String code,
		final String technology, final String description, final String retailPrice, final String link) {

		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List Own Tools");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);
		super.clickOnButton("Update");
		super.fillInputBoxIn("name",name);
		super.fillInputBoxIn("technology",technology);
		super.fillInputBoxIn("description",description);
		super.fillInputBoxIn("retailPrice",retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();
		super.signOut();
	
	}	


}
