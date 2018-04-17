package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductDaoTest {
    private ProductDao productDao;

    @Before
    public void setup() {
        productDao=new ProductDao();
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = productDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException{
        Product product=new Product();
        product.setTitle("AA");
        product.setPrice(200);
        long id=productDao.insert(product);

        Product insertedProduct = productDao.get(id);
        assertThat(insertedProduct.getId(),is(id));
        assertThat(insertedProduct.getTitle(),is(product.getTitle()));
        assertThat(insertedProduct.getPrice(),is(product.getPrice()));
    }


}
