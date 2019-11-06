import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;
import java.awt.Color;

public class CorreoMGUI extends JPanel implements ActionListener{

  private JLabel correo, asunto, fecha, espacio;
  private JPanel panel1;
  private JButton bVer;

  private NuevoCorreoDP mensajeActual;
  private String usuario;

  public CorreoMGUI(NuevoCorreoDP mensaje, String cuenta){

    mensajeActual = mensaje;
    usuario = cuenta;

    correo = new JLabel(mensaje.getEnvia());
    asunto = new JLabel(mensaje.getAsunto());
    espacio = new JLabel("       ");
    bVer = new JButton("Ver correo");

    panel1 = new JPanel();

    bVer.addActionListener(this);

    espacio.setOpaque(true);
    espacio.setBackground(Color.WHITE);
    setBackground(Color.WHITE);

    correo.setPreferredSize(new Dimension(200, 1));
    asunto.setPreferredSize(new Dimension(105, 1));

    panel1.setLayout(new GridLayout(1,3));
    panel1.add(correo);
    panel1.add(asunto);
    panel1.add(bVer);

    this.add(panel1);
    setVisible(true);

    mostrarCorreo(mensaje);
  }

  public CorreoMGUI(NuevoCorreoDP mensaje, String cuenta, boolean seEnvia){

    mensajeActual = mensaje;
    usuario = cuenta;

    correo = new JLabel(mensaje.getRecibe());
    asunto = new JLabel(mensaje.getAsunto());
    espacio = new JLabel("       ");
    bVer = new JButton("Ver correo");

    panel1 = new JPanel();

    bVer.addActionListener(this);

    espacio.setOpaque(true);
    espacio.setBackground(Color.WHITE);
    setBackground(Color.WHITE);

    correo.setPreferredSize(new Dimension(200, 1));
    asunto.setPreferredSize(new Dimension(105, 1));

    panel1.setLayout(new GridLayout(1,3));
    panel1.add(correo);
    panel1.add(asunto);
    panel1.add(bVer);

    this.add(panel1);
    setVisible(true);

    mostrarCorreo(mensaje);
  }

  public void actionPerformed(ActionEvent e){
    String datos;

    if(e.getSource()==bVer){
      new VerCorreoGUI(mensajeActual, usuario);
    }

  }

  public void mostrarCorreo(NuevoCorreoDP mensaje){
    JOptionPane.showMessageDialog(null, mensaje.toString());
  }
}
