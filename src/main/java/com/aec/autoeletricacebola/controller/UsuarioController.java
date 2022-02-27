package com.aec.autoeletricacebola.controller;

import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.LOGIN_USUARIO;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.NOME_USUARIO;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.SENHA_USUARIO;

import java.util.Map;

import com.aec.autoeletricacebola.model.Usuario;
import com.aec.autoeletricacebola.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.GET)
    public String getCadastroUsuarioForm() {
        return "cadastrarUsuario";
    }

    @RequestMapping(value = "usuario/cadastrarNovoUsuario", method = RequestMethod.POST)
    public @ResponseBody
    String cadastrarNovousuario(@RequestBody Map itensUsuario, BindingResult result, RedirectAttributes attributes) {

        String userUsuario = (String) itensUsuario.get(LOGIN_USUARIO);

        if(this.usuarioService.findByUser(userUsuario) != null) {
            return "Já há um usuário com esse login";
        }

        Usuario usuario = new Usuario();

        usuario.setAtivo(true);
        usuario.setNomeUsuario((String) itensUsuario.get(NOME_USUARIO));
        usuario.setUserUsuario(userUsuario);
        usuario.setSenhaUsuario((String) itensUsuario.get(SENHA_USUARIO));

        usuario = this.usuarioService.save(usuario);

        System.out.println("Usuário salvo com sucesso. ID: " + usuario.getIdUsuario());
        return "Usuário salvo com sucesso";
    }

}