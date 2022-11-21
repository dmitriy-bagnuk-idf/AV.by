package pageObjects.av.dataProvider;

import org.testng.annotations.DataProvider;

public class dataForAVby {
    @DataProvider(name = "password data")
    public static Object[][] getPswData() {
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

    @DataProvider(name = "phone number data")
    public static Object[][] getPhoneNumberData() {
        return new Object[][]{
                {"!@#$"},
                {"   "},
                {"1q"},
                {"29123456"},
                {"74 73"},
                {"PhoneNumber"},
                {"number"},
                {"number123"},
                {"1q2w3e4"},
                {"QAtest12345"},
                {"QAtest12345QAtest12345"},
                {"Phone number 3848583"},
                {"!@#$!8747291-."}
        };
    }
}