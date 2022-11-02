package com.mdstech.tutorial.controller;

import com.mdstech.tutorial.bo.LoginBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController {

    @GetMapping("/")
    public String defaultPage(ModelMap model) {
        return "hello";
    }

    //@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
    @GetMapping("/hello/{name:.+}")
    public ModelAndView hello(@PathVariable("name") String name) {

        ModelAndView model = new ModelAndView();
        model.setViewName("hello");
        model.addObject("message", name);

        return model;

    }

    @GetMapping(value = "/login")
    public String init(Model model) {
        model.addAttribute("msg", "Please Enter Your Login Details");
        return "login";
    }

    @PostMapping(value = "/login")
    public String submit(Model model, @ModelAttribute("loginBean") LoginBean loginBean) {
        if (loginBean != null && loginBean.getUserName() != null & loginBean.getPassword() != null) {
            System.out.println(loginBean.getUserName());
            if (loginBean.getUserName().equals("test") && loginBean.getPassword().equals("Test#123")) {
                model.addAttribute("msg", loginBean.getUserName());
                return "success";
            } else {
                model.addAttribute("error", "Invalid Details");
                return "login";
            }
        } else {
            model.addAttribute("error", "Please enter Details");
            return "login";
        }
    }
}
