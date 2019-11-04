import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InicioGUI extends JFrame implements ActionListener{
  private JMenuBar mbPrincipal;
  private JMenu menuCarpetas, menuBandeja, menuCorreo;
  private JMenuItem miPrincipal, miCrearCarpeta;
  private JMenuItem miEnviar, miBuscar, miSalir;
  private JMenuItem miCarpetas[];

  private CorreoAD correo= new CorreoAD();

  /*private RedaccionGUI enviar= new RedaccionGUI();
  private BuscarGUI buscar= new BuscarGUI();
  private PrincipalGUI principal= new PrincipalGUI();
  private BandejaGUI bandeja= new BandejaGUI();*/

  private ArrayList<String> carpetas = new ArrayList<String>();

  private JPanel panel1, loggin, servicio, pContrasena;
  private JTextField tfcorreo, contrasena;
  private JButton bIniciar, bRegistrar;

  //Tener la informacion del Usuario
  private CorreoDP actual;
  private NuevoCorreoDP actualC;

  public InicioGUI(){
    mbPrincipal   =   new JMenuBar();
    menuBandeja   =   new JMenu("Bandeja");
    menuCorreo    =   new JMenu("Correo");
    miPrincipal   =   new JMenuItem("Principal");
    menuCarpetas  =   new JMenu("Carpetas");
    miCrearCarpeta=   new JMenuItem("Crear Carpeta");
    miEnviar      =   new JMenuItem("Enviar");
    miBuscar      =   new JMenuItem("Buscar");
    miSalir       =   new JMenuItem("Salir");

    miCarpetas    =   new JMenuItem[0];

    panel1        =   new JPanel();
    loggin        =   new JPanel();
    servicio      =   new JPanel();
    pContrasena   =   new JPanel();

    tfcorreo      =   new JTextField();
    contrasena    =   new JTextField();
    bIniciar      =   new JButton("Iniciar Sesion");
    bRegistrar    =   new JButton("Registrar");

    //Panel de Loggin

    bIniciar.addActionListener(this);
    bRegistrar.addActionListener(this);

    servicio.setLayout(new GridLayout(1,3));
    servicio.add(new JLabel("Usuario"));
    servicio.add(tfcorreo);

    pContrasena.setLayout(new GridLayout(1,2));
    pContrasena.add(new JLabel("Contrasena"));
    pContrasena.add(contrasena);

    loggin.setLayout(new GridLayout(4,1));
    loggin.add(servicio);
    loggin.add(pContrasena);
    loggin.add(bIniciar);
    loggin.add(bRegistrar);

    //Panel del JFrame
    panel1.setLayout(new FlowLayout());

    miPrincipal.addActionListener(this);
    miCrearCarpeta.addActionListener(this);
    miEnviar.addActionListener(this);
    miBuscar.addActionListener(this);
    miSalir.addActionListener(this);

    menuBandeja.add(miPrincipal);
    menuBandeja.add(menuCarpetas);
    menuCarpetas.add(miCrearCarpeta);

    menuCorreo.add(miEnviar);
    menuCorreo.add(miBuscar);
    menuCorreo.add(miSalir);

    mbPrincipal.add(menuBandeja);
    mbPrincipal.add(menuCorreo);

    panel1.add(loggin);

    this.add(panel1);

    setJMenuBar(mbPrincipal);
    setSize(500,500);
    setVisible(true);

    menuBandeja.setEnabled(false);
    menuCorreo.setEnabled(false);
  }

  public void addCarpeta(String nombre){
    carpetas.add(nombre);
    String[] nombres = new String[carpetas.size()];
    nombres = carpetas.toArray(nombres);
    miCarpetas = new JMenuItem[carpetas.size()];

    menuCarpetas.removeAll();
    menuCarpetas.add(miCrearCarpeta);

    for (int i = 0; i < miCarpetas.length;  i++) {
      miCarpetas[i] = new JMenuItem(nombres[i]);
      miCarpetas[i].addActionListener(this);
      menuCarpetas.add(miCarpetas[i]);
    }

    SwingUtilities.updateComponentTreeUI( this );
  }

  public void actionPerformed(ActionEvent event){

    if(event.getSource()==miPrincipal){
      panel1.removeAll();
      panel1.add(new PrincipalGUI(actual));
      panel1.revalidate();
      panel1.repaint();
      pack();
      setSize(600,500);
    }

    if(event.getSource()==miCrearCarpeta){
      String nuevaCarpeta= JOptionPane.showInputDialog("Nombre nueva carpeta");
      addCarpeta( nuevaCarpeta );
    }

    if(event.getSource()==miEnviar){
      panel1.removeAll();
      panel1.add(new RedaccionGUI(actual));
      panel1.revalidate();
      panel1.repaint();
      pack();
      setSize(500,500);
    }

    if(event.getSource()==miBuscar){
      panel1.removeAll();
      panel1.add(new BuscarGUI(actualC, actual));
      panel1.revalidate();
      panel1.repaint();
      pack();
      setSize(500,500);
    }

    if(event.getSource()==bIniciar){
      actual = correo.crearConexion(tfcorreo.getText(), contrasena.getText());
      if(actual == null) JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
      if(actual != null){
        JOptionPane.showMessageDialog(null, actual.toString());
        menuBandeja.setEnabled(true);
        menuCorreo.setEnabled(true);
        panel1.removeAll();
        panel1.revalidate();
        panel1.repaint();
      }
    }

    if(event.getSource()==bRegistrar){
      new CrearUsuarioGUI();
    }

    if(event.getSource()== miSalir){
			System.exit(0);
		}

  }

  public static void main (String args[]){
    InicioGUI inicio = new InicioGUI();
    inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

}
