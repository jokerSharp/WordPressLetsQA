package ui.model.users;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.model.base.BasePage;

public class UserProfilePage extends BasePage {

    @FindBy(id = "admin_color_light")
    private WebElement colorLightSchemeRadioButton;

    @FindBy(id = "admin_color_fresh")
    private WebElement colorDefaultSchemeRadioButton;

    @FindBy(css = ".color-option.selected > table > tbody > tr > td:nth-child(1)")
    private WebElement firstColorOfLightScheme;

    @FindBy(css = ".color-option.selected > table > tbody > tr > td:nth-child(1)")
    private WebElement firstColorOfDefaultScheme;

    @FindBy(id = "adminmenuwrap")
    private WebElement sideMenuBackground;

    public UserProfilePage(WebDriver driver) {
        super(driver);
    }

    public UserProfilePage selectColorLightScheme() {
        colorLightSchemeRadioButton.click();
        return this;
    }

    public UserProfilePage selectColorDefaultScheme() {
        getWait2().until(ExpectedConditions.visibilityOf(colorDefaultSchemeRadioButton));
        colorDefaultSchemeRadioButton.click();
        return this;
    }

    public String getFirstColorOfLightScheme() {
        return firstColorOfLightScheme.getCssValue("background-color");
    }

    public String getFirstColorOfDefaultScheme() {
        return firstColorOfDefaultScheme.getCssValue("background-color");
    }

    public String getBackgroundColor() {
        return sideMenuBackground.getCssValue("background-color");
    }



}
