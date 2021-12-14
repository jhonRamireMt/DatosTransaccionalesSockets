package servidor.entidad;

import java.util.Date;

public class Cuenta {
    private int numero_cuenta;
    private Double saldo;
    private Date fecha_creado;
    private String tipo_cuenta;
    private Cliente cliente;


    public Cuenta(int numero_cuenta, Double saldo, Date fecha_creado, String tipo_cuenta) {
        this.numero_cuenta = numero_cuenta;
        this.saldo = saldo;
        this.fecha_creado = fecha_creado;
        this.tipo_cuenta = tipo_cuenta;
    }

    public Cuenta(int numero_cuenta, Double saldo, String tipo_cuenta) {
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


    public int getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(int numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
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
}
