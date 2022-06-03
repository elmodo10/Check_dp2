package acme.testing.authenticated.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedConfigurationShowTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/configuration/show.csv", encoding = "utf-8" ,numLinesToSkip = 1)
	@Order(10)
	public void positiveCase(final String defaultCurr, final String acceptedCurr) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Authenticated", "System configuration");
		
		super.checkFormExists();
		super.checkInputBoxHasValue("defaultCurr", defaultCurr);
		super.checkInputBoxHasValue("acceptedCurr", acceptedCurr);
		
		super.signOut();
	}
}
