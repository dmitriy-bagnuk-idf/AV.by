package pageObjects.av.dataProvider;

import org.testng.annotations.DataProvider;

public class dataForAVby {
    @DataProvider(name = "password data")
    public static Object[][] getData() {
        return new Object[][]{
                {""},
                {"1"},
                {"1q"},
                {"1q2w3"},
                {"1q2w3e4"},
                {"QAtest12345"},
                {"QAtest12345QAtest12345"}
        };
    }
}