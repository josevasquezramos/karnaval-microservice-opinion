package com.sistemas.karnaval.servicio;

import java.util.List;

import com.sistemas.karnaval.entidad.Opinion;
import com.sistemas.karnaval.entidad.OpinionResponseDTO;

public interface OpinionService extends iGenericoService<Opinion, Long> {

	/**
	 * Devuelve opiniones enriquecidas
	 * @param idProducto
	 * @return
	 */
	List<OpinionResponseDTO> listarOpinionesPorProducto(Long idProducto);

	/**
	 * Devuelve todas las opiniones enriquecidas
	 */
	List<OpinionResponseDTO> listarTodosEnriquecidos();
}
