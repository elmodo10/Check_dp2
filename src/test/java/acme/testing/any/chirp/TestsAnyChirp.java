package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class TestsAnyChirp extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/chirp.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void testAnyChirpList(final int recordIndex,final String author, final String body, 
		final String creationTime, final String email, final String title ) {

		
		super.clickOnMenu("Anonymous","Chirps");
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, author);
		super.checkColumnHasValue(recordIndex, 1, body);
		super.checkColumnHasValue(recordIndex, 2, creationTime);
		super.checkColumnHasValue(recordIndex, 3, email);
		super.checkColumnHasValue(recordIndex, 4, title);

	}
}