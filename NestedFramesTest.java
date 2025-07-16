package WindowsAndFrames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NestedFramesTest {
    public static void main(String[] args) {
        
        WebDriver driver = new ChromeDriver();

        try {
            
            driver.get("http://the-internet.herokuapp.com/nested_frames");
            driver.manage().window().maximize();
            Thread.sleep(2000);

            driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='frame-top']")));

            int framesInTop = driver.findElements(By.tagName("frame")).size();
            if (framesInTop == 3) {
                System.out.println("✅ Verified 3 frames inside top frame.");
            } else {
                System.out.println("❌ Found " + framesInTop + " frames instead of 3.");
            }

            driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='frame-left']")));
            String leftText = driver.findElement(By.xpath("//body")).getText();
            System.out.println("LEFT frame text: " + leftText);
            if (leftText.equals("LEFT")) {
                System.out.println("✅ LEFT frame text verified.");
            } else {
                System.out.println("❌ LEFT frame text mismatch.");
            }

            driver.switchTo().parentFrame();

            driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='frame-middle']")));
            String middleText = driver.findElement(By.id("content")).getText();
            System.out.println("MIDDLE frame text: " + middleText);
            if (middleText.equals("MIDDLE")) {
                System.out.println("✅ MIDDLE frame text verified.");
            } else {
                System.out.println("❌ MIDDLE frame text mismatch.");
            }

            driver.switchTo().parentFrame();

            driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='frame-right']")));
            String rightText = driver.findElement(By.xpath("//body")).getText();
            System.out.println("RIGHT frame text: " + rightText);
            if (rightText.equals("RIGHT")) {
                System.out.println("✅ RIGHT frame text verified.");
            } else {
                System.out.println("❌ RIGHT frame text mismatch.");
            }
            driver.switchTo().defaultContent();

            driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='frame-bottom']")));
            String bottomText = driver.findElement(By.xpath("//body")).getText();
            System.out.println("BOTTOM frame text: " + bottomText);
            if (bottomText.equals("BOTTOM")) {
                System.out.println("✅ BOTTOM frame text verified.");
            } else {
                System.out.println("❌ BOTTOM frame text mismatch.");
            }
            driver.switchTo().defaultContent();
            String title = driver.getTitle();
            if (title.equals("Frames")) {
                System.out.println("✅ Page title verified: " + title);
            } else {
                System.out.println("❌ Page title mismatch. Found: " + title);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

