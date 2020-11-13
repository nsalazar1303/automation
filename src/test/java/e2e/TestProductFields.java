package e2e;
import com.auto.ui.pageObject.ProductFields;
import com.auto.ui.pageObject.Producto;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.json.simple.JSONObject;




public class TestProductFields extends TestBase {


    ProductFields ProdFields;
    Producto homepage;

    JSONObject JsonProductFieldsUI;






    @BeforeSuite
    public void BeforeAll() {
        ProdFields = PageFactory.initElements(driver, ProductFields.class);
        homepage = PageFactory.initElements(driver,Producto.class);




    }

        @Test(testName = "Validate Product Fields", dataProviderClass = data.DataSource.class ,dataProvider = "dressesFieldsData", priority = 0)
        public void  validateProductFields(JSONObject dressesData) throws InterruptedException {
            int productIndex = (int) dressesData.get("Index");
            ProdFields.clicProdDresses();
            System.out.println("Clicked Dresses link");
            JsonProductFieldsUI = new JSONObject();
            JsonProductFieldsUI= ProdFields.selectProduct(productIndex);
            System.out.println("product : " + JsonProductFieldsUI);
            Assert.assertEquals(JsonProductFieldsUI,dressesData);
        }
    }

