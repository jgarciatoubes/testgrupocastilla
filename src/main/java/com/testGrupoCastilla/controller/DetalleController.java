package com.testGrupoCastilla.controller;

import com.testGrupoCastilla.constants.DetalleConstants;
import com.testGrupoCastilla.dto.DetalleMaestroDTO;
import com.testGrupoCastilla.dto.ErrorResponseDto;
import com.testGrupoCastilla.dto.ResponseDto;
import com.testGrupoCastilla.entity.Detalle;
import com.testGrupoCastilla.service.IDetalleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name = "CRUD REST APIs para Detalles",
        description = "REST APIs para crear, actualizar y consultar detalles asociados a un maestro"
)
@RestController
@RequestMapping(path = "/api/detalles", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class DetalleController {

    private final IDetalleService detalleService;

    @Operation(
            summary = "Crear Detalle",
            description = "REST API para crear un nuevo detalle asociado a un maestro"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @PostMapping("/crear")
    public ResponseEntity<ResponseDto> crearDetalle(@Valid @RequestBody Detalle detalle) {
        detalleService.crearDetalle(detalle);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(DetalleConstants.STATUS_201, DetalleConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Actualizar Detalle",
            description = "REST API para actualizar un detalle existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @PutMapping("/actualizar")
    public ResponseEntity<ResponseDto> actualizarDetalle(@Valid @RequestBody Detalle detalle) {
        try {
            detalleService.actualizarDetalle(detalle);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(DetalleConstants.STATUS_200, DetalleConstants.MESSAGE_200));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(DetalleConstants.STATUS_417, DetalleConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Obtener Detalles por Maestro",
            description = "REST API para obtener una lista de detalles asociados a un ID de maestro"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @GetMapping("/por-maestro/{maestroId}")
    public ResponseEntity<List<DetalleMaestroDTO>> obtenerDetallesPorMaestro(@PathVariable Long maestroId) {
        List<DetalleMaestroDTO> resultado = detalleService.buscarPorIdMaestro(maestroId);
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    @Operation(
            summary = "Buscar Detalles por Rango de Fechas",
            description = "REST API para obtener detalles en un rango de fechas"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @GetMapping("/por-fechas")
    public ResponseEntity<List<Detalle>> buscarPorFechas(
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta) {
        List<Detalle> resultado = detalleService.buscarPorRangoDeFechas(desde, hasta);
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }
}
