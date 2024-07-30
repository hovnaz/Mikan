package am.mikan.mikan.controller;

import am.mikan.mikan.dto.request.ContactRequest;
import am.mikan.mikan.dto.request.FeedbackRequest;
import am.mikan.mikan.entity.Contact;
import am.mikan.mikan.mapper.ContactMapper;
import am.mikan.mikan.repository.ContactRepository;
import am.mikan.mikan.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @PostMapping("/feedback")
    public String addProductComment(@Valid @ModelAttribute FeedbackRequest feedbackRequest) {
        feedbackService.save(feedbackRequest);
        return "redirect:/";
    }

    @PostMapping("/contact-us")
    public String contactUs(@ModelAttribute ContactRequest contactRequest) {
        Contact contact = contactMapper.toEntity(contactRequest);
        contactRepository.save(contact);
        return "redirect:/contact-us";
    }
}
