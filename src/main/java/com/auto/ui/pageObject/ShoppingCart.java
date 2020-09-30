package com.auto.ui.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends PageBase {

    @FindBy(xpath ="//span[@class=\"ajax_cart_quantity unvisible\"]")
    WebElement numberOfProducts;
    @FindBy(xpath ="//a[@title=\"View my shopping cart\"]")
    WebElement cartHeader;
    @FindAll(@FindBy(xpath = "//dl[@class=\"products\"]//dt[contains(@data-id,'cart_block_product')]"))
    List<WebElement> productsInCart;
    List <String> prendas = new ArrayList<String>();
    @FindBy(xpath = "//td[@id='total_product']")
    WebElement totalShoppingCart;
    @FindAll(@FindBy(xpath="//table//tr[contains(@class,'cart_item')]"))
    List<WebElement> productsName;





    public ShoppingCart (WebDriver driver){
        super(driver);
    }

    public int getNumberProducts(){
        int numberOfCart = Integer.parseInt(numberOfProducts.getText());
        System.out.println("Number of products in the cart: "+numberOfCart);
        return numberOfCart;
    }

    public List <String> getsProductsNameInCart(){
         hoverElements(cartHeader);
         prendas.clear();
        for (WebElement product : productsInCart){
            prendas.add(product.findElement(By.xpath(".//a[@class='cart_block_product_name']")).getAttribute("title"));

            }
        System.out.println("item name" + prendas);
    return prendas;
    }

    public float getTotalInShoppingCart(){
        cartHeader.click();
        float totalCheckOut= Float.parseFloat(totalShoppingCart.getText().replace("$",""));
        return totalCheckOut;
    }

    public float deleteItemFromCart(String productName) throws InterruptedException {
        cartHeader.click();
        System.out.println("Producto a eliminar"+productName);
        float costTotalDeleted=0;

        for (WebElement product :productsName ){
            System.out.println("Productos del listado a eliminar"+product.getText());
            if (product.getText().contains(productName)){
                System.out.println("Prenda a eliminar "+product.getText());
                costTotalDeleted = Float.parseFloat(product.findElement(By.xpath(".//td[@class='cart_total']//span[contains(@class,'price') and contains(@id,'product_price_')]"))
                        .getText().replace("$",""));
                System.out.println("Cost total deleted "+costTotalDeleted);
                System.out.println("Product deleted in summary Products in deleteItemFromCart "+summaryProducts +productName);
                summaryProducts.remove(summaryProducts.indexOf(productName));
                product.findElement(By.xpath(".//i[@class='icon-trash']")).click();
                Thread.sleep(15000);

                System.out.println("Deleted Item "+productName);
            }
        }
        return costTotalDeleted;
    }

    public float deleteItemFromDropDown (String productName) throws InterruptedException {
        float costTotalDeleted=0;
        hoverElements(cartHeader);
        Thread.sleep(2000);

        for (WebElement product : productsInCart){
           if( product.findElement(By.xpath(".//a[@class='cart_block_product_name']")).getAttribute("title").contains(productName)) {
              System.out.println("Prenda a eliminar drop down " + product.getText());
              costTotalDeleted = Float.parseFloat(product.findElement(By.xpath(".//..//..//span[@class='price']"))
                       .getText().replace("$", ""));
               System.out.println("Cost total deleted " + costTotalDeleted);
               System.out.println("Product deleted in summary Products in deleteItemFromDropDown "+summaryProducts);
               product.findElement(By.xpath(".//..//..//..//span[@class='remove_link']")).click();
               summaryProducts.remove(summaryProducts.indexOf(productName));
               Thread.sleep(15000);
               System.out.println("Deleted Item " + productName);
               System.out.println("summaryProducts " + summaryProducts);

           }
        }



        return costTotalDeleted;
    }

}
