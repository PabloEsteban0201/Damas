package logica;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class FichaNegra extends Ficha{

    public FichaNegra(Coordenada c) {
        estadoFicha= new EstadoNormalNegra();
        posicion = c;
        stringRep= "negra";
        valor = Ficha.NEGRA;
    }

    @Override
    public boolean esReina() {
        return (valor== Ficha.NEGRA_REINA);
    }

    @Override
    public int getColor() {
        return Ficha.NEGRA;
    }

    @Override
    public void hacerReina() {
        estadoFicha = new EstadoReina();
        valor = Ficha.NEGRA_REINA; 
        stringRep= "reinaN";
    }

    @Override
    public ImageIcon getIcon() {
        ImageIcon imagen = null;
        if(valor == Ficha.NEGRA) {
            imagen =new ImageIcon("src/images/fichaNegra.png");
        }

        if(valor == Ficha.NEGRA_REINA) {
            imagen = new ImageIcon("src/images/fichaNegraReina.png");
        }

        return imagen;
    }

    @Override
    public Ficha clonar() {
        Ficha ficha = null;
        try {
            ficha = (FichaNegra) clone();
            if (valor == Ficha.NEGRA_REINA)
                 ficha.hacerReina();
            System.out.println("Objeto clonado");
        } catch (CloneNotSupportedException e) {
            JOptionPane.showMessageDialog(null, "No se pudo clonar."+e.getMessage(),
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
        return ficha;		
    }	
}
