package com.ams.developer.api.expense.control.repository;

import com.ams.developer.api.expense.control.model.ProveedoresModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<ProveedoresModel,String> {
}
