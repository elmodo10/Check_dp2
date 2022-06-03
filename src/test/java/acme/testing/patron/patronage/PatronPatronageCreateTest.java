/*
 * FavouriteLinkTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/patronagePositivo.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code, final String legalStuff, final String budget, final String startsAt, final String finishesAt, final String link) {

		super.signIn("administrator", "administrator");
		super.clickOnMenu("Patron", "List Patronage");
		super.checkListingExists();
		super.clickOnButton("Create Patronage");
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startsAt", startsAt);
		super.fillInputBoxIn("finishesAt", finishesAt);
		super.fillInputBoxIn("link", link);

		super.clickOnSubmit("Create Patronage");

		super.clickOnMenu("Patron", "List Patronage");
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, legalStuff);
		super.checkColumnHasValue(recordIndex, 3, budget);
		super.checkColumnHasValue(recordIndex, 5, startsAt);
		super.checkColumnHasValue(recordIndex, 6, finishesAt);

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/patronageNegativo.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String status, final String code, final String legalStuff, final String budget, final String startsAt, final String finishesAt, final String link) {
		//Misma estructura que en el positivo
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Patron", "List Patronage");
		super.checkListingExists();
		super.clickOnButton("Create Patronage");
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startsAt", startsAt);
		super.fillInputBoxIn("finishesAt", finishesAt);
		super.fillInputBoxIn("link", link);

		super.clickOnSubmit("Create Patronage");

		super.checkErrorsExist();

		super.signOut();

	}

	@Test
    @Order(10)
    public void hackingTest() {

        super.navigate("/patron/patronage/create");
        super.checkPanicExists();

        super.signIn("inventor1", "inventor1");
        super.navigate("/patron/patronage/create");
        super.checkPanicExists();
        super.signOut();


    }
		

}
