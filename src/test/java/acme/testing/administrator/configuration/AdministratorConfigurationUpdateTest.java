package acme.testing.administrator.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorConfigurationUpdateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/update.csv" , encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String weakSpamWords, final String weakSpamThreshold, 
		final String strongSpamWords,final String strongSpamThreshold, final String acceptedCurr, final String defaultCurr) {
		
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Configurations");
		
        super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
		super.fillInputBoxIn("weakSpamWords", weakSpamWords);
		super.fillInputBoxIn("weakSpamThreshold", weakSpamThreshold);
		super.fillInputBoxIn("strongSpamWords", strongSpamWords);
		super.fillInputBoxIn("strongSpamThreshold", strongSpamThreshold);
		super.fillInputBoxIn("acceptedCurr", acceptedCurr);
		super.fillInputBoxIn("defaultCurr", defaultCurr);
		
		super.clickOnSubmit("Update configuration");	
				
		
		super.clickOnMenu("Administrator", "Configurations");
		super.checkListingExists();

		super.checkColumnHasValue(recordIndex, 0, weakSpamWords);
		super.checkColumnHasValue(recordIndex, 1, weakSpamThreshold);
		super.checkColumnHasValue(recordIndex, 2, strongSpamWords);
		super.checkColumnHasValue(recordIndex, 3, strongSpamThreshold);
		super.checkColumnHasValue(recordIndex, 4, acceptedCurr);
		super.checkColumnHasValue(recordIndex, 5, defaultCurr);
	
		super.signOut();	
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/updateNegativo.csv" , encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String weakSpamWords, final String weakSpamThreshold, 
		final String strongSpamWords,final String strongSpamThreshold, final String acceptedCurr, final String defaultCurr) {
		
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Configurations");
		
        super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
		super.fillInputBoxIn("weakSpamWords", weakSpamWords);
		super.fillInputBoxIn("weakSpamThreshold", weakSpamThreshold);
		super.fillInputBoxIn("strongSpamWords", strongSpamWords);
		super.fillInputBoxIn("strongSpamThreshold", strongSpamThreshold);
		super.fillInputBoxIn("acceptedCurr", acceptedCurr);
		super.fillInputBoxIn("defaultCurr", defaultCurr);
		
		super.clickOnSubmit("Update configuration");	
				
		super.checkErrorsExist();

		super.signOut();	
		
	}
	

}
