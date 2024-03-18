package com.ams.developer.api.expense.control.repository;

import com.ams.developer.api.expense.control.model.GastosPorDiaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GastosPorDiaRepository extends JpaRepository<GastosPorDiaModel,String> {
    @Query(
            name = "GastosPorDiaModel.findAllByMonth",
            value = "SELECT E FROM GastosPorDiaModel E WHERE MONTH(E.fecha) = :mes and YEAR(E.fecha)=:anio order by E.id desc"
    )
    List<GastosPorDiaModel> findAllByMonth(@Param("mes") Integer mes, @Param("anio") Integer anio);
}
