package logica;

public class Jugador {
    private String nombre;
    private int color;
    private boolean pierde;
    private boolean empieza;
    private boolean turno;
    
    public Jugador(String nombre, int color) {
        this.nombre = nombre;
        this.pierde = false; 
        this.empieza = false;
        this.color = color;
        this.turno = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isPierde() {
        return pierde;
    }

    public void setPierde(boolean pierde) {
        this.pierde = pierde;
    }

    public boolean isEmpieza() {
        return empieza;
    }

    public void setEmpieza(boolean empieza) {
        this.empieza = empieza;
    }

    public boolean getTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;		
    }
}
