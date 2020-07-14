package controlador;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import logica.*;
import vista.*;

public final class Juego implements MouseListener{
	
    private Tablero tableroJuego = new Tablero();
    private final VentanaTablero ventana;
    private final GestorMovimiento gestorPrincipal = new GestorMovimiento();
    private final Jugador jugadorNegras, jugadorRojas;
    private ArrayList<Ficha> fichasSalto = new ArrayList<>(); 
    private static Juego instanciaUnica;
    private boolean enJuego;

    /**
     * Implementacion del patron Singleton, dado que no pueden haber 
     * dos o mas juegos al tiempo
     */
    private Juego() {
            jugadorNegras = new Jugador("jugador Negras", Ficha.NEGRA);
            jugadorRojas = new Jugador("Jugador Rojas", Ficha.ROJA);
            ventana = new VentanaTablero(tableroJuego,gestorPrincipal,this);
            enJuego = false;
            empieza();
    }

    public static Juego getInstance() {
    if (instanciaUnica == null) {
        //Creando una unica instancia para el juego
        instanciaUnica = new Juego();
    }
    else{
        System.out.println("Ya hay un juego en el momento");
    }
    return instanciaUnica;
    }

    public void reiniciarJuego() {
            instanciaUnica = null;
    }

    public void iniciarJuego() {
            if(jugadorNegras.isEmpieza()){
                    ventana.setTurno(jugadorNegras.getNombre());
                    jugadorNegras.setTurno(true);
                    jugadorRojas.setTurno(false);
            }else {
                    ventana.setTurno(jugadorRojas.getNombre());
                    jugadorRojas.setTurno(true);
                    jugadorNegras.setTurno(false);
            }
            enJuego=true;
    }

    private Jugador getJugadorActual() {
            Jugador j;
            if(jugadorNegras.getTurno()) {
                    j = jugadorNegras;
            }else {
                    j = jugadorRojas;
            }
            return j;
    }

    private void cambiarTurno() {
            if(jugadorNegras.getTurno()) {
                    ventana.setTurno(jugadorRojas.getNombre());
                    jugadorNegras.setTurno(false);
                    jugadorRojas.setTurno(true);
            }else {
                    if(jugadorRojas.getTurno()) {
                            ventana.setTurno(jugadorNegras.getNombre());
                            jugadorRojas.setTurno(false);
                            jugadorNegras.setTurno(true);
                    }
            }

    }

    private void revisarSaltos() {
            if(getJugadorActual().getColor() == Ficha.NEGRA) {
                    fichasSalto = gestorPrincipal.revisarSaltosNegras(tableroJuego);
            }else {
                    fichasSalto = gestorPrincipal.revisarSaltosRojas(tableroJuego);
            }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
            int xCursor = e.getX();
            int yCursor = e.getY();
            int colorActual;
            Coordenada c;
            Ficha ficha;
            colorActual = getJugadorActual().getColor();

            //1 Revisar Saltos, como conozco el color gracias a colorActual

            revisarSaltos();
            if(enJuego) {
                    if(!fichasSalto.isEmpty()) {
                            if(ventana.getEstadoClick() == VentanaTablero.DESPRESIONADO) {
                                    Ficha fSalto;
                                    Coordenada f;
                                    c = ventana.obtenerCoordenada(xCursor, yCursor);
                                    int cont =0;
                                    for(int i=0; i<fichasSalto.size();i++) {
                                            f=fichasSalto.get(i).getPosicion();
                                            if(c.compararCoordenada(f)) {
                                                    fSalto = fichasSalto.get(i);
                                                    ficha = fichasSalto.get(i);
                                                    fSalto.obtenerSalto(gestorPrincipal, fSalto, tableroJuego);
                                                    ventana.setFichaPresionada(fSalto);
                                                    ventana.setEstadoClick(VentanaTablero.PRESIONADO);
                                                    ventana.getBoard().mostrarOpcionesMovimiento(fSalto);
                                                    ventana.getBoard().repaint();
                                                    cont=1;
                                            }else
                                                    cont = 2;
                                    }
                                    if(cont==2)
                                            JOptionPane.showMessageDialog(null, "Hay un Salto en el Tablero", "Salto", JOptionPane.PLAIN_MESSAGE);
                            }else if(ventana.getEstadoClick() == VentanaTablero.PRESIONADO && 
                                            ventana.obtenerCoordenada(xCursor,yCursor).compararCoordenada(ventana.getFichaPresionada().getPosicion())) {
                                    ventana.getBoard().borrarOpciones();
                                    ventana.getBoard().repaint();
                                    ventana.setEstadoClick(VentanaTablero.DESPRESIONADO);
                                    ventana.setFichaPresionada(null);
                            }else {
                                    if(ventana.getEstadoClick() == VentanaTablero.PRESIONADO && ventana.saltarFicha(xCursor, yCursor)) {
                                    	
                                            ventana.getBoard().borrarOpciones();
                                            ventana.setEstadoClick(VentanaTablero.DESPRESIONADO);
                                            ventana.repaint();
                                            fichasSalto.clear();
                                            ventana.setFichaPresionada(tableroJuego.getFichaTablero(ventana.obtenerCoordenada(xCursor, yCursor)));
                                            revisarSaltos();
                                            if(fichasSalto.isEmpty()) {
                                                    ventana.setFichaPresionada(null);
                                                    cambiarTurno();
                                                    enJuego = gestorPrincipal.hayMovimientos(tableroJuego, getJugadorActual().getColor());

                                            }else {
                                            	
                                            		if(!fichasSalto.contains(ventana.getFichaPresionada())) {
                                            			cambiarTurno();
                                            			ventana.setFichaPresionada(null);
                                            		}
                              
                                            }
                                    }
                            }
                    }else {
                            if(ventana.getEstadoClick() == VentanaTablero.DESPRESIONADO) {
                                    c = ventana.obtenerCoordenada(xCursor, yCursor);
                                    ficha = tableroJuego.getFichaTablero(c);
                                    if(ficha!=null) {
                                            if(ficha.getColor()== colorActual) {			
                                                    ficha.obtenerMovimiento(gestorPrincipal, ficha, tableroJuego);
                                                    if(!ficha.getListMovs().isEmpty()) {
                                                            ventana.setFichaPresionada(ficha);
                                                            ventana.setEstadoClick(VentanaTablero.PRESIONADO);
                                                            //Mostrar los movimientos
                                                            ventana.getBoard().mostrarOpcionesMovimiento(ficha);
                                                            ventana.getBoard().repaint();
                                                    }
                                            }	
                                    }
                            }else if(ventana.getEstadoClick() == VentanaTablero.PRESIONADO && 
                                            ventana.obtenerCoordenada(xCursor,yCursor).compararCoordenada(ventana.getFichaPresionada().getPosicion()) ) {
                                    ventana.getBoard().borrarOpciones();
                                    ventana.getBoard().repaint();
                                    ventana.setFichaPresionada(null);
                                    ventana.setEstadoClick(VentanaTablero.DESPRESIONADO);
                            }else {
                                    //Aqu� se realizar� el movimiento
                                    if(ventana.getEstadoClick() == VentanaTablero.PRESIONADO && ventana.moverFicha(xCursor,yCursor)) {
                                            //moverFicha(xCursor,yCursor);
                                            ventana.getBoard().borrarOpciones();
                                            ventana.setFichaPresionada(null);
                                            ventana.setEstadoClick(VentanaTablero.DESPRESIONADO);
                                            ventana.repaint();
                                            cambiarTurno();
                                            enJuego = gestorPrincipal.hayMovimientos(tableroJuego, getJugadorActual().getColor());
                                    }
                            }
                    }	
            }else {
                    JOptionPane.showMessageDialog(null, "�Juego terminado, Inicie un nuevo juego!");
            }
    }
    public void empieza(){
            Random rand = new Random();
    int randomNum1 = rand.nextInt(2) + 1;
    int randomNum2 = rand.nextInt(2) + 1;

    if(randomNum1 >= randomNum2) {
            jugadorRojas.setEmpieza(true);
    }else {
            jugadorNegras.setEmpieza(true);
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
            return jugadorNegras;
    }

    public Jugador getJugador2() {
            return jugadorRojas;
    }



    @Override
    public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

    }


}
