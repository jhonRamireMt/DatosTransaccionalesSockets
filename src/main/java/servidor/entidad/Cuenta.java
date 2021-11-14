package servidor.entidad;

public class Cuenta {

    private int numero;
    private String tipo;
    private int cliente_cedula;

    public Cuenta(int numero, String tipo, int cliente_cedula) {
        this.numero = numero;
        this.tipo = tipo;
        this.cliente_cedula = cliente_cedula;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCliente_cedula() {
        return cliente_cedula;
    }

    public void setCliente_cedula(int cliente_cedula) {
        this.cliente_cedula = cliente_cedula;
    }
}
