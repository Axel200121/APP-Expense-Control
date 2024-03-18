package com.ams.developer.api.expense.control.repository;

import com.ams.developer.api.expense.control.model.EstadosModel;
import com.ams.developer.api.expense.control.model.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuariosModel,String> {
    UsuariosModel findByCorreo(String correo);
    Optional<UsuariosModel> findByCorreoAndEstadosId(String correo, EstadosModel estado);
}
