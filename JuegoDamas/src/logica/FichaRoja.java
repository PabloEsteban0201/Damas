package logica;

import javax.swing.*;

public class FichaRoja extends Ficha{
	
    public FichaRoja(Coordenada c) {
        estadoFicha= new EstadoNormalRoja();
        valor= Ficha.ROJA;
        stringRep="roja";
        posicion=c;
    }
    @Override
    public boolean esReina() {
        return (valor==Ficha.ROJA_REINA);
    }

    @Override
    public int getColor() {
        return Ficha.ROJA;
    }

    @Override
    public void hacerReina(){
        estadoFicha = new EstadoReina();
        valor = Ficha.ROJA_REINA; 
        stringRep= "reinaR";
    }

    @Override
    public ImageIcon getIcon() {
        ImageIcon imagen = null;
        if(valor == Ficha.ROJA) {
                imagen =new ImageIcon("src/images/fichaRoja.png");
        }

        if(valor == Ficha.ROJA_REINA) {
                imagen = new ImageIcon("src/images/fichaRojaReina.png");
        }

        return imagen;
    }
    @Override
    public Ficha clonar() {
        Ficha ficha = null;
        try {
            ficha = (FichaRoja) clone();
            if (valor == Ficha.ROJA_REINA)
                 ficha.hacerReina();
            System.out.println("Objeto clonado");
        } catch (CloneNotSupportedException e) {
            JOptionPane.showMessageDialog(null, "No se pudo clonar."+e.getMessage(),
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
        return ficha;		
    }


}
