package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import utils.ApplicationManager;

public class BaseTest {
	
	private static final Logger log = LogManager.getLogger(BaseTest.class);
	
	@BeforeMethod
	public void initSetup() {
		//BasicConfigurator.configure();
		log.info("setting up Rest Assured environment");
		RestAssured.baseURI=ApplicationManager.getValueOfProperty("base-url");
		RestAssured.port=Integer.parseInt(ApplicationManager.getValueOfProperty("port"));
		
	}

}
