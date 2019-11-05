import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class BandejaGUI extends JPanel implements ActionListener{
  //private JTextField
  private JButton bSalir;
  private JTextArea taDatos;
  private JPanel panel1, panel2;

  private CorreoAD correo= new CorreoAD();

  //Usuario Actual
  private CorreoDP conexion;

  public BandejaGUI(CorreoDP actual, JPanel bandeja){
    conexion    =   actual;
    bSalir      =   new JButton("Salir");
    taDatos     =   new JTextArea(20,40);
    panel1      =   new JPanel();
    panel2      =   new JPanel();

    bSalir.addActionListener(this);

    panel1.setLayout(new GridLayout(3,2));
    panel2.setLayout(new GridLayout(2,1));
    this.setLayout(new FlowLayout());

    panel1.add(new JLabel("Carpeta"));
    panel1.add(bSalir);
    //panel1.add(new JLabel("Cuerpo: "));

    panel2.add(panel1);
    panel2.add(bandeja);



    add(panel2);
    setSize(500,500);
    setVisible(true);
  }


  public JPanel getPanel2(){
    return this.panel2;
  }

  public void actionPerformed(ActionEvent e){
    if(e.getSource()==bSalir){
      panel2.setVisible(false);
    }
  }
}
