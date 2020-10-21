package com.auto.ui.pageObject;

import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.w3c.dom.events.EventException;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CheckOut extends ShoppingCart {

//Elements
    @FindBy(xpath="//a[@class='button btn btn-default standard-checkout button-medium']")
    WebElement checkOutButton;
    @FindBy(xpath="//button[@type='submit' and contains(@name,'process')]")
    WebElement checkOutButtonAfterSignIn;
    @FindBy(xpath="//ul[@id='address_delivery']//li[@class='address_firstname address_lastname']")
    WebElement nameDeliveryAddress;
    @FindBy(xpath="//ul[@id='address_delivery']//li[@class='address_address1 address_address2']")
    WebElement addressdeliveryAddress;
    @FindBy(xpath="//ul[@id='address_delivery']//li[@class='address_city address_state_name address_postcode']")
    WebElement citydeliveryAddress;
    @FindBy(xpath="//ul[@id='address_delivery']//li[@class='address_country_name']")
    WebElement countrydeliveryAddress;
    @FindBy(xpath="//ul[@id='address_delivery']//li[@class='address_phone_mobile']")
    WebElement mobiledeliveryAddress;
    @FindBy(xpath="//input[@class='delivery_option_radio']")
    WebElement radioDeliveryStatus;
    @FindBy(xpath="//input[@type='checkbox']")
    WebElement checkTerms;
    @FindBy(xpath="//p[@class='fancybox-error']")
    WebElement messageTermsIsPresented;
    @FindBy(xpath="//p[@class='fancybox-error']")
    WebElement validateMessageTerms;
    @FindBy(xpath="//div[@class='fancybox-skin']//a[@title='Close']")
    WebElement messageTermsClose;
    @FindBy(xpath="//td[@id='total_shipping']")
    WebElement valueShipping;
    @FindBy(xpath="//span[@id='total_price']")
    WebElement CheckoutValueTotal;
    @FindBy(xpath="//a[@class='bankwire']")
    WebElement paymentMethod;
    @FindBy(xpath="//span[@id='amount']")
    WebElement valueAmount;
    @FindBy(xpath="//button[@class='button btn btn-default button-medium']")
    WebElement confirmMyOrder;
    @FindBy(xpath="//span[@class='price']")
    WebElement valueAmountConfirm;
    @FindBy(xpath="//div[@class='box']")
    WebElement orderReference;
    @FindBy(xpath="//a[@class='button-exclusive btn btn-default']")
    WebElement backToOrders;




//properties

String lastOrderReference;
Float totalValue;


    public void clicCheckOut(boolean proceed ){
        if (!proceed) {
            checkOutButton.click(); }
        else {checkOutButtonAfterSignIn.click();}
    }


    public CheckOut(WebDriver driver){
        super(driver);


    }

    public JsonObject getDeliveryAddress(){
        JsonObject addressDelivery = new JsonObject();
        addressDelivery.addProperty("nameUser" , nameDeliveryAddress.getText());
        addressDelivery.addProperty("addressUser", addressdeliveryAddress.getText());
        addressDelivery.addProperty("cityUser", citydeliveryAddress.getText());
        addressDelivery.addProperty("countryUser", countrydeliveryAddress.getText());
        addressDelivery.addProperty("mobileUser", mobiledeliveryAddress.getText());


        return addressDelivery;
    }

    public boolean getDeliveryStatus(){
        return super.getRadioButton(radioDeliveryStatus);
    }


    public boolean checkTerm(){
        return super.checkBoxButton(checkTerms);
    }

    public boolean messageTerms(){
        return messageTermsIsPresented.isEnabled();
    }

    public void messageClose()  throws InterruptedException{
        Thread.sleep(2000);
        messageTermsClose.click();
        Thread.sleep(2000);

    }

    public String message(){
        String var;
        var=validateMessageTerms.getText();
        return  var;

    }

    public void checkTermToContinue(){
        checkTerms.click();
    }

    public float checkValueShipping(){
        float totalValueShipping= Float.parseFloat(valueShipping.getText().replace("$",""));
        return totalValueShipping;
    }

    public float checkValueTotal(){

        totalValue= Float.parseFloat(CheckoutValueTotal.getText().replace("$",""));
        return totalValue;

    }

    public void clicPaymentMethod() throws InterruptedException{
        Thread.sleep(2000);
        paymentMethod.click();

    }

    public float getAmount(){
        float getAmount= Float.parseFloat(valueAmount.getText().replace("$",""));
        return getAmount;
    }

    public void clicConfirmOrder(){
        confirmMyOrder.click();
    }


    public float getAmountConfirm(){
        float getAmountConfirm= Float.parseFloat(valueAmountConfirm.getText().replace("$",""));
        return getAmountConfirm;
    }

    public String orderReference(){
        Pattern pattern = Pattern.compile("([A-Z]{9})");

        Matcher matcher = pattern.matcher(orderReference.getText());
        matcher.find();
        lastOrderReference=  matcher.group(1);
        System.out.println("Order Reference " + lastOrderReference);

       return lastOrderReference;


    }

    public void clicBackToOrders(){
        backToOrders.click();
    }




}




