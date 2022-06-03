package acme.testing.any.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyToolkitTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/toolkit/toolkit.csv", encoding = "utf-8" ,numLinesToSkip = 1)
	@Order(10)
	public void positiveCase(final int recordIndex, final String code, final String title, final String descripcion, final String assemblyNotes, final String link) {
		
		
		super.clickOnMenu("Anonymous", "List Published Toolkits");
		
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, link);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("descripcion", descripcion);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);
	}
}