package controlador;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import logica.*;

public final class JuegoConsola {
    public Tablero tableroJuego;
    public final GestorMovimiento gestorPrincipal;
    public final Jugador jugador1, jugador2;

    public JuegoConsola() {
            tableroJuego = new Tablero();
            jugador1 = new Jugador("", Ficha.NEGRA);
            jugador2 = new Jugador("", Ficha.ROJA);
            gestorPrincipal = new GestorMovimiento();
            empieza();
    }

    public void iniciarJuego() {
            boolean enJuego= true;
            //Determina que jugador comienza el juego
            tableroJuego.mostrarTablero();
            if(jugador1.isEmpieza()) {
                    System.out.println("Comienzan negras");
                    jugador1.setTurno(true);
                    ejecutarTurno(jugador1);
                    jugador2.setTurno(true);
            }else {
                    System.out.println("Comienzan rojas");
                    jugador2.setTurno(true);
                    ejecutarTurno(jugador2);
                    jugador1.setTurno(true);
            }

            do {
                    tableroJuego.mostrarTablero();
                    if(jugador1.getTurno()) {
                            System.out.println("turno negras");
                            ejecutarTurno(jugador1);
                            jugador2.setTurno(true);
                    }else {
                            System.out.println("turno rojas");
                            ejecutarTurno(jugador2);
                            jugador1.setTurno(true);
                    }
            }while(enJuego);

    }

    public void ejecutarTurno(Jugador jugador) {
            ArrayList<Ficha> saltos;
            Ficha ficha;
            do {
                    int x = Integer.parseInt(JOptionPane.showInputDialog("Coordenada en x: "));
                    int y = Integer.parseInt(JOptionPane.showInputDialog("Coordenada en y: "));
                    Coordenada c = new Coordenada(x,y);
                    ficha = tableroJuego.getFichaTablero(c);

                    if (jugador.getColor()==Ficha.NEGRA) {
                            saltos = gestorPrincipal.revisarSaltosNegras(tableroJuego);
                    }else {
                            saltos = gestorPrincipal.revisarSaltosRojas(tableroJuego);
                    }

                            if(jugador.getColor()==ficha.getColor() && jugador.getTurno()) {

                                    if(realizarMovimientos(ficha,saltos)) {
                                            jugador.setTurno(false);
                                            System.out.println("cambio turno");
                                    }	
                            }
                            else {
                                    System.out.println("Seleccione una ficha valida");
                            }

            }while(jugador.getTurno());
    }

    private boolean realizarMovimientos(Ficha f, ArrayList<Ficha> saltos) {
            boolean estadoMov = false;
            // Caso para relizar un movimiento normal
            if(saltos.isEmpty()) {
                    ejecutarMovimiento(f);
                    estadoMov = true;
            //Caso para realizar un salto
            }else {
                    ejecutarSalto(saltos);
                    estadoMov = true;
            }
            return estadoMov;
    }
    public void ejecutarMovimiento(Ficha f) {
            ArrayList<Integer> aux = new ArrayList<>();
            f.obtenerMovimiento(gestorPrincipal, f, tableroJuego);

            for(int i=0; i<f.getListMovs().size();i++) {
                    aux.add(i+1);
                    System.out.println(i+1+". Mov: "+f.getListMovs().get(i).toString());
            }
            Integer[] opciones = aux.toArray(new Integer[aux.size()]);
    int movimiento = JOptionPane.showOptionDialog(null, "Seleccione un movimiento:","Damas",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, -1);
    for(int i=0; i<f.getListMovs().size();i++) {
            if (movimiento==i) {
                    f.realizarMovimiento(f.getListMovs().get(i), f, tableroJuego, gestorPrincipal);
                    }
    }

    }

    public void ejecutarSalto(ArrayList<Ficha> saltos) {
            JOptionPane.showMessageDialog(null, "hay saltos disponibles, realizando salto");

            Ficha aux = saltos.get(0);
            System.out.println(aux.toString());
            aux.obtenerSalto(gestorPrincipal, aux, tableroJuego);
            for(int i=0; i<saltos.size();i++) {
                    for(int j=0; j<saltos.get(i).getListMovs().size();j++) {
                            System.out.println("1. Mov: "+saltos.get(i).getListMovs().get(j).toString());
                    }
            }
            aux.realizarSalto(gestorPrincipal, aux.getListMovs().get(0), aux,tableroJuego);
    }

    public void empieza(){
            Random rand = new Random();
    int randomNum1 = rand.nextInt(2) + 1;
    int randomNum2 = rand.nextInt(2) + 1;

    if(randomNum1 >= randomNum2) {
            jugador2.setEmpieza(true);
    }else {
            jugador1.setEmpieza(true);
    }
}

    public Tablero getTableroJuego() {
            return tableroJuego;
    }

    public void setTableroJuego(Tablero tableroJuego) {
            this.tableroJuego = tableroJuego;
    }

    public GestorMovimiento getGestorPrincipal() {
            return gestorPrincipal;
    }

    public Jugador getJugador1() {
            return jugador1;
    }

    public Jugador getJugador2() {
            return jugador2;
    }
}
