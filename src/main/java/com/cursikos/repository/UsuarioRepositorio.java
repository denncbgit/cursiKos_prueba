package com.cursikos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.cursikos.entities.Usuario;

public interface UsuarioRepositorio extends  JpaRepository<Usuario, Long> {
		Usuario findByemail(String email);
}
