package com.ams.developer.api.expense.control.repository;

import com.ams.developer.api.expense.control.model.GastosFijosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GastosFijosRepository extends JpaRepository<GastosFijosModel,String> {
}
