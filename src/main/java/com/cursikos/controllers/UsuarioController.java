package com.cursikos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cursikos.entities.Usuario;
import com.cursikos.repository.UsuarioRepositorio;

@Controller
public class UsuarioController {
		
		@Autowired
		UsuarioRepositorio usuarioRepositorio;
		
		@GetMapping("/")
		public String index() {
			return "index";
		}
		
		
		//REGISTRO
		/////////////////////////////////////////////////////////////////////////////////////////////////
		@GetMapping("/usuario/formRegistro")
		public String formRegistro(Model model , Usuario usuario ) {
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("usuarios", usuarioRepositorio.findAll());
			return "formRegistro";
		}
		
		@PostMapping("/usuario/registro")
		public String crearUsuario(@Valid  @ModelAttribute("usuario") Usuario usuario,BindingResult bindingResult, Model model) {
			//Comprobamos si el formulario contiene errores de validacion
			if (usuarioRepositorio.findByemail(usuario.getEmail()) != null) {
				model.addAttribute("error", "El usuario ya existe en la aplicacion");
				return "error";
			}
			if (bindingResult.hasErrors()) {
				return "formRegistro";
			}else {
				usuarioRepositorio.save(usuario);
				model.addAttribute("esBien", "El usuario con email " + usuario.getEmail() + " y Username  " + usuario.getUsername());
				return "esBien";
			}
			
			
		}
		//REGISTRO/////////////////////////////////////////////////////
		//LOGIN////////////////////////////////////////////////////////
		@GetMapping("/usuario/formLogin")
		public String formLogin() {
			return "formLogin";
		}

			
		
}
