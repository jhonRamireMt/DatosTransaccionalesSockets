package servidor.entidad;

public class Saldo {

    private int idSaldo;
    private Double cantidad;
    private int movimiento_idMovimiento;

    public Saldo(int idSaldo, Double cantidad, int movimiento_idMovimiento) {
        this.idSaldo = idSaldo;
        this.cantidad = cantidad;
        this.movimiento_idMovimiento = movimiento_idMovimiento;
    }

    public int getIdSaldo() {
        return idSaldo;
    }

    public void setIdSaldo(int idSaldo) {
        this.idSaldo = idSaldo;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public int getMovimiento_idMovimiento() {
        return movimiento_idMovimiento;
    }

    public void setMovimiento_idMovimiento(int movimiento_idMovimiento) {
        this.movimiento_idMovimiento = movimiento_idMovimiento;
    }
}
