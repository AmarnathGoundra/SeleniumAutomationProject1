package MiniProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

public static void main(String[] args) throws InterruptedException {

WebDriver driver = new ChromeDriver();
String url = "https://www.coursera.org/";

driver.get(url);

driver.manage().window().maximize(); //maximize window

//STATEMENT 1

WebElement search = driver.findElement(By.xpath("//input[@class='react-autosuggest__input']"));
search.sendKeys("Web Development");
search.sendKeys(Keys.ENTER);

driver.findElement(By.xpath("//div[@data-testid='English-false']")).click();
driver.findElement(By.xpath("//div[@data-testid='Beginner-false']")).click();
Thread.sleep(5000);

String[] courseName = new String[2];
String[] rating = new String[2];
courseName[0] = driver.findElement(By.xpath("//li[@class=\"cds-9 css-0 cds-11 cds-grid-item cds-56 cds-64 cds-76 cds-90\"][1]/div/div/div/div/div/div[2]/div/div[2]/a/h3")).getText();
rating[0] = driver.findElement(By.xpath("//li[@class=\"cds-9 css-0 cds-11 cds-grid-item cds-56 cds-64 cds-76 cds-90\"][1]/div/div/div/div/div/div[2]/div[3]/div[2]/div/div/p[1]")).getText();

courseName[1] = driver.findElement(By.xpath("//li[@class=\"cds-9 css-0 cds-11 cds-grid-item cds-56 cds-64 cds-76 cds-90\"][2]/div/div/div/div/div/div[2]/div/div[2]/a/h3")).getText();
rating[1] = driver.findElement(By.xpath("//li[@class=\"cds-9 css-0 cds-11 cds-grid-item cds-56 cds-64 cds-76 cds-90\"][2]/div/div/div/div/div/div[2]/div[3]/div[2]/div/div/p[1]")).getText();

driver.findElement(By.xpath("//li[@class='cds-9 css-0 cds-11 cds-grid-item cds-56 cds-64 cds-76 cds-90'][1]")).click();
Thread.sleep(10000);

driver.findElement(By.xpath("//li[@class='cds-9 css-0 cds-11 cds-grid-item cds-56 cds-64 cds-76 cds-90'][2]")).click();
Thread.sleep(10000);

ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

int win = 1;
while(win<=2) {
driver.switchTo().window(tabs.get(win));
String time = driver.findElement(By.xpath("//div[@class='cds-9 css-8x8ecu cds-11 cds-grid-item cds-56 cds-76']/div/div/section/div[2]/div[3]/div")).getText();
//System.out.println(hours);
int months = 0;
int hours = 0;
Pattern pm = Pattern.compile("(\\d+) months");
Matcher mm = pm.matcher(time);
if(mm.find()) {
months = Integer.parseInt(mm.group(1));
}
Pattern ph = Pattern.compile("(\\d+) hours");
Matcher mh = ph.matcher(time);
if(mh.find()) {
hours = Integer.parseInt(mh.group(1));
}
System.out.println("Course "+win);
System.out.println("Course Name : "+courseName[win-1]);
System.out.println("Rating : "+rating[win-1]);
System.out.println("Total Learning Hours : "+(4*months*hours));
System.out.println();
win++;
}

driver.switchTo().window(tabs.get(0));

String searchData = driver.findElement(By.xpath("//input[@class='react-autosuggest__input']")).getAttribute("value");
//System.out.println(searchData);

WebElement searchNext = driver.findElement(By.xpath("//input[@class='react-autosuggest__input']"));
for(int i = 0; i<searchData.length(); i++) {
searchNext.sendKeys(Keys.BACK_SPACE);
}

//STATEMENT 2

//WebElement searchNext = driver.findElement(By.xpath("//input[@class='react-autosuggest__input']"));     // Use only if you are running STATEMENT 2 only.

searchNext.sendKeys("Language Learning");
searchNext.sendKeys(Keys.ENTER);
Thread.sleep(10000);

driver.findElement(By.xpath("//button[@aria-label='Show more Language options']")).click();

List<WebElement> languages = driver.findElements(By.xpath("//*[@id='checkbox-group']/div"));
//System.out.println(languages.size());
System.out.println("\n\nLanguages (Count)");
for(int i = 0; i<languages.size(); i++) {
String lang = languages.get(i).getText();
System.out.println(lang);
}

driver.findElement(By.xpath("//button[@class='cds-149 cds-button-disableElevation cds-button-ghost css-1s96oj']")).click();

List<WebElement> levels = driver.findElements(By.xpath("//div[@data-testid='search-filter-group-Level']/div/div"));
//System.out.println(levels.size());
System.out.println("\n\nLevels (Count)");
for(int i = 0; i<levels.size(); i++) {
String lev = levels.get(i).getText();
System.out.println(lev);
}

//STATEMENT 3

//WebElement searchForm = driver.findElement(By.xpath("//a[@data-click-key='front_page.front_page_story.click.navigation_meta_nav_Business']")); //For directly opening from home page.
WebElement searchForm = driver.findElement(By.xpath("//a[@data-click-key='search.search.click.navigation_meta_nav_Business']"));
searchForm.click();

driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Amarnath");
driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Gowndra");
driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("amarnath");
driver.findElement(By.xpath("//input[@id='Phone']")).sendKeys("9911991199");
driver.findElement(By.xpath("//input[@id='Title']")).sendKeys("Selenium Automation Tester");

WebElement select = driver.findElement(By.xpath("//select[@id='Self_reported_employees_to_buy_for__c']"));
Select s = new Select(select);
s.selectByIndex(2);

driver.findElement(By.xpath("//input[@id='mktoCheckbox_92958_0']")).click();

WebElement selectCountry = driver.findElement(By.xpath("//select[@id='Country']"));
Select s1 = new Select(selectCountry);
s1.selectByValue("India");

WebElement selectState = driver.findElement(By.xpath("//select[@id='State']"));
Select s11 = new Select(selectState);
s11.selectByValue("Telangana");

WebElement selectD = driver.findElement(By.xpath("//select[@id='What_the_lead_asked_for_on_the_website__c']"));
Select s2 = new Select(selectD);
s2.selectByIndex(2);

WebElement submit = driver.findElement(By.xpath("//button[@class='mktoButton']"));
submit.submit();

boolean submitted = driver.findElement(By.xpath("//span[text()='example@yourdomain.com']")).isDisplayed();
if(submitted) {
WebElement error = driver.findElement(By.xpath("//div[@id='ValidMsgEmail']"));
String err = error.getText();
System.out.println("\n\nForm Submission Failed.\nError: ");
System.out.println(err);
}
else {
System.out.println("Successfully form submitted.");
}

Thread.sleep(10000);
driver.quit();
}
}
