import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class PrincipalGUI extends JFrame implements ActionListener{
  //private JTextField
  private JButton bSalir;
  private JTextArea taDatos;
  private JPanel panel1, panel2;

  //private CorreoAD correo= new CorreoAD();

  public PrincipalGUI(){

    bSalir      =   new JButton("Salir");
    taDatos     =   new JTextArea(20,40);
    panel1      =   new JPanel();
    panel2      =   new JPanel();

    bSalir.addActionListener(this);

    panel1.setLayout(new GridLayout(5,2));
    panel2.setLayout(new FlowLayout());

    panel1.add(new JLabel("Bandeja de entrada "));
    panel1.add(bSalir);
    //panel1.add(new JLabel("Cuerpo: "));

    panel2.add(panel1);
    panel2.add(new JScrollPane(taDatos));



    add(panel2);
    setSize(500,500);
    setVisible(false);
  }


  public JPanel getPanel2(){
    return this.panel2;
  }

  public void actionPerformed(ActionEvent e){
    if(e.getSource()==bSalir){
      panel2.setVisible(false);
    }
  }

  public static void main(String args[]){
    PrincipalGUI principal= new PrincipalGUI();
    principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
