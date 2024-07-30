package am.mikan.mikan.controller;

import am.mikan.mikan.dto.response.ProductResponse;
import am.mikan.mikan.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductService productService;

    @GetMapping({"","/"})
    public String mainPage(ModelMap modelMap) {
        List<ProductResponse> productList = productService.findAllByIdAndProductByLanguage(LocaleContextHolder.getLocale().getLanguage());
        modelMap.addAttribute("productMenuList", productList);
        return "pages/home";
    }

    @GetMapping("/our_works")
    public String menuPage(ModelMap modelMap) {
        List<ProductResponse> productList = productService.findAllByIdAndProductByLanguage(LocaleContextHolder.getLocale().getLanguage());
        modelMap.addAttribute("productMenuList", productList);
        return "pages/menu";
    }

    @GetMapping("/about-us")
    public String aboutUsPage() {
        return "pages/about-us";
    }

    @GetMapping("/contact-us")
    public String contactUsPage() {
        return "pages/contact-us";
    }

    @GetMapping(value = "/getProductPic/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable String fileName) {
        return productService.getImage(fileName);
    }

}
