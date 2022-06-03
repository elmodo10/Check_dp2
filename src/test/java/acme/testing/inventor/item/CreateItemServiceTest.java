package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class CreateItemServiceTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/componentPositivo.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String code,
		final String technology, final String description, final String retailPrice, final String link) {

		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List Own Components");
		super.checkListingExists();
		super.clickOnButton("Create");
		super.fillInputBoxIn("name",name);
		super.fillInputBoxIn("technology",technology);
		super.fillInputBoxIn("description",description);
		super.fillInputBoxIn("retailPrice",retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor", "List Own Components");
		super.checkListingExists();

		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, retailPrice);
		
		super.signOut();

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/componentNegativo.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String name, final String code,
		final String technology, final String description, final String retailPrice, final String link) {

		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List Own Components");
		super.checkListingExists();
		super.clickOnButton("Create");
		super.fillInputBoxIn("name",name);
		super.fillInputBoxIn("technology",technology);
		super.fillInputBoxIn("description",description);
		super.fillInputBoxIn("retailPrice",retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		super.signOut();
	
	}	

}