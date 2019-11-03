import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;
import java.awt.Color;

public class CorreoMGUI extends JPanel{

  private JLabel correo, asunto, fecha, espacio;
  private JPanel panel1;

  public CorreoMGUI(NuevoCorreoDP mensaje){
    correo = new JLabel(mensaje.getEnvia());
    asunto = new JLabel(mensaje.getAsunto());
    espacio = new JLabel("       ");

    panel1 = new JPanel();

    espacio.setOpaque(true);
    espacio.setBackground(Color.WHITE);
    setBackground(Color.WHITE);

    correo.setPreferredSize(new Dimension(200, 1));
    asunto.setPreferredSize(new Dimension(105, 1));

    panel1.setLayout(new GridLayout(1,3));
    panel1.add(correo);
    panel1.add(espacio);
    panel1.add(asunto);

    this.add(panel1);
    setVisible(true);
  }
}
