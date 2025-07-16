package WindowsAndFrames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class WindowHandle {
    public static void main(String[] args) {
        // Set ChromeDriver path
        

        WebDriver driver = new ChromeDriver();

        try {
            // 1. Open the browser and navigate to the URL
            driver.get("https://the-internet.herokuapp.com/windows");
            driver.manage().window().maximize();
            Thread.sleep(2000);

            // 2. Store original window handle
            String originalWindow = driver.getWindowHandle();

            // 3. Click "Click Here" to open a new window
            driver.findElement(By.linkText("Click Here")).click();
            Thread.sleep(2000);

            // 4. Switch to the new window
            Set<String> allWindows = driver.getWindowHandles();
            for (String window : allWindows) {
                if (!window.equals(originalWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }

            // 5. Verify that "New Window" text is present
            String pageText = driver.findElement(By.tagName("h3")).getText();
            if (pageText.equals("New Window")) {
                System.out.println("✅ New window text verified: " + pageText);
            } else {
                System.out.println("❌ Unexpected content in new window: " + pageText);
            }

            // 6. Close the new window
            driver.close();
            Thread.sleep(1000);

            // 7. Switch back to original window
            driver.switchTo().window(originalWindow);
            if (driver.getTitle().contains("The Internet")) {
                System.out.println("✅ Switched back to original window successfully.");
            } else {
                System.out.println("❌ Failed to switch back to original window.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 8. Close the browser
            driver.quit();
        }
    }
}

