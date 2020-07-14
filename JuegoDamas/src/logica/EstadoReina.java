package logica;

public class EstadoReina implements EstadoFicha{

    @Override
    public void obtenerSalto(GestorMovimiento gm, Ficha ficha, Tablero t) {
            gm.obtenerSaltosReina(ficha, t);
    }

    @Override
    public void realizarSalto(GestorMovimiento gm,Movimiento mov, Ficha ficha, Tablero t) {
            gm.realizarSalto(ficha, mov, t);
    }

    @Override
    public void obtenerMovimiento(GestorMovimiento gm, Ficha ficha, Tablero t) {
            gm.obtenerMovimientosReina(ficha, t);
    }

    @Override
    public void realizarMovimiento(Movimiento mov, Ficha ficha, Tablero t, GestorMovimiento gm) {
            GestorMovimiento.realizarMovimiento(ficha, mov, t);
    }

}
