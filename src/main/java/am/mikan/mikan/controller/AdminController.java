package am.mikan.mikan.controller;

import am.mikan.mikan.dto.request.ProductCrudRequest;
import am.mikan.mikan.dto.response.ProductCrudResponse;
import am.mikan.mikan.entity.Contact;
import am.mikan.mikan.entity.Feedback;
import am.mikan.mikan.entity.Product;
import am.mikan.mikan.mapper.ProductCrudMapper;
import am.mikan.mikan.repository.CommentRepository;
import am.mikan.mikan.repository.ContactRepository;
import am.mikan.mikan.repository.ProductRepository;
import am.mikan.mikan.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final CommentRepository commentRepository;
    private final ContactRepository contactRepository;
    private final ProductCrudMapper productCrudMapper;
    private final ProductRepository productRepository;

    @PostMapping("/product/create")
    public String createProduct(@Valid @ModelAttribute ProductCrudRequest productCrudRequest,
                                @RequestParam(value = "imageFile") MultipartFile file) {
        try {
            String fileName = productService.saveImage(file);
            productCrudRequest.setImage(fileName);
            productService.save(productCrudRequest);
        } catch (IOException ignored) {
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/product/create")
    public String createProductPage() {
        return "pages/admin/product-create";
    }

    @PostMapping("/product/edit")
    public String updateProduct(@ModelAttribute ProductCrudRequest productCrudRequest,
                                @RequestParam(value = "id") int id,
                                @RequestParam(value = "imageFile", required = false) MultipartFile file) {
        Product product = productService.findById(id);
        try {
            if (!file.isEmpty()) {
                String fileName = productService.saveImage(file);
                productCrudRequest.setImage(fileName);
                product.setImage(fileName);
            }
        } catch (IOException ignored) {
            System.out.println("error");
        }
        productRepository.save(product);

        return "redirect:/admin/products";
    }

    @GetMapping("/product/edit/{id}")
    public String updateProductPage(@PathVariable int id, ModelMap modelMap) {
        ProductCrudResponse product = productCrudMapper.toResponse(productService.findById(id));
        modelMap.addAttribute("product", product);
        return "pages/admin/product-update";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/products")
    public String productsPage(ModelMap modelMap) {
        modelMap.addAttribute("productsList", productService.findAllProduct());
        return "pages/admin/products";
    }

    @GetMapping("/feedback")
    public String feedbackList(ModelMap modelMap) {
        List<Feedback> feedbackList = commentRepository.findAll();
        modelMap.addAttribute("feedbacks", feedbackList);
        return "pages/admin/feedback";
    }

    @GetMapping("/feedback/{id}")
    public String feedbackView(ModelMap modelMap, @PathVariable int id) {
        Optional<Feedback> feedbackOptional = commentRepository.findById(id);
        if (feedbackOptional.isPresent()) {
            modelMap.addAttribute("feedback", feedbackOptional.get());
            return "pages/admin/feedback-view";
        }
        return "redirect:/admin/feedback";
    }

    @GetMapping("/feedback/delete/{id}")
    public String deleteFeedback(@PathVariable int id) {
        commentRepository.deleteById(id);
        return "redirect:/admin/feedback";
    }

    @GetMapping("/contact-us")
    public String contactUsList(ModelMap modelMap) {
        List<Contact> contactList = contactRepository.findAll();
        modelMap.addAttribute("contacts", contactList);
        return "pages/admin/contact-us";
    }

    @GetMapping("/contact-us/{id}")
    public String contactUsView(ModelMap modelMap, @PathVariable int id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (contactOptional.isPresent()) {
            modelMap.addAttribute("contact", contactOptional.get());
            return "pages/admin/contact-us-view";
        }
        return "redirect:/admin/contact-us";
    }

    @GetMapping("/contact/us/delete/{id}")
    public String deleteContactUs(@PathVariable int id) {
        contactRepository.deleteById(id);
        return "redirect:/admin/contact-us";
    }

    @GetMapping("/**")
    public String handleRequest() {
        return "redirect:/admin/products";
    }
}
