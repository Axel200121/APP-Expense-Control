package com.ams.developer.api.expense.control.repository;

import com.ams.developer.api.expense.control.model.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuariosModel,String> {
}
