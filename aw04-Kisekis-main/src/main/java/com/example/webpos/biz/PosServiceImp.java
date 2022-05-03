package com.example.webpos.biz;

import com.example.webpos.db.PosDB;
import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;
import com.example.webpos.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class PosServiceImp implements PosService, Serializable {

    private PosDB posDB;

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }


    @Override
    public Product randomProduct() throws Exception {
        return products().get(ThreadLocalRandom.current().nextInt(0, products().size()));
    }

    @Override
    public void checkout(Cart cart) {
        cart.emptyList();
    }
    @Override
    public void cancel(Cart cart) {
        cart.emptyList();
    }

    @Override
    public Cart add(Cart cart, Product product, int amount) throws Exception {
        return add(cart, product.getId(), amount);
    }

    @Override
    public Cart add(Cart cart, String productId, int amount) throws Exception {

        Product product = posDB.getProduct(productId);
        if (product == null) return cart;

        cart.addItem(new Item(product, amount));
        return cart;
    }

    @Override
    public Cart delete(Cart cart, String productId) throws Exception {
        add(cart,productId,-cart.getItem(productId).getQuantity());
        return cart;
    }

    @Override
    public List<Product> products() throws Exception {
        return posDB.getProducts();
    }
}
