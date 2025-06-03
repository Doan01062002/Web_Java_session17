package com.example.session17.controller;

import com.example.session17.model.Product;
import com.example.session17.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(@RequestParam(name = "page", defaultValue = "1") int page,
                               @RequestParam(name = "size", defaultValue = "10") int pageSize,
                               Model model) {
        List<Product> products = productService.getProductsPaginated(page, pageSize);
        long totalProducts = productService.countTotalProducts();
        long totalPages = (totalProducts + pageSize - 1) / pageSize;

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);
        return "admin-products";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin-product-form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product, Model model) {
        // Không cần xử lý upload file, URL ảnh đã được lưu trực tiếp trong product.image
        productService.saveProduct(product);
        return "redirect:/admin/products?saveSuccess=true";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") int id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/admin/products?productNotFound=true";
        }
        model.addAttribute("product", product);
        return "admin-product-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products?deleteSuccess=true";
    }
}