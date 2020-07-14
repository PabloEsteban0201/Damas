package logica;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class Ficha implements Cloneable{

    protected EstadoFicha estadoFicha;
    protected Coordenada posicion;
    protected int valor;
    protected String stringRep;
    private ArrayList<Movimiento> listMovs = new ArrayList<>();   

    public static final int NEGRA = 1;
    public static final int ROJA = 2;
    public static final int ROJA_REINA = -2;
    public static final int NEGRA_REINA = -1;

    public abstract boolean esReina();
    public abstract int getColor();
    public abstract void hacerReina();
    public abstract ImageIcon getIcon();
    public abstract Ficha clonar();

    public Coordenada getPosicion() {
        return posicion;
    } 

    public void setPosition(Coordenada c) {
        posicion = c;
    }

    public int getValor() {
       return valor;
    }

    public void obtenerMovimiento(GestorMovimiento gm, Ficha ficha, Tablero t) {
        estadoFicha.obtenerMovimiento(gm, ficha, t);
    }

    public void realizarMovimiento(Movimiento mov, Ficha ficha, Tablero t, GestorMovimiento gm) {
        estadoFicha.realizarMovimiento(mov, ficha, t, gm);
    }

    public void obtenerSalto(GestorMovimiento gm, Ficha ficha, Tablero t) {
        estadoFicha.obtenerSalto(gm, ficha, t);
    }

    public void realizarSalto(GestorMovimiento gm, Movimiento mov, Ficha ficha, Tablero t) {
        estadoFicha.realizarSalto(gm, mov, ficha, t);
    }

    public ArrayList<Movimiento> getListMovs() {
        return listMovs;
    }

    public void setListMovs(ArrayList<Movimiento> listMovs) {
        this.listMovs = listMovs;
    }
    @Override
    public String toString() {
        return (stringRep+" ("+ this.posicion.getX()+","+this.posicion.getY()+")");
    }

    public EstadoFicha getEstadoFicha() {
        return estadoFicha;
    }
    public void setEstadoFicha(EstadoFicha estadoFicha) {
        this.estadoFicha = estadoFicha;
    }	
}
