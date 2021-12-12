package servidor.entidad;

import java.util.Date;

public class Movimiento {
    private int id_movimiento;
    private String tipo_movimiento;
    private Double monto;
    private Date fecha_creado;
    private Cuenta cuenta;

    public Movimiento(int id_movimiento, String tipo_movimiento, Double monto, Date fecha_creado, Cuenta cuenta) {
        this.id_movimiento = id_movimiento;
        this.tipo_movimiento = tipo_movimiento;
        this.monto = monto;
        this.fecha_creado = fecha_creado;
        this.cuenta = cuenta;
    }

    public Movimiento(int id_movimiento, String tipo_movimiento, Double monto, Date fecha_creado) {
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

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFecha_creado() {
        return fecha_creado;
    }

    public void setFecha_creado(Date fecha_creado) {
        this.fecha_creado = fecha_creado;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    public String mostrarMovimientos(){
        return "Tipo de Movimiento: "+tipo_movimiento+"\n"+
                "Fecha Movimiento: " + fecha_creado +"\n"+
                "Cuenta Numero: " +cuenta.getNumero_cuenta() +"\n";
    }
}
