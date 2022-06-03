package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ComponentListServiceTest extends TestHarness{

	// Test cases -------------------------------------------------------------------------------------
	

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/component.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void InventorListComponentTest(final int recordIndex, final String name, final String code,
		final String technology, final String description, final String retailPrice) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List Own Components");
		
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, retailPrice);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);

		super.signOut();

	}
}
