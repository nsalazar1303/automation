package data;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;

public class DataSource {

    static JSONObject productDetails,productDetails2,addressDeliveryData;

    @DataProvider (name="dressesFieldsData")
    public static Object[][] getDressesFieldsData() throws InterruptedException{
        productDetails = new JSONObject();
        productDetails.put("Index" , (1));
        productDetails.put("Title" , "Printed Dress");
        productDetails.put("Price", new Float (50.99));
        productDetails.put("WishList", true);
        productDetails.put("Compare", true);

        productDetails2 = new JSONObject();
        productDetails2.put("Index" , (2));
        productDetails2.put("Title" , "Printed Summer Dress");
        productDetails2.put("Price", new Float (28.98));
        productDetails2.put("WishList", true);
        productDetails2.put("Compare", true);

        return new Object[][]{
            {productDetails},
            {productDetails2}
        };
    }



    @DataProvider (name="addressDelivery")
    public static Object[][] getAddressDeliveryData() throws InterruptedException {
        addressDeliveryData = new JSONObject();
        addressDeliveryData.put("nameUser", "Natalia Test");
        addressDeliveryData.put("addressUser", "Calle 56 No. 420-89");
        addressDeliveryData.put("cityUser", "Medellin, California 80210");
        addressDeliveryData.put("countryUser", "United States");
        addressDeliveryData.put("mobileUser", "3128957845");
        return new Object[][]{
                {addressDeliveryData},

        };
    }
}
