package com.alura.foro.forohub.forohub.infra.errores;

public class ErrorResponse {
    private String mensaje;
    private String detalle;

    public ErrorResponse(String mensaje, String detalle) {
        this.mensaje = mensaje;
        this.detalle = detalle;
    }

    // Getters y setters
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
