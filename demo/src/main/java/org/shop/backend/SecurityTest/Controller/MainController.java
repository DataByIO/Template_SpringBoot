package org.shop.backend.SecurityTest.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*************************************************************
 /* SYSTEM NAME      : controller
 /* PROGRAM NAME     : MainController.class
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.25   KIMDONGMIN   INTIAL RELEASE
 /*2025.03.25   KIMDONGMIN   웹 동작이 아닌 API성 동작을 기반으로 제작, 이후 웹 동작으로 변경 할 예정
 /*************************************************************/

@Controller
@ResponseBody
public class MainController {

    @GetMapping("/main")
    public String mainP() {

        return "MainController";
    }
}
