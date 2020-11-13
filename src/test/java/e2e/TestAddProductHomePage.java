package e2e;

import com.auto.ui.pageObject.*;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



public class TestAddProductHomePage extends TestBase {

    Producto homepage;
    ShoppingCart numberCart;
    CheckOut checkOut;
    Login logInLogOut;

    MyAccount myAccount;

    @BeforeSuite
    public void BeforeAll(){
        homepage = PageFactory.initElements(driver,Producto.class);
        numberCart = PageFactory.initElements(driver,ShoppingCart.class);
        checkOut = PageFactory.initElements(driver,CheckOut.class);
        myAccount= PageFactory.initElements(driver,MyAccount.class);
        logInLogOut = PageFactory.initElements(driver,Login.class);



    }

    @Test(testName = "Add two products to Cart- Popular and Best Seller",priority = 0)
    public void addTwoProductsToCart() throws InterruptedException {

        homepage.addToCart_continueShopping("popular",4);
        homepage.addToCart_continueShopping("bestSeller",2);
        Assert.assertEquals(numberCart.getNumberProducts(),2);
    }

    @Test(testName = "Add one product for name", priority = 1)
    public void addProductForName() throws InterruptedException {
        homepage.addToCart_continueShopping("popular", "Printed Chiffon Dress");
        homepage.addToCart_continueShopping("popular", "Printed Chiffon Dress");
        Assert.assertEquals(numberCart.getNumberProducts(),4);
    }

    @Test(testName = "Products in the cart", priority = 2)
    public void productsInCart() throws InterruptedException {
        numberCart.getsProductsNameInCart();
    }

    @Test(testName = "Products in the cart by name", priority = 3)
    public void productsInCartByName() throws InterruptedException {
        //List<String> Listica= new ArrayList<>();
        List<String> Listica= Lists.newArrayList();
        Listica.add("Printed Summer Dress");
        Listica.add("Blouse");
        Listica.add("Printed Chiffon Dress");

        assertThat(numberCart.getsProductsNameInCart(),containsInAnyOrder(Listica.toArray()));
        // es otra forma de ejecutar
        assertThat(numberCart.getsProductsNameInCart(),hasItems("Printed Summer Dress","Printed Chiffon Dress"));
        System.out.println("Listica:" + Listica);
        System.out.println("prendas desde el metodo:" +numberCart.getsProductsNameInCart().toArray());
        System.out.println("Prendas desde el metodo:" +numberCart.getsProductsNameInCart().size());

    }

    @Test(testName = "Price for products and Total price", priority = 4)
    public void totalPrice() throws InterruptedException {
        System.out.println("Total del precio:" +homepage.Total_price);
        Assert.assertEquals(homepage.Total_price, numberCart.getTotalInShoppingCart());

    }

    @Test(testName = "Delete item to cart", priority = 5)
    public void totalCheckOut() throws InterruptedException {
        homepage.Total_price=homepage.Total_price-numberCart.deleteItemFromCart("Printed Chiffon Dress");
        Assert.assertEquals(homepage.Total_price,numberCart.getTotalInShoppingCart());
    }

    @Test(testName = "Delete item from drop down cart", priority = 6)
    public void totalCheckOutAndQuantity() throws InterruptedException {
        homepage.Total_price= homepage.Total_price-numberCart.deleteItemFromDropDown("Printed Summer Dress");
        Assert.assertEquals(homepage.Total_price,numberCart.getTotalInShoppingCart());
        assertThat(numberCart.getsProductsNameInCart(),not(hasItem("Printed Summer Dress")));
    }

    @Test(testName ="Checkout and login", priority=7)
    public void checkOutProcess() throws InterruptedException {
      checkOut.clicCheckOut(false);
      logInLogOut.login("nataliasal@hotmail.com", "test1234");
      Thread.sleep(5000);
      Assert.assertEquals( logInLogOut.isLogIn(),true);
    }

    @Test(testName ="Validate delivery address",dataProviderClass = data.DataSource.class ,dataProvider = "addressDelivery" ,priority=8)
    public void validateDeliveryAddress(JSONObject addressDeliveryData) throws InterruptedException {
      Assert.assertEquals(addressDeliveryData,checkOut.getDeliveryAddress());
        System.out.println("Validated sucessfull address");
    }

    @Test(testName ="Proceed Checkout", priority=9)
    public void proceedCheckout() throws InterruptedException {
        checkOut.clicCheckOut(true);
        Assert.assertEquals(true,checkOut.getDeliveryStatus());
        Assert.assertEquals(false,checkOut.checkTerm());
        checkOut.clicCheckOut(true);
        Assert.assertEquals(true,checkOut.messageTerms());
        Assert.assertEquals("You must agree to the terms of service before continuing.",checkOut.message());
        System.out.println("Validated message");
        checkOut.messageClose();
        System.out.println("Message closed");
        checkOut.checkTermToContinue();
        System.out.println("Checked Terms and Condition");
        Assert.assertEquals(true,checkOut.checkTerm());
        System.out.println("Validated if the value is checked "+checkOut.checkTerm());
        checkOut.clicCheckOut(true);


       // logInLogOut.LogOut();
    }

    @Test(testName ="Products confirmation and Payment method", priority=10)
    public void paymentConfirmProducts() throws InterruptedException {
        //Assert.assertEquals(homepage.Total_price,numberCart.getTotalInShoppingCart());
        //System.out.println("Total price "+numberCart.getTotalInShoppingCart());
        Assert.assertEquals(checkOut.checkValueShipping(),2);
        System.out.println("Total shipping "+checkOut.checkValueShipping());
        Assert.assertEquals(homepage.Total_price+checkOut.checkValueShipping(),checkOut.checkValueTotal());
        System.out.println("Total Checkout "+checkOut.checkValueTotal());
        //Float Valor= checkOut.checkValueTotal();
        PageBase.totalValue=checkOut.checkValueTotal();
        System.out.println("Product lists" + numberCart.getsProductsNameInCart());
        System.out.println("summary products" + PageBase.summaryProducts);
        Assert.assertEquals(numberCart.getsProductsNameInCart(),PageBase.summaryProducts);
        checkOut.clicPaymentMethod();
        System.out.println("Clicked method payment");
        Float getAmount=checkOut.getAmount();
        checkOut.clicConfirmOrder();
        System.out.println("Clicked Confirm my Order");
        Float getAmountConfirm=checkOut.getAmountConfirm();
        Assert.assertEquals(getAmount,getAmountConfirm);
        String orderReference=checkOut.orderReference();
        checkOut.clicBackToOrders();
        String orderNumber= myAccount.validateOrderReference(orderReference);
        Assert.assertEquals(orderReference,orderNumber);
        System.out.println("Confirmed my Order Number " +orderNumber);
        PageBase.lastOrderReference= orderNumber;

    }

    @Test(testName ="My Account and order history", priority=11)
    public void myAccountHistory() throws InterruptedException {
        String orderNumber= myAccount.validateOrderReference(PageBase.lastOrderReference);
        Assert.assertEquals(PageBase.lastOrderReference,orderNumber);
        System.out.println("Confirmed my Order Number " +orderNumber);
        Assert.assertEquals(PageBase.totalValue,myAccount.validateAmount(orderNumber));
    }
}



