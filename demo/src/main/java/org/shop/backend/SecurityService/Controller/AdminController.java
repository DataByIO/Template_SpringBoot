package org.shop.backend.SecurityService.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class AdminController {


    @GetMapping("/admin")
    public String admin() {

        return "adminController";
    }
}
