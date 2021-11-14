package servidor.entidad;

import java.util.Date;

public class Movimiento {

    private int idMovimiento;
    private Date fecha;
    private String tipo;
    private int cuenta_numero;

    public Movimiento(int idMovimiento, Date fecha, String tipo, int cuenta_numero) {
        this.idMovimiento = idMovimiento;
        this.fecha = fecha;
        this.tipo = tipo;
        this.cuenta_numero = cuenta_numero;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCuenta_numero() {
        return cuenta_numero;
    }

    public void setCuenta_numero(int cuenta_numero) {
        this.cuenta_numero = cuenta_numero;
    }
}
