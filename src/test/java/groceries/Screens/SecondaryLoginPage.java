package groceries.Screens;

import functional.tests.core.enums.PlatformType;
import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import org.openqa.selenium.By;

public class SecondaryLoginPage extends BasePage {
    private UIElement btnLogin;
    private UIElement btnSignup;

    public SecondaryLoginPage() {
        super();
    }

    public void login(String userName, String password) {
        this.insertUserName(userName);
        this.insertPassword(password);
        this.login();
    }

    public void login() {
        this.btnLogin.tap();
    }

    public void cancel() {
        this.find.byLocator(btnCancelLocator()).click();
        this.wait.waitForNotVisible(btnCancelLocator(), 2000, true);
    }

    public ErrorDialog ok() {
        this.log.info("Click on ok button in error dialog.");
        this.wait.waitForVisible(btnOkLocator(), 3, false).tap();

        ErrorDialog errorDialog = new ErrorDialog(this.context);

        if (this.settings.platform == PlatformType.iOS && this.settings.platformVersion < 10) {
            errorDialog.waitToAppear();
        }

        return errorDialog;
    }

    public void signUp() {
        this.log.info("Click on sign up button.");
        this.btnSignup.click();
        this.wait.waitForVisible(btnBackToLoginLocator(), 1000, true);
    }

    public void backToLogin() {
        this.btnBackToLogin().click();
        this.loaded();
    }

    public void showForgotPasswordDialog() {
        this.btnForgotPassword().tap();
        if (this.settings.platform == PlatformType.iOS && this.settings.platformVersion < 10) {
            this.wait.waitForVisible(this.locators.byText("Enter the email", false, false), 5, true);
        }
    }

    private void insertUserName(String text) {
        this.userField().setText(text);
        if (this.settings.platform == PlatformType.Android) {
            hideKeyboard();
        } else {
            try {
                this.wait.waitForVisible(this.locators.byText("Done", false, false)).click();
            } catch (Exception e) {
                this.app.hideKeyboard();
            }
        }
    }

    private void insertPassword(String text) {
        if ((this.settings.platform == PlatformType.Android) && this.settings.platformVersion >= 8.0) {
            this.passwordField().click();
        }
        this.passwordField().setText(text);
        if (this.settings.platform == PlatformType.Android) {
            hideKeyboard();
        } else if (this.settings.platform == PlatformType.iOS) {
            if (this.settings.platformVersion < 13.0) {
                this.find.byLocator(this.locators.byText("Done")).click();
            } else {
                this.find.byLocator(By.id("done")).click();
            }
        }
    }

    private UIElement userField() {
        if (this.settings.platform == PlatformType.iOS)
            return this.find.byLocator(userNameLocator());
        else {
            return this.find.elementsByLocator(userNameLocator()).get(0);
        }
    }

    private UIElement passwordField() {
        return this.find.byLocator(passwordLocator());
    }

    public boolean loaded() {
        if (this.getBtnSignup() != null) {
            this.log.info("Groceries secondary login page loaded.");
            this.refresh();

            return true;
        }

        return false;
    }

    private void refresh() {
        this.btnLogin = this.wait.waitForVisible(loginButtonLocator());
    }

    public ErrorDialog loginWithWrongName(String userName) {
        this.insertUserName(userName);
        this.btnLogin.click();

        return new ErrorDialog(this.context);
    }

    private UIElement getBtnSignup() {
        return this.btnSignup = this.wait.waitForVisible(signupButtonLocator(), 10, false);
    }

    private UIElement btnForgotPassword() {
        if (this.settings.platform == PlatformType.iOS && this.settings.platformVersion < 10) {
            return this.find.byLocator(By.id("Forgot password?"));
        }
        return this.find.byLocator(this.locators.byText("Forgot password?", false, false));
    }

    private UIElement btnBackToLogin() {
        return this.find.byLocator(btnBackToLoginLocator());
    }

    private By loginButtonLocator() {
        String getStartedString = "Login";

        return this.locators.byText(getStartedString);
    }

    private By signupButtonLocator() {
        String signUpHere = "Sign up here";
        if (this.settings.platform == PlatformType.iOS && this.settings.platformVersion < 10) {
            return By.id(signUpHere);
        } else {
            return this.locators.byText(signUpHere, false, false);
        }
    }

    private By userNameLocator() {
        return this.locators.editTextLocator();
    }

    private By passwordLocator() {
        if (this.settings.platform == PlatformType.iOS) {
            return By.className("UIASecureTextField");
        } else if (this.settings.platform == PlatformType.Android) {
            return By.xpath("//android.widget.EditText[2]");
        } else {
            return null;
        }
    }

    private By btnCancelLocator() {
        return this.locators.byText("Cancel", false, false);
    }

    private By btnOkLocator() {
        return this.locators.byText("Ok");
    }

    private By btnBackToLoginLocator() {
        String backToLogin = "Back to login";
        if (this.settings.platform == PlatformType.iOS && this.settings.platformVersion < 10) {
            return By.id(backToLogin);
        }
        return this.locators.byText(backToLogin, false, false);
    }
}

