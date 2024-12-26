package com.sistemas.karnaval.entidad;

import lombok.Data;

@Data
public class ProductoDTO {

	private Long id;
	private String nombre;
	private String descripcion;
	private Boolean estado;
}
