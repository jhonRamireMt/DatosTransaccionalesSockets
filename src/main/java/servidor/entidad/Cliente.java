package servidor.entidad;

import java.util.Date;

public class Cliente {
    private int numero_documento;
    private String nombres;
    private String apellidos;
    private Date fecha_creado;
    private int numero_telefono;
    private int clave;
    private Cuenta cuenta;

    public Cliente(int numero_documento, String nombres, String apellidos, Date fecha_creado, int numero_telefono, int clave) {
        this.numero_documento = numero_documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha_creado = fecha_creado;
        this.numero_telefono = numero_telefono;
        this.clave = clave;
    }

    /*ESTE CONSTRUCTOR INICIALIZA LOS ATRIBUTOS PARA UNA CONSULTA DE SALDO*/
    public Cliente(int numero_documento, String nombres, String apellidos, Cuenta cuenta) {
        this.numero_documento = numero_documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cuenta = cuenta;
    }

    public Cliente(String nombres_cliente, String apellidos_cliente, int no_identificacion) {
        this.nombres = nombres_cliente;
        this.apellidos = apellidos_cliente;
        this.numero_documento = no_identificacion;
    }


    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public int getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(int numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFecha_creado() {
        return fecha_creado;
    }

    public void setFecha_creado(Date fecha_creado) {
        this.fecha_creado = fecha_creado;
    }

    public int getNumero_telefono() {
        return numero_telefono;
    }

    public void setNumero_telefono(int numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String mostrarConsultaDeSaldo(){
        return "Cedula: "+numero_documento +"\n"+
                "Nombre cliente: "+nombres+" "+apellidos +"\n"+
                "Numero de cuenta: " +cuenta.getNumero_cuenta() +"\n"+
                "Saldo disponible: "+cuenta.getSaldo() +"\n" +
                "Tipo de cuenta: " +cuenta.getTipo_cuenta();
    }

    public void mostrarClientes(){
        System.out.println("documento: "+numero_documento);
        System.out.println("nombre:" +nombres+" "+apellidos);
        System.out.println("fecha de creado: "+fecha_creado);
        System.out.println("Telefono: "+numero_telefono);
        System.out.println("clave: "+clave);
    }
}
