package resetsoap;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Author zq
 * Created by CTSIG on 2018/6/20.
 * Email : qizhou1994@126.com
 */
public class JAXRS2Client {

    public static void main(String[] args) {
        String baseAddress = "http://localhost:8080/ws/rest";

        JacksonJsonProvider jsonProvider = new JacksonJsonProvider();

         List productList = ClientBuilder.newClient()
                .register(jsonProvider)
                .target(baseAddress)
                .path("/products")
                .request(MediaType.APPLICATION_JSON)
                .get(List.class);
        for (Object product : productList) {
            System.out.println(product);
        }

      /*  List<Product> productList = ClientBuilder.newClient()
                .register(jsonProvider)
                .target(baseAddress)
                .path("/products")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Product>>(){});
        for (Product product : productList) {
            System.out.println(product);
        }*/
    }
}
