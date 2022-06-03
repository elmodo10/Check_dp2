package acme.testing.any.items;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyItemsTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/tools.csv", numLinesToSkip = 1)
	@Order(0)
	public void checkToolListing(final int recordIndex, final String name, final String code , final String technology, final String description, final String retailPrice, final String link, final String type) {
		//super.signIn("administrator", "administrator");
		
		// Name,Code,Technology,Description,Info
		
		super.clickOnMenu("Anonymous", "List Published Tools");
		
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, technology);
		super.checkColumnHasValue(recordIndex, 3, description);
		super.checkColumnHasValue(recordIndex, 4, link);
		
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/tools.csv", numLinesToSkip = 1)
	@Order(1)
	public void checkShow(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String type) {

		super.clickOnMenu("Anonymous", "List Published Tools");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("type", type);
		
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/components.csv", numLinesToSkip = 1)
	@Order(3)
	public void checkComponentListing(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String type) {
		
		
		super.clickOnMenu("Anonymous", "List Published Components");
		
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, technology);
		super.checkColumnHasValue(recordIndex, 3, description);
		super.checkColumnHasValue(recordIndex, 4, link);
		super.checkColumnHasValue(recordIndex, 5, type);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/components.csv", numLinesToSkip = 1)
	@Order(4)
	public void checkComponentShow(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String type) {

		super.clickOnMenu("Anonymous", "List Published Components");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("type", type);
		
	}
}
