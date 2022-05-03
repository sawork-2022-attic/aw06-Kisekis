package com.example.webpos.web;

import com.example.webpos.biz.PosService;
import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PosController {

    @Autowired
    private CacheManager cacheManager;

    private PosService posService;

    private Cart cart;

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @GetMapping("/")
    public String pos(Model model, HttpServletRequest request) throws Exception {
        request.getSession(true);
        model.addAttribute("products", posService.products());
        model.addAttribute("cart", cart);
        return "index";
    }

    @GetMapping("/add")
    public String addByGet(@RequestParam(name = "pid") String pid, Model model) throws Exception {
        posService.add(cart, pid, 1);
        model.addAttribute("products", posService.products());
        model.addAttribute("cart", cart);
        return "redirect:/";
    }
    @GetMapping("/minus")
    public String minus(@RequestParam(value = "pid") String pid,Model model) throws Exception {
        posService.add(cart,pid,-1);
        model.addAttribute("products", posService.products());
        model.addAttribute("cart", cart);
        return "redirect:/";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam(value = "pid") String pid,Model model) throws Exception {
        posService.delete(cart,pid);
        model.addAttribute("products", posService.products());
        model.addAttribute("cart", cart);
        return "redirect:/";
    }
    @GetMapping("/charge")
    public String charge() {
        posService.checkout(cart);
        return "redirect:/";
    }
    @GetMapping("/cancel")
    public String cancel() {
        posService.cancel(cart);
        return "redirect:/";
    }
}
