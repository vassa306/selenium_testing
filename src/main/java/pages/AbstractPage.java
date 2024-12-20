package pages;

import org.openqa.selenium.WebDriver;
import static org.openqa.selenium.support.PageFactory.initElements;

public class AbstractPage {

        private WebDriver driver;

        public AbstractPage(WebDriver driver) {
            initElements(driver, this);
            this.driver = driver;
        }

        protected WebDriver getDriver() {
            return driver;
        }
}




