package logica;


public interface EstadoFicha {
    public void obtenerSalto(GestorMovimiento gm, Ficha ficha, Tablero t);
    public void realizarSalto(GestorMovimiento gm, Movimiento mov, Ficha ficha, Tablero t);
    public void obtenerMovimiento(GestorMovimiento gm, Ficha ficha, Tablero t);
    public void realizarMovimiento(Movimiento mov, Ficha ficha, Tablero t, GestorMovimiento gm);
}
