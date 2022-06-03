package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class CreateTestsAnyChirp extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/chirpPositivo.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String version,final String author, final String body, 
		final String email, final String title, final String confirm) {

		super.clickOnMenu("Anonymous","Chirps");
		super.checkListingExists();
		super.clickOnButton("Create");
		super.fillInputBoxIn("title",title);
		super.fillInputBoxIn("author",author);
		super.fillInputBoxIn("body",body);
		super.fillInputBoxIn("email",email);
		//Creado el atributo confirm en la base de datos para poder aceptar el checkbox requerido
		super.fillInputBoxIn("confirm", confirm);
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Anonymous","Chirps");
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, author);
		super.checkColumnHasValue(recordIndex, 1, body);
		super.checkColumnHasValue(recordIndex, 3, email);
		super.checkColumnHasValue(recordIndex, 4, title);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/chirpNegativo.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String version,final String author, final String body, 
		final String email, final String title, final String confirm) {
		//Misma estructura que en el positivo
		super.clickOnMenu("Anonymous","Chirps");
		super.checkListingExists();
		super.clickOnButton("Create");
		super.fillInputBoxIn("title",title);
		super.fillInputBoxIn("author",author);
		super.fillInputBoxIn("body",body);
		super.fillInputBoxIn("email",email);
		//Creado el atributo confirm en la base de datos para poder aceptar el checkbox requerido
		super.fillInputBoxIn("confirm", confirm);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		
	}
	
		//No existe la posibilidad de hacer un hackingTest
	
}