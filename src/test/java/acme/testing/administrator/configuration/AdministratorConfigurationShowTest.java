package acme.testing.administrator.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorConfigurationShowTest extends TestHarness {
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/show.csv", numLinesToSkip = 1)
	@Order(0)
	public void checkListing(final int recordIndex, final String weakSpamWords, final String weakSpamThreshold, final String strongSpamWords, final String strongSpamThreshold, final String defaultCurr, final String acceptedCurr) {
		super.signIn("administrator", "administrator");
		
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
	@CsvFileSource(resources = "/administrator/configuration/show.csv", numLinesToSkip = 1)
	@Order(1)
	public void checkShow(final int recordIndex, final String weakSpamWords, final String weakSpamThreshold, final String strongSpamWords, final String strongSpamThreshold, final String defaultCurr, final String acceptedCurr) {
		super.signIn("administrator", "administrator");
		
		super.navigate("/administrator/configuration/show");
		
		super.checkFormExists();
		super.checkInputBoxHasValue("weakSpamWords", weakSpamWords);
		super.checkInputBoxHasValue("weakSpamThreshold", weakSpamThreshold);
		super.checkInputBoxHasValue("strongSpamWords", strongSpamWords);
		super.checkInputBoxHasValue("strongSpamThreshold", strongSpamThreshold);
		super.checkInputBoxHasValue("defaultCurr", defaultCurr);
		super.checkInputBoxHasValue("acceptedCurr", acceptedCurr);
		
		super.signOut();
	}
}
