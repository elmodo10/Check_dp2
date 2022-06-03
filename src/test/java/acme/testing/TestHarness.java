/*
 * TestHarness.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing;

import org.hibernate.internal.util.StringHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import acme.framework.testing.AbstractTest;

public abstract class TestHarness extends AbstractTest {
	
	// Business methods -------------------------------------------------------
	
	protected void signIn(final String username, final String password) {
		assert !StringHelper.isBlank(username);
		assert !StringHelper.isBlank(password);
		
		super.navigateHome();
		super.clickOnMenu("Sign in");
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("remember", "true");
		super.clickOnSubmit("Sign in");
		super.checkCurrentPath("/master/welcome");
		super.checkLinkExists("Account");
	}

	protected void signOut() {
		super.navigateHome();
		super.clickOnMenu("Sign out");
		super.checkCurrentPath("/master/welcome");
		super.checkNotLinkExists("Account");
	}

	protected void signUp(final String username, final String password, final String name, final String surname, final String email) {
		assert !StringHelper.isBlank(username);
		assert !StringHelper.isBlank(password);
		assert !StringHelper.isBlank(name);
		assert !StringHelper.isBlank(surname);
		assert !StringHelper.isBlank(email);

		super.navigateHome();
		super.clickOnMenu("Sign up");	
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("confirmation", password);
		super.fillInputBoxIn("identity.name", name);
		super.fillInputBoxIn("identity.surname", surname);
		super.fillInputBoxIn("identity.email", email);		
		super.fillInputBoxIn("accept", "true");
		super.clickOnSubmit("Sign up");
		super.checkCurrentPath("/master/welcome");
		super.checkNotLinkExists("Account");
	}
	
	protected void checkXpathExists(final String xpath) {
		By locator;

		locator = By.xpath(xpath);
		assert super.getDriver().exists(locator) : xpath + " was expected, but doesn't exist";
	}
	
	protected void checkXpathNotExists(final String xpath) {
		By locator;

		locator = By.xpath(xpath);
		assert !super.getDriver().exists(locator) : "XPath "+xpath+" was expected, but doesn't exist";
	}
	
	protected void checkXpathContains(final String xpath, final String content) {
		By locator;

		locator = By.xpath(xpath+"[normalize-space() = '"+content+"']");
		assert super.getDriver().exists(locator) : "XPath "+xpath+" with content "+content+" was expected, but doesn't exist";
	}
	
	protected void checkXpathInputValue(final String xpath, final String inputValue) {
		assert !StringHelper.isBlank(xpath);

		By inputLocator, optionLocator;
		String inputTag, inputType;
		WebElement inputBox;
		WebElement option;
		String contents, value;

		inputLocator = By.xpath(xpath);
		inputBox = super.getDriver().locateOne(inputLocator);
		inputTag = inputBox.getTagName();
		switch (inputTag) {
		case "textarea":
			contents = inputBox.getAttribute("value");
			break;
		case "input":
			inputType = inputBox.getAttribute("type");
			switch (inputType) {
			case "text":
			case "password":
			case "hidden":
				contents = inputBox.getAttribute("value");
				break;
			default:
				contents = null;
				assert false : String.format("Cannot check an input box of type '%s/%s'", inputTag, inputType);
			}
			break;
		case "select":
			optionLocator = By.xpath(String.format(xpath));
			assert super.getDriver().exists(optionLocator) : String.format("Cannot find selected option in input box '%s'", xpath);
			option = super.getDriver().locateOne(optionLocator);
			contents = option.getText();
			break;
		default:
			contents = null;
			assert false : String.format("Cannot check an input box of type '%s'", inputTag);
		}
		contents = (contents == null ? "" : contents.trim());
		value = (xpath != null ? inputValue.trim() : "");

		assert contents.equals(value) : String.format("Expected value '%s' in input box '%s', but '%s' was found", value, xpath, contents);
	}
}
