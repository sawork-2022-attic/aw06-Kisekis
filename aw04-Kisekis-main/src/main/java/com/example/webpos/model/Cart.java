package com.example.webpos.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
@SessionScope
public class Cart implements Serializable {

    private List<Item> items = new ArrayList<>();

    public boolean addItem(Item item) {
        for(Item i : items) {
            if(i.getProduct().getId().equals(item.getProduct().getId())) {
                if(i.getQuantity()+item.getQuantity()<=0) {
                    items.remove(i);
                    return false;
                }
                i.setQuantity(i.getQuantity()+item.getQuantity());
                return true;
            }
        }

        return items.add(item);
    }

    public boolean emptyList() {
        items.clear();
        return true;
    };

    public Item getItem(String id) {
        for(Item i : items) {
            if(i.getProduct().getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

    public double getTotal() {
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getQuantity() * items.get(i).getProduct().getPrice();
        }
        return total;
    }

}
