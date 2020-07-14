package logica;

public abstract class Movimiento {
	   
    protected Coordenada destino;
    protected Ficha ficha;
    protected Movimiento siguiente = null;
      
    public abstract boolean esSalto();
    public abstract Movimiento copia(Tablero nuevoTablero);
    public abstract Movimiento copia();
   
    public Movimiento(Ficha ficha, Coordenada destino) {
        this.ficha = ficha;
        this.destino = destino;
    }

    public Ficha getFicha() {
        return ficha;
    }
   
    public Coordenada getDestino() {
    	return destino;
    }
    
    public Coordenada getOrigen() {
    	return ficha.getPosicion();
    }

   // For the MoveList class.
    public void setNext(Movimiento siguiente) {
      this.siguiente = siguiente;   
    }
   
   
    // For the MoveList class.
    public Movimiento getNext() {
       return siguiente;
    }

    @Override
    public String toString() {
        String rta;
        rta = "(" + ficha.getPosicion().getX() +","+ ficha.getPosicion().getY()
            +") --->"+ "(" + destino.getX() +"," + destino.getY()+")";
        return rta;
    }
}
