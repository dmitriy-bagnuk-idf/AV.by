import org.testng.annotations.Test;
import pageObjects.av.HomePage;
import pageObjects.baseObjects.BaseTest;

public class test extends BaseTest {
    @Test
    public void testOpen() {
        get(HomePage.class)
                .open();
    }
}
