package com.auto.ui.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import javax.swing.text.html.HTMLDocument;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Producto extends PageBase {

    //Elements
@FindBy(xpath = "//*[@class='homefeatured']")
    WebElement tabPopular;
@FindBy(xpath = "//*[@class='blockbestsellers']")
    WebElement tabBestseller;
@FindAll(@FindBy(xpath = "//ul[@id='homefeatured']//li[contains(@class,'ajax_block_product')]"))
    List<WebElement> productsPopular;
@FindAll(@FindBy(xpath = "//ul[@id='blockbestsellers']//li[contains(@class,'ajax_block_product')]"))
    List<WebElement> productsBestSeller;
@FindBy(xpath ="//span[contains(@class,'continue')]")
WebElement btnContinue;

@FindBy(xpath = "//span[@id='layer_cart_product_quantity']")
WebElement quantity;



    public float Total_price =0;
    @FindBy(xpath = "//span[@id='layer_cart_product_price']")
    WebElement price;





    //Properties
        private WebElement product;

        public Producto (WebDriver driver){
            super(driver);
        }

        //Methods or Actions
        public  void selectTab(String tabName) {
            if (tabName=="popular") {
                tabPopular.click();
            }
            else {
                tabBestseller.click();
            }

        }

        public WebElement  selectAnyProduct(String tabName,int productIndex){

            if (tabName=="popular"){
               product = productsPopular.get(productIndex);
                //select any product - input Product name & price -- to do
            }else {
                product = productsBestSeller.get(productIndex);
            }
            //over= new Actions(driver);
            super.hoverElements(product);
            return product;
        }

    public WebElement  selectAnyProduct(String tabName, String productName){

        if (tabName=="popular"){
            for (WebElement popular : productsPopular){

                System.out.println("lo que tiene producto" + popular.getText());
                if (popular.getText().contains(productName)){

                    product=popular;
                    break;
                }
            }

        }else {
            for (WebElement bestSellers : productsBestSeller){
                if (bestSellers.getText().contains(productName)){
                    product= bestSellers;
                    break;
                }
            }

        }
        //over= new Actions(driver);
        super.hoverElements(product);
        return product;
    }

        public void addToCart(WebElement product) throws InterruptedException {
            WebElement addToCartButton= product.findElement(By.xpath("div//a[contains(@class,'ajax_add_to_cart_button')]"));
            addToCartButton.click();
            Thread.sleep(2500);

        }

        public void continueShopping() throws InterruptedException {
            btnContinue.click();
            Thread.sleep(2500);
        }

        public void addToCart_continueShopping(String tabName,int productIndex) throws InterruptedException {
            selectTab(tabName);
            WebElement product=selectAnyProduct(tabName,productIndex);
            addToCart(product);
            if(summaryProducts.indexOf(getNamebyIndex(product))==-1) {
                summaryProducts.add(getNamebyIndex(product));
            }
            System.out.println("Summary productos new list"+summaryProducts);
            Total_price += getPrice(product);
            continueShopping();
        }


    public String getNamebyIndex(WebElement product) throws InterruptedException {
        return (product.findElement(By.xpath(".//a[@class='product-name']")).getAttribute("title"));

    }

        public void addToCart_continueShopping(String tabName, String productName) throws InterruptedException {
        selectTab(tabName);
        WebElement product=selectAnyProduct(tabName,productName);
        addToCart(product);
        if(summaryProducts.indexOf(productName)==-1) {
           summaryProducts.add(productName);
        }
        System.out.println("Summary productos new list"+summaryProducts);
        Total_price += getPrice(product);
        continueShopping();

    }

    public float getPrice(WebElement product) throws InterruptedException {
        float price = Float.parseFloat(this.price.getText().replace("$",""));
        float unitPrice = price / Float.parseFloat(quantity.getText());
        System.out.println("Precio unitario del product"+unitPrice);
        return unitPrice;
        }



    }

