package resetsoap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author zq
 * Created by CTSIG on 2018/6/20.
 * Email : qizhou1994@126.com
 */

public class ProductServiceImpl implements ProductService {
    private static List<Product> list = new ArrayList<Product>();
    static {
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setName("产品" + i);
            product.setTime("时间" + i);
            list.add(product);
        }
    }

    public List<Product> retrieveAllProducts() {
        return list;
    }

    public Product retrieveProductById(long id) {
        return list.get((int) id);
    }

    public List<Product> retrieveProductsByName(String name) {
        return null;
    }

    public Product createProduct(Product product) {
        return null;
    }

    public Product updateProductById(long id, Map<String, Object> fieldMap) {
        return null;
    }

    public Product deleteProductById(long id) {
        return null;
    }
}
