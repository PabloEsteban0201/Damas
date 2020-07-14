package logica;

public class Coordenada {
    
    private final int x;
    private final int y;
    
    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean compararCoordenada(Coordenada d) {
        return d.getX() == x && d.getY()==y;
    }

    public Coordenada arribaIzquierda() {
        return new Coordenada(x-1,y-1);
    }

    public Coordenada arribaDerecha() {
        return new Coordenada(x+1,y-1);
    }

    public Coordenada abajoIzquierda() {
        return new Coordenada(x-1,y+1);
    }

    public Coordenada abajoDerecha() {
        return new Coordenada(x+1,y+1);
    }

    public Coordenada arribaIzquierdaSalto() {
        return new Coordenada(x-2,y-2);
    }

    public Coordenada arribaDerechaSalto() {
        return new Coordenada(x+2,y-2);
    }

    public Coordenada abajoIzquierdaSalto() {
        return new Coordenada(x-2,y+2);
    }

    public Coordenada abajoDerechaSalto() {
        return new Coordenada(x+2,y+2);
    }

    @Override
    public String toString() {
        String c;
        c = "En x: " + x+ " en Y: " +y;
        return c;
    }

    
}
