package com.myapp.checkout.controller;

import com.myapp.checkout.model.CheckoutSubmitRequest;
import com.myapp.checkout.service.SaveService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class CheckoutSubmitController {
    private static final Logger log = LoggerFactory.getLogger(CheckoutSubmitController.class);
    private final SaveService saveService;

    @PostMapping("/submit/checkout")
    public String submitCheckOut(CheckoutSubmitRequest request, Model model) {
        log.info(request.toString());
        Long checkoutId = saveService.save(request);
        model.addAttribute("checkoutId", checkoutId);
        return "submitComplete";
    }
}
