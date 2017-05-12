package uitests.Tests.ListView;

import functional.tests.core.enums.PlatformType;
import functional.tests.core.mobile.element.UIElement;
import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import uitests.Screens.HomePageExtended;
import uitests.Tests.UIBaseTests;

public class ListViewTest extends UIBaseTests {
    private HomePageExtended homePageExtended;

    @BeforeClass(alwaysRun = true)
    public void beforeLayoutsTestsClass() {
        this.homePageExtended = new HomePageExtended("list-view", this.context);
    }

    @Test(groups = {"android", "ios"})
    public void listViewItemsTemplate_01() throws Exception {
        if (this.settings.platform == PlatformType.iOS && this.settings.platformVersion < 10) {
            this.log.warn("This test is not executing for iOS 9");
            return;
        }
        this.homePageExtended.navigateTo("list-view-templates");
        UIElement scroll = this.homePageExtended.wait.waitForVisible(this.locators.byText("SCROLL", false, false), 5, true);
        this.compareScreens(10, 0.30);

        scroll.tap();
        this.homePageExtended.wait.waitForNotVisible(this.locators.byText("item99", false, false), 3, true);
        this.compareScreens(10);

        scroll.tap();
        this.homePageExtended.wait.waitForNotVisible(this.locators.byText("item1", false, false), 3, true);
        this.compareScreens(10);

        this.assertImagesResults();
    }

    @Test(groups = {"android", "ios"})
    public void listViewRotate() throws Exception {
        if (this.settings.platform == PlatformType.iOS && this.settings.platformVersion < 10) {
            this.log.warn("This test is not executing for iOS 9");
            return;
        }
        this.homePageExtended.navigateTo("images-template");
        this.compareScreens(5);
        this.device.rotate(ScreenOrientation.LANDSCAPE);
        this.compareScreens(5);
        this.device.rotate(ScreenOrientation.PORTRAIT);
        this.compareScreens(5);
        this.assertImagesResults();
    }
}
