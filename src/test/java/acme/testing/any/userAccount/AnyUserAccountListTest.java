package acme.testing.any.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyUserAccountListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/userAccount/list-enabled.csv", encoding = "utf-8" ,numLinesToSkip = 1)
	@Order(10)
	public void positiveCase(final int recordIndex, final String roles,final String name, final String surname, final String email){
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Authenticated", "List Enabled Accounts");
		
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 0, roles);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, surname);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("identity.name", name);
		super.checkInputBoxHasValue("identity.surname", surname);
		super.checkInputBoxHasValue("identity.email", email);
		
		super.checkNotErrorsExist();
	}
		
	

}
