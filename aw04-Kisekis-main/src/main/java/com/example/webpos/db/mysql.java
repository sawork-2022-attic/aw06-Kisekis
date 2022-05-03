package com.example.webpos.db;

import com.example.webpos.model.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Primary
@Repository
public class mysql implements PosDB {
    private List<Product> products = null;

    @Override
    public List<Product> getProducts() throws Exception {
        try {
            if (products == null)
                products = parseMysql("Java");
        } catch (IOException e) {
            products = new ArrayList<>();
        }
        return products;
    }

    @Override
    public Product getProduct(String productId) throws Exception {
        for (Product p : getProducts()) {
            if (p.getId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public static List<Product> parseMysql(String keyword) throws Exception {

        List<Product> list = new ArrayList<>();
        String myDriver = "com.mysql.cj.jdbc.Driver";  ;
        String myUrl = "jdbc:mysql://172.19.163.152:3306/mysql";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "test", "test");

        Statement st = conn.createStatement();
        String sql = "SELECT * FROM products LIMIT 50;";
        ResultSet rs = st.executeQuery(sql);
        Random rand = new Random();

        while(rs.next()) {
            String main_cat = rs.getString("main_cat");
            String title = rs.getString("title");
            String asin = rs.getString("asin");
            String category = rs.getString("category");
            List<String> categorys = new ArrayList<String>(Arrays.asList(category.split(",")));
            String imageURLHighRes = rs.getString("imageURLHighRes");
            List<String> images = new ArrayList<String>(Arrays.asList(imageURLHighRes.split(",")));
            Product product = new Product(asin,title, rand.nextDouble(), images.get(0),
                    main_cat,title,categorys, images);
            list.add(product);
        }

        conn.close();
        return list;
    }

}
