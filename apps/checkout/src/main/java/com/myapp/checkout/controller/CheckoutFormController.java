package com.myapp.checkout.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutFormController {
    private static final Logger log = LoggerFactory.getLogger(CheckoutFormController.class);

    @GetMapping("/form/checkout")
    public String checkoutForm(Model model) {
        log.info("Request checkout form");
        return "checkoutForm";
    }
}
