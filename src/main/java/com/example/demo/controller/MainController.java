package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public String main(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "main";
    }

    @GetMapping("/Product")
    public List<Product> index() {
        return productRepository.findAll();
    }

    @GetMapping("/Product/{id}")
    public Product show(@PathVariable String id) {
        int productId = Integer.parseInt(id);
        return productRepository.findById(productId).get();
    }

    @PostMapping("/Product")
    public Product create(@RequestBody Map<String, ?> body) {
        double price = (Double) body.get("price");
        int quantity = (Integer) body.get("quantity");
        String description = (String) body.get("description");
        String name = (String) body.get("name");
        String brand = (String) body.get("brand");
        int rating = (Integer) body.get("rating");
        String image = (String) body.get("image");

        return productRepository.save(new Product(price, quantity, description, name, brand, rating, image));
    }

    @PostMapping("/Product/{id}")
    public Product update(@PathVariable String id, @RequestBody Map<String, ?> body) {
        int productId = Integer.parseInt(id);

        //Get product
        Product product = productRepository.findById(productId).get();

        double price = (Double) body.get("price");
        int quantity = (Integer) body.get("quantity");
        String description = (String) body.get("description");
        String name = (String) body.get("name");
        String brand = (String) body.get("brand");
        int rating = (Integer) body.get("rating");
        String image = (String) body.get("image");

        product.setPrice(price);
        product.setQuantity(quantity);
        product.setDescription(description);
        product.setName(name);
        product.setBrand(brand);
        product.setRating(rating);
        product.setImage(image);

        return productRepository.save(product);
    }

    @DeleteMapping("/Product/{id}")
    public boolean delete(@PathVariable String id) {
        productRepository.delete(show(id));
        return true;
    }


}