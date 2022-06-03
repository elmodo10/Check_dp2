package acme.testing.authenticated.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedAnnouncementTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/announcement.csv", encoding = "utf-8" ,numLinesToSkip = 1)
	@Order(10)
	public void positiveCase(final int recordIndex,final String title, final String creation, final String body,final String link, final String critic) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Authenticated", "List Announcement");
		
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, creation);
		super.checkColumnHasValue(recordIndex, 2, link);
		super.checkColumnHasValue(recordIndex, 3, critic);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("creation", creation);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("critic", critic);

		super.signOut();
	}

}