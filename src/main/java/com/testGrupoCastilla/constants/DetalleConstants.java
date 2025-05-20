package com.testGrupoCastilla.constants;

public final class DetalleConstants {

    private DetalleConstants() {
        // prevent instantiation
    }

    // Reutilizables
    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Detalle creado correctamente";

    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Operación realizada con éxito";

    public static final String STATUS_417 = "417";
    public static final String MESSAGE_417_UPDATE = "Error al actualizar el detalle. Por favor, inténtelo de nuevo o contacte con soporte";
    public static final String MESSAGE_417_DELETE = "Error al eliminar el detalle. Por favor, inténtelo de nuevo o contacte con soporte";

    // Eliminados porque no aplican a Detalle
    // public static final String SAVINGS = "Savings";
    // public static final String ADDRESS = "123 Main Street, New York";
}
