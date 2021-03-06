package sdkexamples.Tests;

import functional.tests.core.mobile.element.UIElement;
import functional.tests.core.enums.PlatformType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sdkexamples.SdkBaseTest;

public class SdkFpsMeterTests extends SdkBaseTest {

    protected String page = "FPS Meter";

    @Override
    protected String subMainPage() {
        return this.page;
    }

    @DataProvider(name = "example")
    public Object[][] data() {
        return new Object[][]{
                {"Usage"}
        };
    }

    @Test(dataProvider = "example")
    public void sdkFpsMeterTest(String example) throws Exception {
        if (this.settings.platform == PlatformType.Android) {
            UIElement btn = this.wait
                    .forVisibleElements(this.locators.textViewLocator(), 30, false)
                    .get(1);
            this.mainPage.navigateTo(btn);
        } else {
            this.mainPage.navigateTo(example);
        }
    }
}
