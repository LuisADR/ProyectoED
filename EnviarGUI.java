import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class EnviarGUI extends JFrame implements ActionListener{
  private JTextField tfDestino, tfAsunto;
  private JButton bEnviar, bCancelar, bSalir;
  private JTextArea taDatos;
  private JPanel panel1, panel2;

  //private CorreoAD correo= new CorreoAD();

  public EnviarGUI(){

    tfDestino   =   new JTextField();
    tfAsunto    =   new JTextField();
    bEnviar     =   new JButton("Enviar");
    bCancelar   =   new JButton("Limpiar");
    bSalir      =   new JButton("Salir");
    taDatos     =   new JTextArea(20,40);
    panel1      =   new JPanel();
    panel2      =   new JPanel();

    bEnviar.addActionListener(this);
    bCancelar.addActionListener(this);
    bSalir.addActionListener(this);

    panel1.setLayout(new GridLayout(5,2));
    panel2.setLayout(new FlowLayout());

    panel1.add(new JLabel("Destinatario: "));
    panel1.add(tfDestino);
    panel1.add(new JLabel("Asunto: "));
    panel1.add(tfAsunto);
    panel1.add(bEnviar);
    panel1.add(bCancelar);
    panel1.add(bSalir);
    //panel1.add(new JLabel("Cuerpo: "));

    panel2.add(panel1);
    panel2.add(new JScrollPane(taDatos));



    add(panel2);
    setSize(500,500);
    setVisible(false);
  }

  public String obtenerDatos(){
    String datos="";

    String destino= tfDestino.getText();
    String asunto= tfAsunto.getText();
    String mensaje= taDatos.getText();

    if(destino.isEmpty() || asunto.isEmpty() || mensaje.isEmpty()){
      datos="VACIO";
    }
    else{
      datos=destino+"_"+asunto+"_"+mensaje;
    }

    return datos;
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
    EnviarGUI enviar= new EnviarGUI();
    enviar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
