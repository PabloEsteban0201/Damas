/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegodamas;

import controlador.Juego;

/**
 *
 * @author forer
 */
public class JuegoDamas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Juego juego = Juego.getInstance();
        juego.iniciarJuego();
    }
    
}
