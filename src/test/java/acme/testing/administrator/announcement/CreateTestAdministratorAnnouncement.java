package acme.testing.administrator.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class CreateTestAdministratorAnnouncement extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/announcementPositivo.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String version,final String title, final String body, 
		final String critic, final String link, final String confirm) {

		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator","List announcements");
		super.checkListingExists();
		super.clickOnButton("Create");
		super.fillInputBoxIn("title",title);
		super.fillInputBoxIn("body",body);
		super.fillInputBoxIn("critic",critic);
		super.fillInputBoxIn("link",link);
		super.fillInputBoxIn("confirm", confirm);
		super.clickOnSubmit("Create");
		super.checkListingExists();
		

		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, body);
		super.checkColumnHasValue(recordIndex, 3, critic);
		super.checkColumnHasValue(recordIndex, 4, link);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/announcementNegativo.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String version,final String title, final String body, 
		final String critic, final String link, final String confirm) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator","List announcements");
		super.checkListingExists();
		super.clickOnButton("Create");
		super.fillInputBoxIn("title",title);
		super.fillInputBoxIn("body",body);
		super.fillInputBoxIn("critic",critic);
		super.fillInputBoxIn("link",link);
		super.fillInputBoxIn("confirm", confirm);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		
	}
	
	@Test
    @Order(10)
    public void hackingTest() {

        super.navigate("/administrator/announcement/create");
        super.checkPanicExists();

        super.signIn("inventor1", "inventor1");
        super.navigate("/administrator/announcement/create");
        super.checkPanicExists();
        super.signOut();

        super.signIn("patron1", "patron1");
        super.navigate("/administrator/announcement/create");
        super.checkPanicExists();
        super.signOut();
    }

}
