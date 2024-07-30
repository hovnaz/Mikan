package am.mikan.mikan.controller.advice;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyControllerAdvice {

    @ModelAttribute()
    public void currentUrl(ModelMap modelMap, HttpServletRequest request) {
        modelMap.addAttribute("currentUrl", request.getRequestURI());
    }
}
