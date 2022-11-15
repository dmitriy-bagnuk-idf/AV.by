package testNgUtils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import propertyHelper.PropertyReader;

public class Listener implements ITestListener {
    @Override
    public void onStart(ITestContext context){
        //<условие> ? <если условие = true> : <если условие = false>
        String propertyName = context
                .getSuite()
                .getParameter("config") == null ? System.getProperty("config") : context.getSuite().getParameter("config");
        new PropertyReader(propertyName);
    }
    @Override
    public void onTestFailure(ITestResult result){
        Reporter.log("Ohh, this test failed => " + result.getName());
    }
    @Override
    public void onFinish(ITestContext context) {
        Reporter.log(context.getSuite().getXmlSuite().getTest());
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("Cool, this test passed => " + result.getName());
    }
}
