package io.github.benny123tw.vitethymeleafdemo.controller;

import io.github.benny123tw.vitethymeleafdemo.bean.GreetingForm;
import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("names", Arrays.asList("Alice", "Bob", "Charlie"));
        model.addAttribute("greetingForm", new GreetingForm());
        return "index";
    }

    @PostMapping("/greeting")
    public String greeting(String name, Model model) {
        model.addAttribute("message", "Hello, " + name + "!");
        return "greeting";
    }

}
