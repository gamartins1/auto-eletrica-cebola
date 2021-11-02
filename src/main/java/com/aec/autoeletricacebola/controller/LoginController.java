package com.aec.autoeletricacebola.controller;

import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.MENSAGEM;

import com.aec.autoeletricacebola.model.Usuario;
import com.aec.autoeletricacebola.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String validateLoginInfo(Usuario usuario, RedirectAttributes attributes) {

        Usuario user = loginService.findUserLogin(usuario);

        if(user == null) {
            attributes.addFlashAttribute(MENSAGEM, "Erro ao fazer login, verifique o usuário/senha");
            return "redirect:/login";
        }

        return "redirect:/menu";
    }
}
