package com.ams.developer.api.expense.control.repository;

import com.ams.developer.api.expense.control.model.EstadosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadosRepository extends JpaRepository<EstadosModel,String> {
}
