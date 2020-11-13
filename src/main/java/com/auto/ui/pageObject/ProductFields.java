package com.auto.ui.pageObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.xpath.XPath;
import java.util.List;
import org.json.simple.JSONObject;


public class ProductFields extends PageBase{

    // Elements
    @FindBy(xpath = "//div[@id='block_top_menu']/ul/li/a[@title='Dresses']")
    WebElement dressesLink;

    @FindBy(xpath ="//li[contains(@class ,'first-item-of-tablet-line')]//a[@class='product_img_link' and @title='Printed Dress']")
    WebElement printedDress;

    @FindAll(@FindBy(xpath = "//ul[@class='product_list grid row']/li"))
    List<WebElement> productsTypeDresses;

    @FindBy(xpath="//h1[@itemprop='name']")
    WebElement productName;

    @FindBy(xpath="//span[@id='our_price_display']")
    WebElement productValue;

    @FindBy(xpath = "//a[@id='send_friend_button']")
    WebElement productSend;

    WebElement product;



    JSONObject productDetails;




    public ProductFields(WebDriver driver) {
        super(driver);
    }

    public void clicProdDresses() throws InterruptedException{
       dressesLink.click();
       Thread.sleep(10000);
    }

    public String validateTitleField() throws InterruptedException{
        String productTitle= productName.getText();
        return productTitle;
    }


    public Float validatePriceField() throws InterruptedException{
        Float productPrice= Float.parseFloat(productValue.getText().replace("$",""));
        return productPrice;
    }



    public boolean validateSendField() throws InterruptedException{
        return productSend.isEnabled();
    }

    public JSONObject selectProduct(int productIndex) throws InterruptedException{
        product = productsTypeDresses.get(productIndex);
        String productTitle=getTitleProduct(product);
        super.hoverElements(product);
        Thread.sleep(2000);
        Float productPrice=getPriceProduct(product);
        boolean productAddToCompare= getAddToCompareProduct(product);
        boolean productWishList= getWishListProduct(product);
        System.out.println("productTitle " + productTitle +" and productPrice" + productPrice );
        Thread.sleep(1000);
        WebElement moreButton= product.findElement(By.xpath(".//a[@title='View']"));
        Thread.sleep(2000);
        moreButton.click();
        Thread.sleep(2000);

        productDetails = new JSONObject();
        productDetails.put("Index" , productIndex);
        productDetails.put("Title" , productTitle);
        productDetails.put("Price", productPrice);
        productDetails.put("Compare", productAddToCompare);
        productDetails.put("WishList", productWishList);
        return productDetails;
    }

    public String getTitleProduct(WebElement product) throws InterruptedException {
        String productTitle = product.findElement(By.xpath(".//a[@class='product_img_link']")).getAttribute("title");
        return productTitle;
    }

    public Float getPriceProduct(WebElement product) throws InterruptedException {
        float productPrice = Float.parseFloat(product.findElement(By.xpath(".//div[@class='content_price']//span")).getText().replace("$",""));
        return productPrice;
    }

    public Boolean getAddToCompareProduct(WebElement product) throws InterruptedException {
        boolean AddToCompareProduct = product.findElement(By.xpath(".//a[@class='add_to_compare']")).isDisplayed();
        return AddToCompareProduct;
    }

    public Boolean getWishListProduct(WebElement product) throws InterruptedException {
        boolean AddToCompareProduct = product.findElement(By.xpath(".//div[@class='wishlist']")).isDisplayed();
        return AddToCompareProduct;
    }


}




