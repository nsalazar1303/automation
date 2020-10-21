package com.auto.ui.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class PageBase {


//Properties


    public WebDriver driver;
    public Actions over ;
    public static String lastOrderReference;
    public static float totalValue;

    public static List<String> summaryProducts = new ArrayList<String>();

    public PageBase (WebDriver driver){
        this.driver=driver;
        this.over= new Actions(this.driver);

    }

    public void hoverElements(WebElement element){
        over.moveToElement(element).perform();
        System.out.println("Hovered in element");
    }

    public boolean getRadioButton(WebElement element){
    return element.isEnabled();
    }

    public boolean checkBoxButton(WebElement element){
        return element.isSelected();
    }
}
