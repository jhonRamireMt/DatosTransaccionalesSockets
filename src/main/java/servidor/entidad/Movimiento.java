package servidor.entidad;

import servidor.conexion.Dao;


import java.util.Date;



public class Movimiento {
    private int id_movimiento;
    private String tipo_movimiento;
    private int monto;
    private Date fecha_creado;
    private Cliente cliente;
    private Cuenta cuenta;
    private Dao dao;

    public Movimiento(String tipo_de_movimiento, int monto, Date fecha, Cuenta cuenta, Cliente cliente) {
        this.tipo_movimiento = tipo_de_movimiento;
        this.monto = monto;
        this.fecha_creado = fecha;
        this.cuenta = cuenta;
        this.cliente = cliente;
    }


    public Movimiento() {

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    public String mostrarMovimientos(){

        return ("--------------------------------------------------------------"+"\n"+
                    "CLIENTE: " +cliente.getNombres()+" "+cliente.getApellidos()+"\n"+
                    "No IDENTIFICACION: "+cliente.getNumero_documento()+"\n"+
                    "TIPO DE MOVIMIENTO: " + tipo_movimiento+"\n"+
                    "MONTO: "+monto+"\n"+
                    "FECHA DEL MOVIMIENTO: " +fecha_creado+"\n"+
                    "NUMERO DE CUENTA: " + cuenta.getNumero_cuenta()+"\n");


    }
}
