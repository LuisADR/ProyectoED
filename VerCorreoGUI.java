import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VerCorreoGUI extends JFrame implements ActionListener{
  private JMenuBar mbPrincipal;
  private JMenu menuCarpetas;
  private JMenuItem miCarpetas[], miSalir;

  private JTextField tfRemite, tfDestino, tfAsunto;
  private JTextArea taDatos;
  private JPanel panel1, panel2;

  private CorreoAD correo= new CorreoAD();
  private NuevoCorreoDP mensaje;

  private ArrayList<String> carpetas = new ArrayList<String>();

  public VerCorreoGUI(/*NuevoCorreoDP datos*/){

    //mensaje = datos;

    mbPrincipal   =   new JMenuBar();
    menuCarpetas  =   new JMenu("Mover a");
    miSalir       =   new JMenuItem("Cerrar");

    tfRemite    =   new JTextField();
    tfDestino   =   new JTextField();
    tfAsunto    =   new JTextField();

    taDatos     =   new JTextArea(11,40);
    panel1      =   new JPanel();
    panel2      =   new JPanel();


    crearCarpetas();

    //Creamos la configuracion del panel
    panel1.setLayout(new GridLayout(5,2));
    panel2.setLayout(new GridLayout(2,1));
    this.setLayout(new FlowLayout());

    panel1.add(new JLabel("Remitente: "));
    panel1.add(tfRemite);

    panel1.add(new JLabel("Destinatario: "));
    panel1.add(tfDestino);

    panel1.add(new JLabel("Asunto: "));
    panel1.add(tfAsunto);

    panel2.add(panel1);
    panel2.add(new JScrollPane(taDatos));

    mbPrincipal.add(menuCarpetas);
    mbPrincipal.add(miSalir);

    this.add(panel2);
    setJMenuBar(mbPrincipal);
    setSize(500,500);
    setVisible(true);

  }

  public void crearCarpetas(){

    //Añadimos al menu
    String[] nombres = new String[carpetas.size()];
    nombres = carpetas.toArray(nombres);
    miCarpetas = new JMenuItem[carpetas.size()];

    menuCarpetas.removeAll();

    //Volemos a cargar el menu
    for (int i = 0; i < miCarpetas.length;  i++) {
      miCarpetas[i] = new JMenuItem(nombres[i]);
      miCarpetas[i].addActionListener(this);
      menuCarpetas.add(miCarpetas[i]);
    }

    SwingUtilities.updateComponentTreeUI(this);

  }

  public void desplegarDatos(){

  }

  public void actionPerformed(ActionEvent e){
    String datos;


  }

  public static void main (String args[]){
    VerCorreoGUI inicio = new VerCorreoGUI();
    inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

}
