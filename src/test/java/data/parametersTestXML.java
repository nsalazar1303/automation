package data;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;







public class parametersTestXML {
    @Test
    @Parameters({"envURL"})
    //constructor
        public void parametersTestXML(String envURL)throws InterruptedException{
        System.out.println("Welcome ->"+envURL);
    }

}
