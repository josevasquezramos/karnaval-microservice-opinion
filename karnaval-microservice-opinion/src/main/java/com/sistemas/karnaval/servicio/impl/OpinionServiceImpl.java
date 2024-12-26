package com.sistemas.karnaval.servicio.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemas.karnaval.cliente.ClienteFeignClient;
import com.sistemas.karnaval.cliente.ProductoFeignClient;
import com.sistemas.karnaval.entidad.ClienteDTO;
import com.sistemas.karnaval.entidad.Opinion;
import com.sistemas.karnaval.entidad.OpinionResponseDTO;
import com.sistemas.karnaval.entidad.ProductoDTO;
import com.sistemas.karnaval.repositorio.OpinionRepository;
import com.sistemas.karnaval.servicio.OpinionService;

@Service
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private OpinionRepository opinionRepository;

    @Autowired
    private ProductoFeignClient productoFeignClient;

    @Autowired
    private ClienteFeignClient clienteFeignClient;

    @Override
    public Opinion crear(Opinion opinion) {
        // Validar existencia del producto
        ProductoDTO producto = productoFeignClient.obtenerProductoPorId(opinion.getIdProducto());
        if (producto == null || !producto.getEstado()) {
            throw new RuntimeException("Producto no encontrado o inactivo");
        }

        opinion.setFechaOpinion(LocalDate.now()); // Asignar fecha actual
        return opinionRepository.save(opinion);
    }

    @Override
    public List<OpinionResponseDTO> listarOpinionesPorProducto(Long idProducto) {
        // Validar existencia del producto
        ProductoDTO producto = productoFeignClient.obtenerProductoPorId(idProducto);
        if (producto == null || !producto.getEstado()) {
            throw new RuntimeException("Producto no encontrado o inactivo");
        }

        return opinionRepository.findByIdProducto(idProducto).stream().map(opinion -> {
            OpinionResponseDTO dto = new OpinionResponseDTO();
            dto.setId(opinion.getId());
            dto.setCalificacion(opinion.getCalificacion());
            dto.setComentario(opinion.getComentario());
            dto.setFechaOpinion(opinion.getFechaOpinion());
            dto.setProducto(producto);

            // Obtener detalles del cliente si existe
            if (opinion.getIdCliente() != null) {
                ClienteDTO cliente = clienteFeignClient.obtenerClientePorId(opinion.getIdCliente());
                dto.setCliente(cliente);
            }

            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<OpinionResponseDTO> listarTodosEnriquecidos() {
        return opinionRepository.findAll().stream().map(opinion -> {
            OpinionResponseDTO dto = new OpinionResponseDTO();
            dto.setId(opinion.getId());
            dto.setCalificacion(opinion.getCalificacion());
            dto.setComentario(opinion.getComentario());
            dto.setFechaOpinion(opinion.getFechaOpinion());

            // Obtener detalles del producto
            ProductoDTO producto = productoFeignClient.obtenerProductoPorId(opinion.getIdProducto());
            dto.setProducto(producto);

            // Obtener detalles del cliente si existe
            if (opinion.getIdCliente() != null) {
                ClienteDTO cliente = clienteFeignClient.obtenerClientePorId(opinion.getIdCliente());
                dto.setCliente(cliente);
            }

            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Opinion editar(Long id, Opinion opinion) {
        Opinion opinionExistente = opinionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Opinión no encontrada"));

        opinionExistente.setCalificacion(opinion.getCalificacion());
        opinionExistente.setComentario(opinion.getComentario());
        return opinionRepository.save(opinionExistente);
    }

    @Override
    public void eliminar(Long id) {
        opinionRepository.deleteById(id);
    }

    @Override
    public Opinion buscarPorId(Long id) {
        return opinionRepository.findById(id).orElseThrow(() -> new RuntimeException("Opinión no encontrada"));
    }

	@Override
	public List<Opinion> listarTodos() {
		return opinionRepository.findAll();
	}
}
