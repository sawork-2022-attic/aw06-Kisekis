package com.example.webpos.biz;

import com.example.webpos.model.Cart;
import com.example.webpos.model.Product;

import java.util.List;

public interface PosService {

    public void checkout(Cart cart);

    public void cancel(Cart cart);

    public Cart add(Cart cart, Product product, int amount) throws Exception;

    public Cart add(Cart cart, String productId, int amount) throws Exception;

    public Cart delete(Cart cart,String id) throws Exception;

    public List<Product> products() throws Exception;

    public Product randomProduct() throws Exception;
}
