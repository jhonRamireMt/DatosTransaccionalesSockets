package servidor.entidad;

import java.util.Date;

public class Movimiento {
    private int id_movimiento;
    private String tipo_movimiento;
    private int monto;
    private Date fecha_creado;

    public Movimiento(int id_movimiento, String tipo_movimiento, int monto, Date fecha_creado) {
        this.id_movimiento = id_movimiento;
        this.tipo_movimiento = tipo_movimiento;
        this.monto = monto;
        this.fecha_creado = fecha_creado;
    }

    public int getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(int id_movimiento) {
        this.id_movimiento = id_movimiento;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public Date getFecha_creado() {
        return fecha_creado;
    }

    public void setFecha_creado(Date fecha_creado) {
        this.fecha_creado = fecha_creado;
    }
}
