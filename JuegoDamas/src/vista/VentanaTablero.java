package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

import logica.*;
import controlador.*;
/**
*
* @author Lenovo
*/
public class VentanaTablero extends JFrame implements ActionListener{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Tablero tableroPrincipal;
    private BoardPanel board ;
    private GestorMovimiento mainGestor;
    public final static int PRESIONADO = 1;
    public static final int DESPRESIONADO = 0;
    private int estadoClick = DESPRESIONADO;
    private Ficha fichaPresionada = null;
    private Juego juegoNuevo;
    private final JPanel contentPane;
    private final JLabel lblNombreNegras;
    private final JLabel lblNombreRojas;
    private final JLabel lblTurno;
    private final JPanel panelDatos;
    private final JButton btnNuevoJuego;
   /**
     * @param t
     * @param gm
     * @param j
    */
    public VentanaTablero(Tablero t, GestorMovimiento gm, Juego j) { 
        setTitle("Damas");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setIconImage(new ImageIcon("src/images/tablero600.jpg").getImage());

        this.tableroPrincipal = t;
        this.mainGestor = gm;
        this.juegoNuevo = j;

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 610, 610);
        panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
        contentPane.add(panel);
        panel.setLayout(null);

        panelDatos = new JPanel();
        panelDatos.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        panelDatos.setBackground(Color.WHITE);
        panelDatos.setBounds(635, 100, 120, 300);
        panelDatos.setLayout(null);
        contentPane.add(panelDatos);

        JLabel lblJugador1 = new JLabel("Jugador 1: ");
        lblJugador1.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 14));
        lblJugador1.setBounds(10, 25, 69, 14);
        panelDatos.add(lblJugador1);

        lblNombreNegras = new JLabel("");
        lblNombreNegras.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 13));
        lblNombreNegras.setBounds(10, 45, 80, 14);
        lblNombreNegras.setText(juegoNuevo.getJugador1().getNombre());
        panelDatos.add(lblNombreNegras);

        JLabel lblNegras = new JLabel("Negras");
        lblNegras.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 13));
        lblNegras.setBounds(10, 65, 46, 14);
        panelDatos.add(lblNegras);

        JLabel lblJugador2 = new JLabel("Jugador 2: ");
        lblJugador2.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 14));
        lblJugador2.setBounds(10, 100, 69, 14);
        panelDatos.add(lblJugador2);

        lblNombreRojas = new JLabel("");
        lblNombreRojas.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 13));
        lblNombreRojas.setBounds(10, 120, 80, 14);
        lblNombreRojas.setText(juegoNuevo.getJugador2().getNombre());
        panelDatos.add(lblNombreRojas);

        JLabel lblRojas = new JLabel("Rojas");
        lblRojas.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 13));
        lblRojas.setBounds(10, 140, 46, 14);
        panelDatos.add(lblRojas);

        JLabel lblMoviendo = new JLabel("Moviendo: ");
        lblMoviendo.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 14));
        lblMoviendo.setBounds(10, 193, 69, 14);
        panelDatos.add(lblMoviendo);

        lblTurno = new JLabel("");
        lblTurno.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 13));
        lblTurno.setBounds(10, 213, 80, 14);
        panelDatos.add(lblTurno);

        board = new BoardPanel(this.tableroPrincipal);
        board.setBounds(5, 5, 600, 600);
        board.addMouseListener(juegoNuevo);		
        panel.add(board);

        btnNuevoJuego = new JButton("Nuevo Juego");
        btnNuevoJuego.setBounds(635, 420, 120, 20);
        btnNuevoJuego.addActionListener(this);
        contentPane.add(btnNuevoJuego);

        this.setSize(780, 660);
        this.setLayout(null);		
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
	
    public Coordenada obtenerCoordenada(int x, int y) {
        Coordenada click = null;
        for(int i=0; i<Tablero.ALTURA;i++) {
            for(int j=0; j<Tablero.ANCHO;j++) {
                if(x>(j*75) && x<((j*75)+75) 
                    && y>(i*75) && y<((i*75)+75)) {
                    click = new Coordenada(j,i);
                }
            }
        }
        return click;
    }

    public boolean  moverFicha(int x, int y) {
        boolean mueve = false;
        Coordenada c = null;
        if(!fichaPresionada.getListMovs().isEmpty()) {
            for(int i=0; i<fichaPresionada.getListMovs().size();i++) {
                c = fichaPresionada.getListMovs().get(i).getDestino();
                if(c.compararCoordenada(obtenerCoordenada(x, y))) {
                    fichaPresionada.realizarMovimiento(
                    fichaPresionada.getListMovs().get(i), fichaPresionada, tableroPrincipal, mainGestor);
                    fichaPresionada.getListMovs().clear();
                    mueve = true;
                }
            }
        }
        return mueve;
    }

    public boolean  saltarFicha(int x, int y) {
        boolean mueve = false;
        Coordenada c = null;
        if(fichaPresionada.getListMovs().size()!=0) {
            for(int i=0; i<fichaPresionada.getListMovs().size();i++) {
                c = fichaPresionada.getListMovs().get(i).getDestino();
                if(c.compararCoordenada(obtenerCoordenada(x, y))) {
                    fichaPresionada.realizarSalto(mainGestor, fichaPresionada.getListMovs().get(i)
                                    , fichaPresionada, tableroPrincipal);
                    fichaPresionada.getListMovs().clear();
                    mueve = true;
                }
            }
        }
        return mueve;
    }

    public Tablero getT() {
        return tableroPrincipal;
    }



    public void setT(Tablero t) {
        this.tableroPrincipal = t;
    }



    public BoardPanel getBoard() {
        return board;
    }



    public void setBoard(BoardPanel board) {
        this.board = board;
    }



    public GestorMovimiento getGm() {
        return mainGestor;
    }



    public void setGm(GestorMovimiento gm) {
        this.mainGestor = gm;
    }



    public int getEstadoClick() {
        return estadoClick;
    }



    public void setEstadoClick(int estadoClick) {
        this.estadoClick = estadoClick;
    }



    public Ficha getFichaPresionada() {
        return fichaPresionada;
    }



    public void setFichaPresionada(Ficha fichaPresionada) {
        this.fichaPresionada = fichaPresionada;
    }



    public static int getPresionado() {
        return PRESIONADO;
    }



    public static int getDespresionado() {
        return DESPRESIONADO;
    }
    public void setTurno(String turno) {
        this.lblTurno.setText(turno);
        panelDatos.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnNuevoJuego) {
            this.dispose();
            juegoNuevo.reiniciarJuego();
            juegoNuevo = Juego.getInstance();
            juegoNuevo.iniciarJuego();
        }
    }

}
