package com.auto.ui.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyAccount extends PageBase{

//Elements

    @FindAll(@FindBy(xpath="//table[@id='order-list']//tr"))
    List<WebElement> tableOrders;




//properties

    String orderNumberText;
    Float amountMyAccount;
    String referenceOrders= "//a[@class='color-myaccount']";

    public MyAccount(WebDriver driver) {
        super(driver);
    }

    public String validateOrderReference(String orderReference){
        for (WebElement orderRow : tableOrders){
            orderNumberText= (orderRow.findElement(By.xpath(referenceOrders)).getText());
            if (orderNumberText.contains(orderReference)){
                break;
            }
        }
        System.out.println("Order Number " +orderNumberText);
        return orderNumberText;
    }

    public float validateAmount(String orderReference){
        for (WebElement orderRow : tableOrders){
            orderNumberText= (orderRow.findElement(By.xpath(referenceOrders)).getText());
            if (orderNumberText.contains(orderReference)){
                amountMyAccount= Float.parseFloat(orderRow.findElement(By.xpath("//td[@class='history_price']")).getText().replace("$",""));
                //amountMyAccount= Float.parseFloat(orderRow.findElement(By.xpath("//table[@id='order-list']//tr//td[@class='history_price']")).getText().replace("$",""));
                break;
            }
        }
        System.out.println("Amount in Myaccount history " +amountMyAccount);
        return amountMyAccount;
    }



}
