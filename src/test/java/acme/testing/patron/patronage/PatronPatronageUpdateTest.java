package acme.testing.patron.patronage;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageUpdateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/patronagePositivo.csv" , encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code,final String legalStuff,final String budget ,final String startsAt, final String finishesAt, final String link) {
		
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Patron", "List Patronage");
		
        super.clickOnListingRecord(recordIndex);
        
        super.checkFormExists();
        super.clickOnButton("Update Patronage");
        super.fillInputBoxIn("legalStuff",legalStuff);
		super.fillInputBoxIn("budget",budget);
		super.fillInputBoxIn("startsAt",startsAt);
		super.fillInputBoxIn("finishesAt",finishesAt);
		super.fillInputBoxIn("link",link);
		
		super.clickOnSubmit("Update Patronage");	
				
		super.clickOnMenu("Patron", "List Patronage");
		super.checkListingExists();


        super.checkColumnHasValue(recordIndex, 2, legalStuff);
        super.checkColumnHasValue(recordIndex, 3, budget);
        super.checkColumnHasValue(recordIndex, 5, startsAt);
        super.checkColumnHasValue(recordIndex, 6, finishesAt);
	
		super.signOut();	
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/patronageNegativo.csv" , encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String legalStuff,final String budget ,final String startsAt, final String finishesAt, final String link) {
		
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Patron", "List Patronage");
		
        super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
        super.clickOnButton("Update Patronage");
        super.fillInputBoxIn("legalStuff",legalStuff);
		super.fillInputBoxIn("budget",budget);
		super.fillInputBoxIn("startsAt",startsAt);
		super.fillInputBoxIn("finishesAt",finishesAt);
		super.fillInputBoxIn("link",link);
		
		super.clickOnSubmit("Update Patronage");	
				
		super.checkErrorsExist();

		super.signOut();	
		
	}
	
	
	

}
