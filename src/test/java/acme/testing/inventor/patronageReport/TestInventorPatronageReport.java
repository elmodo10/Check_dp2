package acme.testing.inventor.patronageReport;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class TestInventorPatronageReport extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronageReport/patronage.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10) 
	public void testInventorsOnPatronageReportsList(final int recordIndex, final String status, final String code, final String legalStuff, final String budget, 
		final String starsAt, final String finishesAt,final String link,final String seqNumber, final String createdAt, final String memorandum, final String link2) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Inventor", "Patronages");
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, budget);
		
        super.clickOnListingRecord(recordIndex);
        
        super.clickOnButton("Patronage Reports");
        
        super.checkColumnHasValue(recordIndex, 0, seqNumber);
		super.checkColumnHasValue(recordIndex, 1, createdAt);
		super.checkColumnHasValue(recordIndex, 2, memorandum);
		super.checkColumnHasValue(recordIndex, 3, link2);
		
        super.clickOnListingRecord(recordIndex);

        super.checkInputBoxHasValue("seqNumber", seqNumber);
        super.checkInputBoxHasValue("createdAt", createdAt);
        super.checkInputBoxHasValue("memorandum", memorandum);
        


		super.signOut();
	}

}