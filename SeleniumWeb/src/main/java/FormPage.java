import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class FormPage {

    private final WebDriver driver;
    private final By mail = By.id("L-UserNameField");
    private final By password = By.name("sifre");
    private final By loginRequestBtn = By.id("gg-login-enter");
    private final By signBtn = By.cssSelector("#main-header > div:nth-child(3) > div > div > div > div.sc-1nx8ums-0.fQSWwJ > div > div:nth-child(1) > div > div.gekhq4-4.egoSnI");
    private final By signBtn2 = By.cssSelector("#main-header > div:nth-child(3) > div > div > div > div.sc-1nx8ums-0.fQSWwJ > div > div.gekhq4-5.hJhHqb > div.sc-3wdx43-0.bAxXdY > div > div > div > a");
    private final By searchBox = By.name("k");
    private final By searchBtn = By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[2]/button");
    private final By secondPage = By.xpath("//*[@id=\"best-match-right\"]/div[5]/ul/li[7]/a");
    private final By addToBasket = By.xpath("//*[@id=\"sp-addbasket-button\"]/form");
    private final By lowPrice = By.id("sp-price-lowPrice");
    private final By highPrice = By.id("sp-price-highPrice");
    private final By basketBtn = By.className("header-cart-hidden-link");
    private final By basketPrice = By.className("total-price");
    private final By deleteItem = By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div/div/div[6]/div[2]/div[2]/div[1]/div[3]/div/div[2]/div/a/i");
    private final By emptyBasket = By.id("empty-cart-container");
    WebDriverWait wait;

    public FormPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

  public void Btn() throws InterruptedException {
        driver.manage().window().maximize();
        Thread.sleep(3000);
        WebElement BtnSpace = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(signBtn)));
        BtnSpace.click();
        Thread.sleep(3000);
        WebElement BtnSpace2 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(signBtn2)));
        BtnSpace2.click();
      }
   public void setMail(String mailAsString){
        WebElement mailSpace = driver.findElement(mail);
        mailSpace.click();
        mailSpace.sendKeys(mailAsString);
    }
    public void setPassword(String passwordAsString){
        WebElement passwordSpace = driver.findElement(password);
        passwordSpace.click();
        passwordSpace.sendKeys(passwordAsString);
    }
    public void loginRequestBtn(){
        WebElement loginClick = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(loginRequestBtn)));
        loginClick.click();
    }

    public int controlLoginPage(){
        String actualUrl="https://www.gittigidiyor.com/";
        String expectedUrl= driver.getCurrentUrl();

        if(actualUrl.equalsIgnoreCase(expectedUrl)) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public void searchRequest(String searchAsString){
        driver.manage().window().maximize();
        WebElement searchSpace = driver.findElement(searchBox);
        searchSpace.click();
        searchSpace.sendKeys(searchAsString); //kutuya yazildi.

        WebElement searchClick = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(searchBtn)));
        searchClick.click(); // butona tıklandı.

    }

    public void secondPage() {
        WebElement secondPageClick = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(secondPage)));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", secondPageClick);
    }

    public void randomItemSelect() {
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.className("cell-border-css"))));
        Random rand = new Random();
        int randomProduct = rand.nextInt(elements.size());
        elements.get(randomProduct).click();
    }

    public void setAddToBasket() {
        WebElement addToBasketClick = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(addToBasket)));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", addToBasketClick);
    }

    public String getLowHighPriceFromPage(){
        WebElement lowPriceSpace = driver.findElement(lowPrice);
        if(lowPriceSpace.isDisplayed()) {
            return lowPriceSpace.getText();
        }
        else{
            WebElement highPriceSpace = driver.findElement(highPrice);
            return highPriceSpace.getText();
        }
    }

    public void goToBasket(){
        WebElement goToBasketClick = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(basketBtn)));
        goToBasketClick.click();
    }

    public String getPriceFromBasket(){
        WebElement basketPriceSpace = wait.until(ExpectedConditions.visibilityOf(driver.findElement(basketPrice)));
        return basketPriceSpace.getText();
    }

    public void increaseItemInBasket(int itemCount){
        WebElement element = driver.findElement( By.tagName("select"));
        Select amount = new Select(element);
        amount.selectByIndex(itemCount-1);
    }

    public String getItemAmountInBasket(){
        WebElement element = driver.findElement( By.tagName("select") );
        Select item = new Select(element);
        List<WebElement> selectedOptions = item.getAllSelectedOptions();
        System.out.println("option selected: --> " + selectedOptions.get(0).getText());
        return selectedOptions.get(0).getText();
    }

    public void deleteBasket(){
        WebElement goToBasketClick = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(deleteItem)));
        goToBasketClick.click();
    }

    public int isEmptyBasket(){
        WebElement emptySpace = driver.findElement(emptyBasket);
        if(emptySpace.isDisplayed()) {
            return 1; //sepet boş
        }
        else{
            return 0; // sepet boş değil, hata!
        }
    }

}
