package com.ams.developer.api.expense.control.repository;

import com.ams.developer.api.expense.control.model.GastosFijosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GastosFijosRepository extends JpaRepository<GastosFijosModel,String> {

    @Query(
            name="GastosFijosModel.findAllByMonth",
            value="SELECT E FROM GastosFijosModel E WHERE MONTH(E.fecha)= :mes and YEAR(E.fecha)=:anio order by E.id desc"
    )
    List<GastosFijosModel> findAllByMonth(@Param("mes") Integer mes, @Param("anio") Integer anio);
}
