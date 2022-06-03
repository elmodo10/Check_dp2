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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;


public class PatronPatronageTest extends TestHarness {
    @ParameterizedTest
    @CsvFileSource(resources = "/patron/patronage/patronage.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10) 
    public void testpatronOnPatronageReportsList(final int recordIndex, final String status,final String code, final String legalStuff,final String budget ,final String startsAt, final String finishesAt, final String link) {

        super.signIn("administrator", "administrator");
        super.clickOnMenu("Patron", "List Patronage");
        super.checkListingExists();

        super.checkColumnHasValue(recordIndex, 0, status);
        super.checkColumnHasValue(recordIndex, 1, code);
        super.checkColumnHasValue(recordIndex, 2, legalStuff);
        super.checkColumnHasValue(recordIndex, 3, budget);
        super.checkColumnHasValue(recordIndex, 5, startsAt);
        super.checkColumnHasValue(recordIndex, 6, finishesAt);
        
        
        super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
        super.checkInputBoxHasValue("status", status);
        super.checkInputBoxHasValue("code", code);
        super.checkInputBoxHasValue("legalStuff", legalStuff);
        super.checkInputBoxHasValue("budget", budget);
        super.checkInputBoxHasValue("startsAt", startsAt);
        super.checkInputBoxHasValue("finishesAt", finishesAt);
        super.checkInputBoxHasValue("link", link);
       
        
        

        super.signOut();
    }
}
