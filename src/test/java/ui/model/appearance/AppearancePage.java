package ui.model.appearance;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class AppearancePage extends BasePage {

    @FindBy(css = "#twentytwentyfour-name > span")
    private WebElement twentyTwentyFourTheme;

    @FindBy(css = "#twentytwentytwo-name > span")
    private WebElement twentyTwentyTwoTheme;

    @FindBy(id = "twentytwentytwo-name")
    private WebElement twentyTwentyTwoThemeName;

    @FindBy(xpath = "//a[@aria-label='Activate Twenty Twenty-Two']")
    private WebElement twentyTwentyTwoThemeActivateButton;

    @FindBy(css = "#message2 > p")
    private WebElement newThemeActivatedMessage;

    public AppearancePage(WebDriver driver) {
        super(driver);
    }

    public String getTwentyTwentyFourThemeActiveStatus() {
        return twentyTwentyFourTheme.getText();
    }

    public AppearancePage clickActivateTwentyTwentyTwoTheme() {
        twentyTwentyTwoThemeActivateButton.click();

        return new AppearancePage(getDriver());
    }

    public AppearancePage hoverOntwentyTwentyTwoTheme() {
        new Actions(getDriver())
                .moveToElement(twentyTwentyTwoThemeName)
                .perform();

        return this;
    }

    public String getNewThemeActivatedMessage() {
        return newThemeActivatedMessage.getText();
    }

    public String getTwentyTwentyTwoThemeActiveStatus() {
        return twentyTwentyTwoTheme.getText();
    }
}
