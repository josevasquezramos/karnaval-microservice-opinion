package com.sistemas.karnaval.entidad;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OpinionResponseDTO {
	
    private Long id;
    private Integer calificacion;
    private String comentario;
    private LocalDate fechaOpinion;
    private ProductoDTO producto;
    private ClienteDTO cliente;
}
