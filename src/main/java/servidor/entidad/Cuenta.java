package servidor.entidad;

import java.util.Date;

public class Cuenta {
    private int numero_cuenta;
    private int saldo;
    private Date fecha_creado;
    private String tipo_cuenta;
    private Cliente cliente;


    public Cuenta(int numero_cuenta, int saldo, Date fecha_creado, String tipo_cuenta) {
        this.numero_cuenta = numero_cuenta;
        this.saldo = saldo;
        this.fecha_creado = fecha_creado;
        this.tipo_cuenta = tipo_cuenta;
    }

    public Cuenta(int numero_cuenta, int saldo, String tipo_cuenta) {
        this.numero_cuenta = numero_cuenta;
        this.saldo = saldo;
        this.tipo_cuenta = tipo_cuenta;
    }

    public Cuenta(int numero_cuenta, String tipo_cuenta) {
        this.numero_cuenta = numero_cuenta;
        this.tipo_cuenta = tipo_cuenta;
    }

    public Cuenta(int numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public Cuenta(int cuenta_numero, Cliente cliente) {
        this.numero_cuenta=cuenta_numero;
        this.cliente = cliente;
    }

    public Cuenta(int cuenta_numero, int saldo_disponible) {
        this.numero_cuenta=cuenta_numero;
        this.saldo = saldo_disponible;
    }

    public int getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(int numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Date getFecha_creado() {
        return fecha_creado;
    }

    public void setFecha_creado(Date fecha_creado) {
        this.fecha_creado = fecha_creado;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
