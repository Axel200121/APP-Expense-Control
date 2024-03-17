package com.ams.developer.api.expense.control.repository;

import com.ams.developer.api.expense.control.model.GastosPorDiaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GastosPorDiaRepository extends JpaRepository<GastosPorDiaModel,String> {
}
