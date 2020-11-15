package segura.taylor.bl.entidades;

import java.time.LocalDate;

public class Movimiento {
    //Variables
    private EnumTipoMovimiento tipo;
    private LocalDate fecha;
    private String descripcion;
    private double monto;

    //Propiedades
    public EnumTipoMovimiento getTipo() {
        return tipo;
    }
    public void setTipo(EnumTipoMovimiento tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }

    //Constructores
    public Movimiento(){}
    public Movimiento(EnumTipoMovimiento tipo, LocalDate fecha, String descripcion, double monto) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
    }

    //Metodos
    @Override
    public String toString() {
        return "Movimiento{" +
                "tipo=" + tipo +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", monto=" + monto +
                '}';
    }
}
