package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class CreateTestInventorPatronageReport extends TestHarness{
		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/patronageReport/patronagePositivo.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final int recordIndex, final String seqNumber, final String createdAt, final String memorandum,final String link, final String confirm) {
			
			
			super.signIn("administrator", "administrator");
			super.clickOnMenu("Inventor", "Patronages");
			super.clickOnListingRecord(recordIndex);
	        
	        super.clickOnButton("Create new patronage report");
			super.fillInputBoxIn("memorandum", memorandum);
			super.fillInputBoxIn("link", link);
			//Creado el atributo confirm en la base de datos para poder aceptar el checkbox requerido
			super.fillInputBoxIn("confirm", confirm);
			super.clickOnSubmit("Create new patronage report");
	        super.clickOnButton("Patronage Reports");
			super.checkListingExists();
			super.checkColumnHasValue(recordIndex, 2, memorandum);
			super.checkColumnHasValue(recordIndex, 3, link);

			
			super.signOut();
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/patronageReport/patronageNegativo.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void negativeTest(final int recordIndex, final String seqNumber, final String createdAt, final String memorandum,final String link, final String confirm) {
			
			//Misma estructura que en el positivo

			super.signIn("administrator", "administrator");
			super.clickOnMenu("Inventor", "Patronages");
			super.clickOnListingRecord(recordIndex);
	        
	        super.clickOnButton("Create new patronage report");
			super.fillInputBoxIn("memorandum", memorandum);
			super.fillInputBoxIn("link", link);
			//Creado el atributo confirm en la base de datos para poder aceptar el checkbox requerido
			super.fillInputBoxIn("confirm", confirm);
			super.clickOnSubmit("Create new patronage report");
	        
			super.checkErrorsExist();

		}
		
		@Test
        @Order(10)
        public void hackingTest() {

            super.navigate("/inventor/patronageReport/create");
            super.checkPanicExists();

            super.signIn("patron1", "patron1");
            super.navigate("/inventor/patronageReport/create");
            super.checkPanicExists();
            super.signOut();

        }
		
}
