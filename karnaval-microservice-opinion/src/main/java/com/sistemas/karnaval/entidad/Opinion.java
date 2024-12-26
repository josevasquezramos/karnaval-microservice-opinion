package com.sistemas.karnaval.entidad;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "opiniones")
@Data
public class Opinion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long idProducto; // ID del producto relacionado

	@Column(nullable = true)
	private Long idCliente; // ID del cliente relacionado (opcional)

	@Column(nullable = false)
	private Integer calificacion; // Calificación (1-5)

	@Column(nullable = true, length = 255)
	private String comentario; // Texto de la opinión

	@Column(nullable = false)
	private LocalDate fechaOpinion; // Fecha en que se realizó la opinión
}
