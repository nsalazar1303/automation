package com.auto.ui.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends PageBase{

    //Elements
    @FindBy(xpath = "//input[@id='email']")
    WebElement emailField;

    @FindBy(xpath = "//input[@id='passwd']")
    WebElement passwordField;

    @FindBy(xpath = "//button[@id='SubmitLogin']")
    WebElement submitButton;


    @FindBy(xpath = "//a[@class='logout']")
    WebElement logOutButton;




    public Login(WebDriver driver){
        super(driver);
    }


    public void login (String userEmail,String userPassword){
        emailField.clear();
        emailField.sendKeys(userEmail);
        passwordField.clear();
        passwordField.sendKeys(userPassword);
        submitButton.click();
    }

    public void LogOut (){
        logOutButton.click();
    }

    public boolean isLogIn(){
        try{
            return logOutButton.isDisplayed();
        }
        catch(Exception e){
            return false;
        }
    }

    public void validateDeliveryAddress(){


    }
}
