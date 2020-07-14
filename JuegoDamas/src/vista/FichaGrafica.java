package vista;

import javax.swing.ImageIcon;

import logica.*;

public class FichaGrafica {

    private ImageIcon imagen;
    private String rtaImagen;
    private Coordenada pos;
    private int color;
    
    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    public String getRtaImagen() {
        return rtaImagen;
    }

    public void setRtaImagen(String rtaImagen) {
        this.rtaImagen = rtaImagen;
    }

    public Coordenada getPos() {
        return pos;
    }

    public void setPos(Coordenada pos) {
        this.pos = pos;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
