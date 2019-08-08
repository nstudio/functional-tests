package templates.healthsurvey.pages;

import com.google.common.collect.ImmutableMap;
import functional.tests.core.enums.PlatformType;
import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import functional.tests.core.mobile.find.Wait;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.Assert;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LoginPage extends BasePage {

    public LoginPage() {
        super();
        UIElement home = this.wait.waitForVisible(this.locators.findByTextLocator("Login", true));
        Assert.assertNotNull(home, "Login page not loaded!");
        this.log.info("Login page loaded.");
    }


    public void login(String user, String pass) {
        this.email().setText(user);

        if (this.settings.platform == PlatformType.Android) {
            if (this.settings.platformVersion > 6.0) {
                Wait.sleep(500);
                ((AndroidDriver) this.client.driver).pressKey(new KeyEvent(AndroidKey.ENTER));
                Wait.sleep(500);
                ((AndroidDriver) this.client.driver).pressKey(new KeyEvent(AndroidKey.ENTER));
                Wait.sleep(500);
            }
            this.sendEnterAndTypeText(pass);
            this.hideKeyboard();
        } else {
            this.password().setText(pass);
            this.find.byText("Done").click();
        }
        this.loginButton().click();
    }

    public RegisterPage register() {
        UIElement registerButton = this.wait.waitForVisible(this.locators.findByTextLocator("Register", true));
        registerButton.click();
        return new RegisterPage();
    }

    private UIElement email() {
        return find.byText("example@progress.com");
    }

    private UIElement password() {
        return find.byText("Enter password");
    }

    private UIElement loginButton() {
        if (this.settings.platform == PlatformType.Android) {
            return this.find.byLocator(this.locators.findByTextLocator("android.widget.Button", "Login", true, false));
        } else {
            List<UIElement> list = this.find.elementsByLocator(this.locators.byText("Login"));
            return list.get(list.size() - 1);
        }
    }

    private void sendEnterAndTypeText(String text) {
        if (this.settings.platform == PlatformType.Android) {
            Wait.sleep(20);
            ((AndroidDriver) this.client.driver).pressKey(new KeyEvent(AndroidKey.ENTER));
            Wait.sleep(20);

            List<String> args = Arrays.asList(
                    "text",
                    "\"" + text + "\""
            );
            Map<String, Object> sendTextCmd = ImmutableMap.of(
                    "command", "input",
                    "args", args
            );
            this.client.driver.executeScript("mobile: shell", sendTextCmd);

            Wait.sleep(20);
        } else {
            throw new NotImplementedException();
        }
    }
}
