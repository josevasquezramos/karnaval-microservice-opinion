package com.sistemas.karnaval.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemas.karnaval.entidad.Opinion;

import java.util.List;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {
	List<Opinion> findByIdProducto(Long idProducto);
}
