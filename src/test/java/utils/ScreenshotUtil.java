package utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.login.functionality.LoginWithParametersFromTestNGxml;

public class ScreenshotUtil extends LoginWithParametersFromTestNGxml{

	public boolean takeScreenShot() throws IOException {
		Date currentDate = new Date();
		String screenshotFilename = currentDate.toString().replace(" ", "-").replace(":", "-");
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(".//screenshot//"+screenshotFilename+".png"));
		return true;
	}
}
