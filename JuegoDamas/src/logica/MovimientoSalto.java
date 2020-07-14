package logica;

public class MovimientoSalto extends Movimiento{

    public MovimientoSalto(Ficha ficha, Coordenada destino) {
        super(ficha, destino);
    }

    @Override
    public boolean esSalto() {
        return true;
    }

    @Override
    public Movimiento copia(Tablero nuevoTablero) {
        return new MovimientoSalto(nuevoTablero.getFichaTablero(
            ficha.getPosicion()), destino);
    }

    @Override
    public Movimiento copia() {
        return new MovimientoSalto(ficha, destino);
    }

    public Coordenada fichaComida() {
        //if(ficha.getPosicion())
        return null;
    }
    
}
