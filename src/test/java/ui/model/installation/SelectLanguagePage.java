package ui.model.installation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class SelectLanguagePage extends BasePage {

    @FindBy(xpath = "//option[@lang='en']")
    private WebElement englishLanguage;

    @FindBy(id = "language-continue")
    private WebElement continueButton;

    public SelectLanguagePage(WebDriver driver) {
        super(driver);
    }

    public SelectLanguagePage selectEnglishLanguage() {
        englishLanguage.click();
        return this;
    }

    public AdminRegistrationPage clickContinue() {
        continueButton.click();
        return new AdminRegistrationPage(getDriver());
    }
}
