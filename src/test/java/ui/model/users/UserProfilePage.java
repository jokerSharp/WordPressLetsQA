package ui.model.users;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.model.base.BasePage;

public class UserProfilePage extends BasePage {

    @FindBy(id = "admin_color_light")
    private WebElement colorLightRadioButton;

    @FindBy(css = ".color-option.selected > table > tbody > tr > td:nth-child(1)")
    private WebElement firstColorOfLightScheme;

    @FindBy(id = "adminmenuwrap")
    private WebElement sideMenuBackground;

    public UserProfilePage(WebDriver driver) {
        super(driver);
    }

    public UserProfilePage selectColorLightScheme() {
        colorLightRadioButton.click();
        return this;
    }

    public String getFirstColorOfLightScheme() {
        return firstColorOfLightScheme.getCssValue("background-color");
    }

    public String getBackgroundColor() {
        return sideMenuBackground.getCssValue("background-color");
    }



}
