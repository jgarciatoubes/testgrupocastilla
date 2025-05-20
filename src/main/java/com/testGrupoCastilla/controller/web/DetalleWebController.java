package com.testGrupoCastilla.controller.web;

import com.testGrupoCastilla.dto.DetalleMaestroDTO;
import com.testGrupoCastilla.entity.Detalle;
import com.testGrupoCastilla.entity.Maestro;
import com.testGrupoCastilla.repository.MaestroRepository;
import com.testGrupoCastilla.service.IDetalleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class DetalleWebController {

    private final IDetalleService detalleService;
    private final MaestroRepository maestroRepository;

    @GetMapping("/detalles")
    public String verFormulario(Model model) {
        model.addAttribute("maestros", maestroRepository.findAll());
        model.addAttribute("detalle", new Detalle());
        return "formulario";
    }

    @PostMapping("/detalles")
    public String guardarDetalle(@ModelAttribute("detalle") Detalle detalle, Model model) {
        try {
            detalleService.crearDetalle(detalle);
            model.addAttribute("exito", "Detalle guardado correctamente.");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("maestros", maestroRepository.findAll());
        return "formulario";
    }

    @GetMapping("/detalles/por-maestro")
    public String buscarPorMaestro(@RequestParam("maestroId") Long maestroId, Model model) {
        List<DetalleMaestroDTO> detalles = detalleService.buscarPorIdMaestro(maestroId);
        model.addAttribute("detalles", detalles);
        model.addAttribute("maestros", maestroRepository.findAll());
        model.addAttribute("detalle", new Detalle());
        return "formulario";
    }
}
