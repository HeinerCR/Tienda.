
package com.Tienda.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    @RequestMapping("/")
    public String index (Model model, HttpSession session) {
        Long idUsuario = (Long)session.getAttribute ("idUsuario");
        return "index";
    }

}
