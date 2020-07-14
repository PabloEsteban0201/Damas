package logica;

public final class Tablero {
	
    public static final int ALTURA = 8;
    public static final int ANCHO = 8;
    private final Ficha tablero[][] = new Ficha[ALTURA][ANCHO];

    public Tablero() {
        inicializarTablero();
    }

    public void inicializarTablero() {
        //Llenar fichas Negras
        for(int i=0;i<3;i++) {
            for(int j=0;j<ANCHO;j++) {
                if(i%2 == 0) {//Filas Pares
                    if(j%2==0) {//Columnas Pares
                        tablero[i][j] = new FichaNegra(new Coordenada(j,i));
                    }else {
                        tablero[i][j] = null;
                    }
                }else {
                    if(j%2!=0) {
                        tablero[i][j] = new FichaNegra(new Coordenada(j,i));
                    }else {
                        tablero[i][j] = null;
                    }
                }
            }
        }

        //Llenar fichas Rojas
        for(int i=5;i<ALTURA;i++) {
            for(int j=0;j<ANCHO;j++) {
                if(i%2 != 0) {//Filas Impares
                    if(j%2!=0) {//Columnas Impares
                        tablero[i][j] = new FichaRoja(new Coordenada(j,i));
                    }else {
                        tablero[i][j] = null;
                    }
                }else {
                    if(j%2==0) {
                            tablero[i][j] = new FichaRoja(new Coordenada(j,i));
                    }else {
                            tablero[i][j] = null;
                    }
                }
            }
        }

        //Llenar espacios vacios
        for(int i=3;i<5;i++) {
            for(int j=0;j<ANCHO;j++) {
                tablero[i][j] = null;
            }
        }
    }

    public void borrarFicha(Coordenada c) {
        if(tablero[c.getY()][c.getX()]!=null) {
            tablero[c.getY()][c.getX()]=null;
        }
    }
    
    public Ficha getFichaTablero(Coordenada c) {
        return tablero[c.getY()][c.getX()];
    }

    public void setFichaTablero(Ficha ficha, Coordenada c) {
        tablero[c.getY()][c.getX()] = ficha;
        ficha.setPosition(c);
    }

    public void mostrarTablero() {
        for(int i=0;i<ALTURA;i++) {
            for(int j=0;j<ANCHO;j++) {
                if(tablero[i][j] != null) {
                    System.out.print(tablero[i][j].getValor()+" ");
                }else {
                    System.out.print("0 ");
                }
            }
            System.out.println(" ");
        }
        System.out.println("------------------\n");
    }
}
