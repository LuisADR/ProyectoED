import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InicioGUI extends JFrame implements ActionListener{
  private JMenuBar mbPrincipal;
  private JMenu menuCarpetas, menuBandeja, menuCorreo;
  private JMenuItem miPrincipal, miImportantes, miSpam,  miCrearCarpeta;
  private JMenuItem miEnviar, miBuscar, miSalir;

  //private CorreoAD correo= new CorreoAD();

  private EnviarGUI enviar= new EnviarGUI();
  private BuscarGUI buscar= new BuscarGUI();
  private PrincipalGUI principal= new PrincipalGUI();
  private ImportantesGUI importantes= new ImportantesGUI();
  private BandejaGUI bandeja= new BandejaGUI();

  private JPanel panel;

  public InicioGUI(){
    mbPrincipal   =   new JMenuBar();
    menuBandeja   =   new JMenu("Bandeja");
    menuCorreo    =   new JMenu("Correo");
    miPrincipal   =   new JMenuItem("Principal");
    menuCarpetas  =   new JMenu("Carpetas");
    miCrearCarpeta=   new JMenuItem("Crear Carpeta");
    miImportantes =   new JMenuItem("Importantes");
    miSpam        =   new JMenuItem("Spam");
    miEnviar      =   new JMenuItem("Enviar");
    miBuscar      =   new JMenuItem("Buscar");
    miSalir       =   new JMenuItem("Salir");

    panel         =   new JPanel();

    miPrincipal.addActionListener(this);
    miImportantes.addActionListener(this);
    miSpam.addActionListener(this);
    miCrearCarpeta.addActionListener(this);
    miEnviar.addActionListener(this);
    miBuscar.addActionListener(this);
    miSalir.addActionListener(this);

    menuBandeja.add(miPrincipal);
    menuBandeja.add(menuCarpetas);
    menuCarpetas.add(miImportantes);
    menuCarpetas.add(miSpam);
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

    if(event.getSource()==miImportantes){
      panel.setVisible(false);
      panel=importantes.getPanel2();
      panel.setVisible(true);
      add(panel);
      setVisible(true);
    }

    if(event.getSource()==miSpam){
      panel.setVisible(false);
      panel=bandeja.getPanel2();
      panel.setVisible(true);
      add(panel);
      setVisible(true);
    }

    if(event.getSource()==miCrearCarpeta){
      String nuevaCarpeta= JOptionPane.showInputDialog("Nombre nueva carpeta");
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
