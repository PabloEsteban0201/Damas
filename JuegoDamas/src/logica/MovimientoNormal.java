package logica;

public class MovimientoNormal extends Movimiento{

    public MovimientoNormal(Ficha ficha, Coordenada destino) {
        super(ficha, destino);
    }

    @Override
    public boolean esSalto() {
        return false;
    }

    @Override
    public Movimiento copia(Tablero nuevoTablero) {
        return new MovimientoNormal(nuevoTablero.getFichaTablero(
                ficha.getPosicion()),destino);
    }

    @Override
    public Movimiento copia() {
        return new MovimientoNormal(ficha.clonar(),destino);
    }

}
