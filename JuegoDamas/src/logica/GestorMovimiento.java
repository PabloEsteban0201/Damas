package logica;

import java.util.ArrayList;

/*
 * Clase que contiene las reglas del juego
 */
public class GestorMovimiento {
	
    ArrayList<Ficha> saltosNegrasDef;
    ArrayList<Ficha> saltosRojasDef;

    /**
     * Metodo para revisar si hay fichas con saltos disponibles para las negras
     * @param t, un objeto Tablero, para revisar como es la situaci�n
     * actual del tablero.
     * @return  ArrayList de Fichas con capacidad de Salto
     */
    public ArrayList<Ficha> revisarSaltosNegras(Tablero t) {
        ArrayList<Ficha> saltosNegras= new ArrayList<>();
        Coordenada c;
        saltosNegrasDef = new ArrayList<>();

        for(int i=0; i<Tablero.ALTURA; i++) {
            for(int j=0; j<Tablero.ANCHO; j++) {
                c = new Coordenada(j,i);
                //revisa que no sea una casilla vacia
                if(t.getFichaTablero(c)!= null 
                    && t.getFichaTablero(c).getColor() == Ficha.NEGRA) {
                    //Revisa si es reina
                    if(t.getFichaTablero(c).getValor() == Ficha.NEGRA_REINA){	
                        buscarSaltosReina(saltosNegras,c,t);
                    }
                    //Revisa si hay saltos abajo puesto que es negra normal
                    else if(buscarFichaSaltosAbajo(c, t)!=null)
                        saltosNegras.add(buscarFichaSaltosAbajo(c, t));
                }
            }
        }
        saltosNegrasDef = saltosNegras;
        return saltosNegras;
    }

    /**
     * Metodo para revisar si hay fichas con saltos disponibles para las rojas
     * @param t, un objeto Tablero, para revisar como es la situaci�n
     * actual del tablero.
     * @return  ArrayList de Fichas con capacidad de Salto
     */
    public ArrayList<Ficha> revisarSaltosRojas(Tablero t) {
        ArrayList<Ficha> saltosRojas= new ArrayList<>();
        Coordenada c;
        saltosRojasDef = new ArrayList<>();
        for(int i=0; i<Tablero.ALTURA; i++) {
            for(int j=0; j<Tablero.ANCHO; j++) {
                c = new Coordenada(j,i);
                if(t.getFichaTablero(c)!= null  
                    && t.getFichaTablero(c).getColor()==Ficha.ROJA) {
                    //Revisa si es reina
                    if(t.getFichaTablero(c).getValor() == Ficha.ROJA_REINA){
                        buscarSaltosReina(saltosRojas,c,t);
                    }else if(buscarFichaSaltosArriba(c, t)!=null)
                        saltosRojas.add(buscarFichaSaltosArriba(c, t));
                } else {        
                }
            }
        }
        saltosRojasDef = saltosRojas;
        return saltosRojas;
    }

    public void buscarSaltosReina(ArrayList<Ficha> saltos, Coordenada c, Tablero t){
        if(buscarFichaSaltosArriba(c, t)!=null) 
                saltos.add(buscarFichaSaltosArriba(c,t));
        if(buscarFichaSaltosAbajo(c, t)!=null)
                saltos.add(buscarFichaSaltosAbajo(c, t));
    }

    /**
     * Metodo para buscar fichas con saltos arriba disponibles
     * @param f Coordenada de la ficha 
     * @param t Tablero
     * @return Ficha, que tiene los saltos
     */
    private Ficha buscarFichaSaltosArriba(Coordenada f, Tablero t) {
        //Se revisa que haya una ficha y sea de diferente color
        Coordenada destinoSI = f.arribaIzquierdaSalto();
        Coordenada destinoSD = f.arribaDerechaSalto();
        Ficha ficha = null;

        if(!fueraDeLimite(destinoSI) 
            && t.getFichaTablero(f.arribaIzquierda()) != null
            && t.getFichaTablero(f.arribaIzquierda()).getColor() 
            != t.getFichaTablero(f).getColor()
            && t.getFichaTablero(destinoSI) == null) {
            ficha = t.getFichaTablero(f);
        }

        if(!fueraDeLimite(destinoSD) 
            && t.getFichaTablero(f.arribaDerecha()) != null
            && t.getFichaTablero(f.arribaDerecha()).getColor() 
            != t.getFichaTablero(f).getColor()
            && t.getFichaTablero(destinoSD) == null) {
            ficha = t.getFichaTablero(f);
        }
        return ficha;
    }

    /**
     * Metodo para buscar fichas con saltos abajo disponibles
     * @param f Coordenada de la ficha 
     * @param t Tablero
     * @return Ficha, que tiene los saltos
     */
    private Ficha buscarFichaSaltosAbajo(Coordenada f, Tablero t) {
        //Se revisa que haya una ficha y sea de diferente color
        Coordenada destinoSI = f.abajoIzquierdaSalto();
        Coordenada destinoSD = f.abajoDerechaSalto();
        Ficha ficha = null;

        if(!fueraDeLimite(destinoSI) 
            && t.getFichaTablero(f.abajoIzquierda()) != null 
            && t.getFichaTablero(f.abajoIzquierda()).getColor() 
            != t.getFichaTablero(f).getColor()
            && t.getFichaTablero(destinoSI) == null) {
            ficha = t.getFichaTablero(f);
        }

        if(!fueraDeLimite(destinoSD)
            && t.getFichaTablero(f.abajoDerecha()) != null 
            && t.getFichaTablero(f.abajoDerecha()).getColor() 
            != t.getFichaTablero(f).getColor()
            && t.getFichaTablero(destinoSD) == null) {
            ficha = t.getFichaTablero(f);
        }
        return ficha;
    }


    //Estos metodos est�n fijos a que solo se usaran fichas que hayan salido del proceso
    //De buscar fichas con saltos disponibles
    /**
     * Agrega los saltos(movimientos) a la ficha correspondiente
     * @param ficha
    * @param t
     */
    public void obtenerSaltosReina(Ficha ficha, Tablero t) {
        if(ficha.esReina()) {
            obtenerSaltosAbajo(ficha,t);
            obtenerSaltosArriba(ficha, t);
        }
    }
    /**
     * Guarda los saltos posibles en la ficha
     * @param f, ficha a la cual se le asignan los saltos
     * @param t, tablero
     */
    public void obtenerSaltosNegras(Ficha f, Tablero t) {
        if(f.getColor()==Ficha.NEGRA)
            obtenerSaltosAbajo(f, t);
    }

    /**
     * Guarda los saltos posibles en la ficha
     * @param f ficha a la cual se le asignan los saltos
     * @param t tablero
     */
    public void obtenerSaltosRojas(Ficha f, Tablero t) {
        if(f.getColor()==Ficha.ROJA) {
            f.getListMovs().clear();
            obtenerSaltosArriba(f,t);
        }
    }

    private void obtenerSaltosArriba(Ficha ficha, Tablero t) {
        //Se revisa que haya una ficha y sea de diferente color
        Coordenada destinoSI = ficha.getPosicion().arribaIzquierdaSalto();
        Coordenada destinoSD = ficha.getPosicion().arribaDerechaSalto();
        //Revisa ambos destinos 
        if(!fueraDeLimite(destinoSI) && ficha != null 
            && t.getFichaTablero(ficha.getPosicion().arribaIzquierda())!= null 
            && t.getFichaTablero(ficha.getPosicion().arribaIzquierda()).getColor() 
            != ficha.getColor() && t.getFichaTablero(destinoSI)==null) {
            ficha.getListMovs().add(new MovimientoSalto(ficha,destinoSI));
        }

        if(!fueraDeLimite(destinoSD) && ficha != null 
            && t.getFichaTablero(ficha.getPosicion().arribaDerecha())!= null
            && t.getFichaTablero(ficha.getPosicion().arribaDerecha()).getColor() 
            != ficha.getColor() && t.getFichaTablero(destinoSD)==null) {
            ficha.getListMovs().add(new MovimientoSalto(ficha,destinoSD));
        }
    }

    private void obtenerSaltosAbajo(Ficha ficha, Tablero t) {
        //Se revisa que haya una ficha y sea de diferente color
        Coordenada destinoSI = ficha.getPosicion().abajoIzquierdaSalto();
        Coordenada destinoSD = ficha.getPosicion().abajoDerechaSalto();
        ficha.getListMovs().clear();
        //if(validarSaltoAbajo(ficha.getPosicion(), destinoSI, ficha.getColor(),t ))

        //Revisa ambos destinos 
        if(!fueraDeLimite(destinoSI) && ficha != null 
            && t.getFichaTablero(ficha.getPosicion().abajoIzquierda())!= null 
            && t.getFichaTablero(ficha.getPosicion().abajoIzquierda()).getColor()
            != ficha.getColor() && t.getFichaTablero(destinoSI)==null) {
            ficha.getListMovs().add(new MovimientoSalto(ficha,destinoSI));    
        }

        if(!fueraDeLimite(destinoSD) && ficha != null 
            && t.getFichaTablero(ficha.getPosicion().abajoDerecha())!= null 
            && t.getFichaTablero(ficha.getPosicion().abajoDerecha()).getColor() 
            != ficha.getColor() && t.getFichaTablero(destinoSD) == null){
            ficha.getListMovs().add(new MovimientoSalto(ficha,destinoSD));
        }
    }

    public void realizarSalto(Ficha ficha, Movimiento mov, Tablero t) {
        Coordenada captura = hallarCaptura(ficha, mov);
        t.borrarFicha(captura);
        t.borrarFicha(mov.getOrigen());
        ficha.setPosition(mov.getDestino());
        t.setFichaTablero(ficha, ficha.getPosicion());
        volverReina(ficha);
        ficha.getListMovs().clear();
    }

    private Coordenada hallarCaptura(Ficha ficha, Movimiento mov) {
        Coordenada captura = null;
        int x = mov.getDestino().getX();
        int y = mov.getDestino().getY();

        if(ficha.getPosicion().arribaIzquierdaSalto().compararCoordenada(mov.getDestino())) {
            captura = new Coordenada(x+1,y+1);
        }
        if(ficha.getPosicion().arribaDerechaSalto().compararCoordenada(mov.getDestino())) {
            captura = new Coordenada(x-1,y+1);
        }
        if(ficha.getPosicion().abajoIzquierdaSalto().compararCoordenada(mov.getDestino())) {
            captura = new Coordenada(x+1,y-1);
        }
        if(ficha.getPosicion().abajoDerechaSalto().compararCoordenada(mov.getDestino())) {
            captura = new Coordenada(x-1,y-1);
        }

        return captura;
    }

    public void obtenerMovimientosReina(Ficha f, Tablero t) {
        if(f.esReina() && f!=null) {
            obtenerMovimientosAbajo(f, t);
            obtenerMovimientosArriba(f, t);
        }
    }

    public void obtenerMovimientosNegras(Ficha f, Tablero t){
        if(f.getColor() == Ficha.NEGRA && f!=null) {
            obtenerMovimientosAbajo(f, t);
        }
    }

    public void obtenerMovimientosRojas(Ficha f, Tablero t) {
        if(f.getColor() == Ficha.ROJA && f!=null) {
            f.getListMovs().clear();
            obtenerMovimientosArriba(f, t);
        }
    }

    public static void realizarMovimiento(Ficha ficha, Movimiento mov, Tablero t) {
        if(ficha!=null) {
            t.borrarFicha(mov.getOrigen());
            ficha.setPosition(mov.getDestino());
            t.setFichaTablero(ficha, ficha.getPosicion());
            volverReina(ficha);
            ficha.getListMovs().clear();
        }
    }

    private void obtenerMovimientosArriba(Ficha f, Tablero t) {
        //Arriba derecha
        if(validarMovArriba(f.getPosicion(), f.getPosicion().arribaDerecha(), t)) 
            f.getListMovs().add(new MovimientoNormal(f,
                f.getPosicion().arribaDerecha()));
        //Arriba izquierda
        if(validarMovArriba(f.getPosicion(),f.getPosicion().arribaIzquierda() , t))
            f.getListMovs().add(new MovimientoNormal(f,
                f.getPosicion().arribaIzquierda()));
    }

    private void obtenerMovimientosAbajo(Ficha f, Tablero t) {
        f.getListMovs().clear();
        //Abajo derecha
        if(validarMovAbajo(f.getPosicion(), f.getPosicion().abajoDerecha(), t)) 
            f.getListMovs().add(new MovimientoNormal(f,
                f.getPosicion().abajoDerecha()));
        //Abajo izquierda
        if(validarMovAbajo(f.getPosicion(),f.getPosicion().abajoIzquierda() , t))
            f.getListMovs().add(new MovimientoNormal(f,
                f.getPosicion().abajoIzquierda()));
    }

    private static void volverReina(Ficha ficha) {
        if(!ficha.esReina()){
            if( ficha.getColor() == Ficha.ROJA 
                && ficha.getPosicion().getY() == 0)
                ficha.hacerReina();
            else if(ficha.getColor() == Ficha.NEGRA 
                && ficha.getPosicion().getY() == 7)
                ficha.hacerReina();
        }
    }

    private static boolean validarMovAbajo(Coordenada origen,Coordenada destino,
            Tablero tablero) {
        return !fueraDeLimite(origen) && 
            !fueraDeLimite(destino) && tablero.getFichaTablero(destino)== null;
    }

    private static boolean validarMovArriba(Coordenada origen,Coordenada destino,
            Tablero tablero) {

        return !fueraDeLimite(origen) && 
            !fueraDeLimite(destino) && tablero.getFichaTablero(destino)== null;
    }
    /**
     * Metodo que indica si una coordenada est� dentro del tablero
     * @param c objeto Coordenada
     * @return false, si la coordeanada est� en el tablero, true si no est�
     */
    private static boolean fueraDeLimite(Coordenada c){
        return !((-1 < c.getX() && c.getX()< 8) 
            && (-1 < c.getY() && c.getY()< 8));
    }
	
    public boolean hayMovimientos(final Tablero tablero, int color) {
        ArrayList<Movimiento> listaMovs = new ArrayList<>();
        Coordenada c = null;
        Movimiento auxMov;
        Ficha auxFicha;
        int contFichas=0;
        for (int i=0; i<8; i++) {
            for(int j=0; j< 8; j++) {
                c = new Coordenada(i,j);
                auxFicha= tablero.getFichaTablero(c);
                if ((auxFicha != null) && (auxFicha.getColor() == color)) {
                    auxFicha.obtenerSalto(this, auxFicha, tablero);
                    contFichas+=1;
                    if(auxFicha.getListMovs().isEmpty())
                            auxFicha.obtenerMovimiento(this, auxFicha, tablero);

                    for(int k=0; k<auxFicha.getListMovs().size();k++) {
                       auxMov = auxFicha.getListMovs().get(k);
                       listaMovs.add(auxMov);  
                       auxFicha.getListMovs().clear();
                    }
                }
            }
        }
        return !(listaMovs.isEmpty() || contFichas<2);
    }
}
