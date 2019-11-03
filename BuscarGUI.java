import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;


// PROBANDO PROBANDO XD

public class BuscarGUI extends JPanel implements ActionListener{
  private JTextField tfDestino, tfAsunto;
  private JButton bBuscarDestino, bBuscarAsunto, bSalir;
  private JTextArea taDatos;
  private JPanel panel1, panel2;

  //private CorreoAD correo= new CorreoAD();

  public BuscarGUI(){

    tfDestino   =   new JTextField();
    tfAsunto    =   new JTextField();

    bBuscarDestino=   new JButton("Buscar por Destinatario");
    bBuscarAsunto=   new JButton("Buscar por Asunto");
    bSalir      =   new JButton("Salir");

    taDatos     =   new JTextArea(11,30);

    panel1      =   new JPanel();
    panel2      =   new JPanel();

    bBuscarDestino.addActionListener(this);
    bBuscarAsunto.addActionListener(this);
    bSalir.addActionListener(this);

    panel1.setLayout(new GridLayout(5,2));
    panel2.setLayout(new GridLayout(2,1));

    panel1.add(new JLabel("Destinatario: "));
    panel1.add(tfDestino);

    panel1.add(new JLabel("Asunto: "));
    panel1.add(tfAsunto);

    panel1.add(bBuscarDestino);
    panel1.add(bBuscarAsunto);
    panel1.add(bSalir);
    //panel1.add(new JLabel("Cuerpo: "));

    panel2.add(panel1);
    panel2.add(new JScrollPane(taDatos));



    add(panel2);
    setSize(500,500);
    setVisible(true);
  }

  public String obtenerDatos(){
    String datos="";

    String destino= tfDestino.getText();
    String asunto= tfAsunto.getText();
    String mensaje= taDatos.getText();

    if(destino.isEmpty() || asunto.isEmpty()){
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
}
