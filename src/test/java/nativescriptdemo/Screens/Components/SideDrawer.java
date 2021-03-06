package nativescriptdemo.Screens.Components;

import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.basetest.MobileContext;
import functional.tests.core.mobile.element.UIElement;
import functional.tests.core.enums.SwipeElementDirection;
import org.testng.Assert;

public class SideDrawer extends BasePage {

    public SideDrawer(MobileContext context) {
        super(context);
    }

    private UIElement home() {
        return this.wait.waitForVisible(this.locators.byText("Home"));
    }

    private UIElement about() {
        return this.wait.waitForVisible(this.locators.byText("About"));
    }

    private UIElement gettingStarted() {
        return this.wait.waitForVisible(this.locators.byText("Getting started", false, false));
    }

    private UIElement source() {
        return this.find.byText("Source");
    }

    private UIElement verifiedPlugins() {
        return this.wait.waitForVisible(this.locators.byText("Verified plugins"));
    }

    private UIElement documentation() {
        return this.wait.waitForVisible(this.locators.byText("Documentation"));
    }

    private UIElement productPage() {
        return this.wait.waitForVisible(this.locators.byText("Product page"));
    }

    private UIElement codeSamples() {
        UIElement element = this.context.gestures.swipeInWindowToElement(SwipeElementDirection.DOWN, this.locators.byText("Code samples"), 1, 500, 500);
        return element;
    }

    public void loaded() {
        UIElement element = this.wait.waitForVisible(this.locators.byText("Getting started"), 3, true);
        if (element != null) {
            this.log.info("Side Drawer loaded.");
        } else {
            Assert.fail("Side Drawer NOT loaded!");
        }
    }

    public void unloaded() {
        Boolean isVisible = this.wait.waitForNotVisible(this.locators.byText("Getting started"), 3, true);
        if (isVisible) {
            Assert.fail("Side Drawer NOT unloaded! It is still visible.");
        } else {
            this.log.info("Side Drawer unloaded. It is not visible.");
        }
    }

    public void tapHome() {
        this.home().tap();
    }

    public void tapAbout() {
        this.about().tap();
    }

    public void tapGettingStarted() {
        this.gettingStarted().tap();
    }

    public void tapSource() {
        this.source().tap();
    }

    public void tapVerifiedPlugins() {
        this.verifiedPlugins().tap();
    }

    public void tapDocumentation() {
        this.documentation().tap();
    }

    public void tapProductPage() {
        this.productPage().tap();
    }

    public void tapCodeSamples() {
        this.codeSamples().tap();
    }
}
