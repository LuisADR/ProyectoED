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

  //private CorreoAD correo= new CorreoAD();

  private EnviarGUI enviar= new EnviarGUI();
  private BuscarGUI buscar= new BuscarGUI();
  private PrincipalGUI principal= new PrincipalGUI();
  private ImportantesGUI importantes= new ImportantesGUI();
  private BandejaGUI bandeja= new BandejaGUI();

  private ArrayList<String> carpetas = new ArrayList<String>();

  private JPanel panel;

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

    panel         =   new JPanel();

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

    setJMenuBar(mbPrincipal);
    setSize(500,500);
    setVisible(true);
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
      panel.setVisible(false);
      //panel=bandeja.getPanel2();
      panel.setVisible(true);
      add(panel);
      setVisible(true);
    }

    if(event.getSource()==miPrincipal){
      panel.setVisible(false);
      panel=principal.getPanel2();
      panel.setVisible(true);
      add(panel);
      setVisible(true);
    }

    if(event.getSource()==miCrearCarpeta){
      String nuevaCarpeta= JOptionPane.showInputDialog("Nombre nueva carpeta");
      addCarpeta( nuevaCarpeta );
    }

    if(event.getSource()==miEnviar){
      panel.setVisible(false);
      panel=enviar.getPanel2();
      panel.setVisible(true);
      add(panel);
      setVisible(true);
    }

    if(event.getSource()==miBuscar){
      panel.setVisible(false);
      panel=buscar.getPanel2();
      panel.setVisible(true);
      add(panel);
      setVisible(true);
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
