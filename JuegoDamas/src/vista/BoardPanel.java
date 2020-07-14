package vista;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import logica.*;
/**
 *
 * @author Lenovo
 */
public class BoardPanel extends JPanel {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Tablero tablero;
    private ArrayList<Coordenada> movsDispo;

    public BoardPanel(Tablero t) {
        tablero = t;
        this.setBounds(0, 0, 600, 600);
        movsDispo = new ArrayList<>();
    }

    // Metodo que es llamado automaticamente por la maquina virtual Java cada vez que repinta
    @Override
    public void paint(Graphics g) {
        Image background = new ImageIcon("src/images/tablero600.jpg").getImage();
        super.paintComponent(g);
        // Mandamos que pinte la imagen en el panel
        if (background != null) {
            g.drawImage(background, 0, 0, 600, 600, null);
        }
        //Pintarfichas
        dibujarFichas(g,tablero);
        dibujarOpciones(movsDispo,g);
    }
    
    private void dibujarFichas(Graphics g, Tablero t) {
        Coordenada c = null;
        Ficha ficha = null;
        for(int i=0;i<Tablero.ALTURA;i++) {
            for(int j=0;j<Tablero.ANCHO;j++) {
                c = new Coordenada(j,i);
                ficha = t.getFichaTablero(c);
                if(ficha!=null) {
                    g.drawImage(ficha.getIcon().getImage(), ficha.getPosicion().getX()*75, 
                        ficha.getPosicion().getY()*75, 75, 75, null);
                }
            }
        }
    }

    public void dibujarOpciones(ArrayList<Coordenada> destinos, Graphics g) {
        if(!destinos.isEmpty()) {
            for(int i=0;i<destinos.size();i++) {
                    g.drawImage(new ImageIcon("src/images/indicador.png").getImage(), 
                        destinos.get(i).getX()*75, destinos.get(i).getY()*75, 75, 75, null);
            }
        }
    }

    public void mostrarOpcionesMovimiento( Ficha ficha) {

        ArrayList<Coordenada> destinos = new ArrayList<>();
        destinos.clear();
        if(!ficha.getListMovs().isEmpty()) {
            for(int i=0; i<ficha.getListMovs().size();i++) {
                destinos.add(ficha.getListMovs().get(i).getDestino());
            }
        }
        movsDispo = destinos;
    }

    public void borrarOpciones() {
        movsDispo.clear();
    }
    
    public ArrayList<Coordenada> getMovsDispo() {
        return movsDispo;
    }

    public void setMovsDispo(ArrayList<Coordenada> movsDispo) {
        this.movsDispo = movsDispo;
    }
}
