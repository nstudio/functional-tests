package uitests.Tests.ListPicker;

import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import uitests.Screens.HomePageExtended;
import uitests.Tests.UIBaseTests;

public class ListPickerTest extends UIBaseTests {
    private HomePageExtended homePageExtended;

    @BeforeClass(alwaysRun = true)
    public void beforeLayoutsTestsClass() {
        this.homePageExtended = new HomePageExtended("list-picker", this.context);
    }

    @Test(groups = {"android", "ios"})
    public void listPicker_issue_2895() throws Exception {
        this.homePageExtended.navigateTo("issue_2895");
        this.device.rotate(ScreenOrientation.PORTRAIT);
        this.compareScreens(5);
        this.device.rotate(ScreenOrientation.LANDSCAPE);
        this.assertImagesResults();
    }
}


