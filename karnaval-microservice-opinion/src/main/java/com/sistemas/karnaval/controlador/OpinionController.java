package com.sistemas.karnaval.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistemas.karnaval.entidad.Opinion;
import com.sistemas.karnaval.entidad.OpinionResponseDTO;
import com.sistemas.karnaval.servicio.OpinionService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/opiniones")
public class OpinionController {

	@Autowired
	private OpinionService opinionService;

	/**
	 * Endpoint para crear una nueva opinión.
	 * 
	 * @param opinion Datos de la opinión.
	 * @return Opinión creada.
	 */
	@PostMapping
	public ResponseEntity<Opinion> crearOpinion(@RequestBody Opinion opinion) {
		return ResponseEntity.ok(opinionService.crear(opinion));
	}

	/**
	 * Endpoint para obtener opiniones por producto.
	 * 
	 * @param idProducto ID del producto.
	 * @return Lista de opiniones relacionadas con el producto.
	 */
	@GetMapping("/producto/{idProducto}")
	public ResponseEntity<List<OpinionResponseDTO>> obtenerOpinionesPorProducto(
			@PathVariable Long idProducto) {
		return ResponseEntity.ok(opinionService
				.listarOpinionesPorProducto(idProducto));
	}

	/**
	 * Endpoint para listar todas las opiniones.
	 * 
	 * @return Lista de todas las opiniones.
	 */
	@GetMapping
	public ResponseEntity<List<OpinionResponseDTO>> listarTodasOpiniones() {
		return ResponseEntity.ok(opinionService.listarTodosEnriquecidos());
	}

	/**
	 * Endpoint para obtener una opinión por ID.
	 * 
	 * @param id ID de la opinión.
	 * @return Opinión encontrada.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Opinion> obtenerOpinionPorId(@PathVariable Long id) {
		return ResponseEntity.ok(opinionService.buscarPorId(id));
	}

	/**
	 * Endpoint para editar una opinión existente.
	 * 
	 * @param id      ID de la opinión.
	 * @param opinion Datos actualizados de la opinión.
	 * @return Opinión actualizada.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Opinion> editarOpinion(@PathVariable Long id, @RequestBody Opinion opinion) {
		return ResponseEntity.ok(opinionService.editar(id, opinion));
	}

	/**
	 * Endpoint para eliminar una opinión por ID.
	 * 
	 * @param id ID de la opinión.
	 * @return Respuesta sin contenido.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarOpinion(@PathVariable Long id) {
		opinionService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
