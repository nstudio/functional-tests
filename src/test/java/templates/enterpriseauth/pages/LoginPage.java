package templates.enterpriseauth.pages;

import functional.tests.core.enums.PlatformType;
import functional.tests.core.enums.SwipeElementDirection;
import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import functional.tests.core.mobile.find.Wait;
import org.openqa.selenium.By;
import org.testng.Assert;


public class LoginPage extends BasePage {
    public LoginPage() {
        super();
        Assert.assertNotNull(loginButton(), "Login page not loaded!");
        this.log.info("Login page loaded.");
    }

    public void login(String userName, String userPass) {
        loginButton().click();
        UIElement userEditText = this.find.byLocator(this.locators.editTextLocator());
        UIElement nextButton = this.find.byText("Next");
        Wait.sleep(1000);
        this.gestures.swipeInWindow(SwipeElementDirection.DOWN, 1000);
        Wait.sleep(1000);
        userEditText.sendKeys(userName);
        Wait.sleep(1000);
        this.app.hideKeyboard();
        nextButton.click();
        this.app.hideKeyboard();
        Wait.sleep(1000);
        if (this.settings.platform == PlatformType.Android) {
            this.find.byLocator(this.locators.editTextLocator()).sendKeys(userPass);
        } else {
            this.find.byLocator(By.className("XCUIElementTypeSecureTextField")).sendKeys(userPass);
        }
        this.app.hideKeyboard();
        Wait.sleep(1000);
        this.find.byText("Sign in").click();
        this.find.byText("Yes", false, this.settings.defaultTimeout).click();
    }

    private UIElement loginButton() {
        return this.find.byText("Log in");
    }
}
