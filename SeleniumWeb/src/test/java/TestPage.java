import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPage extends BaseTest {
    int returned = 0;
    String priceFromPage;
    String priceFromBasket;
    String selectedOption;
    @BeforeEach
    public void Btn() throws InterruptedException {
        FormPage.Btn();
        Thread.sleep(1000);
        FormPage.setMail("fomesi9858@cnxingye.com");
        FormPage.setPassword("Passw0rd12345");
        FormPage.loginRequestBtn();
        returned = FormPage.controlLoginPage();

        if(0 == returned) {
            Thread.sleep(3000);
            driver.quit(); //eğer giriş yaptıktan sonra yönlendirilen sayfa anasayfa değilse testi bitir.
        }
    }   //giris yapildi.
    @Test
    public void searchRequest() throws InterruptedException {
        FormPage.searchRequest("Bilgisayar");
        FormPage.secondPage();
        FormPage.randomItemSelect();
        FormPage.setAddToBasket();
        Thread.sleep(1000);
        priceFromPage = FormPage.getLowHighPriceFromPage();
        FormPage.goToBasket();
        priceFromBasket = FormPage.getPriceFromBasket();
        if(priceFromPage.compareToIgnoreCase(priceFromBasket) == 0){
            // 2 tane ekle
            FormPage.increaseItemInBasket(2);
            Thread.sleep(1000);
            selectedOption = FormPage.getItemAmountInBasket();
            if(selectedOption.compareToIgnoreCase(String.valueOf(2)) == 0){
                // sepetten sil, boş olmasına bak.
                System.out.println("Test passed");
                Thread.sleep(1000);
                FormPage.deleteBasket();
                Thread.sleep(1000);
                if(1 == FormPage.isEmptyBasket()){
                    //tüm testler tamamlandı.
                    //quit
                    Thread.sleep(5000);
                }
                else{
                    System.out.println("sepet boş değil,silinememiş. Hata!");
                }
            }
            else{
                System.out.println("seçilen index 2 değil, hata!");
            }
        }
        else{
            System.out.println("sepettekiyle, ürünün fiyatı aynı değil ! hata");
        }
    }
}
