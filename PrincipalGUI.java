import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class PrincipalGUI extends JPanel implements ActionListener{
  //private JTextField
  private JButton bSalir;
  private JTextArea taDatos;
  private JPanel panel1, panel2, panelCorreo;

  private CorreoAD correo= new CorreoAD();

  //Usuario Actual
  private CorreoDP conexion;

  public PrincipalGUI(CorreoDP actual){
    conexion    =   actual;
    bSalir      =   new JButton("Salir");
    taDatos     =   new JTextArea(20,40);
    panel1      =   new JPanel();
    panel2      =   new JPanel();
    panelCorreo =   correo.consultarJP(conexion.getCorreo());

    bSalir.addActionListener(this);

    panel1.setLayout(new GridLayout(1,2));
    panel2.setLayout(new GridLayout(2,1));

    panel1.add(new JLabel("Bandeja de entrada "));
    panel1.add(bSalir);
    //panel1.add(new JLabel("Cuerpo: "));

    panel2.add(panel1);
    panel2.add(panelCorreo);



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
