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

  private CorreoAD correo= new CorreoAD();
  private CorreoDP act;

  public BuscarGUI(NuevoCorreoDP actualC, CorreoDP actual){

    act= actual;
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

  public JPanel getPanel2(){
    return this.panel2;
  }

  public void actionPerformed(ActionEvent e){
    String respuesta, datos;

    if(e.getSource()==bBuscarDestino){
      String envia= act.getCorreo();
      String destino= tfDestino.getText();
      respuesta=correo.consultarDestino(destino, envia);
      taDatos.setText(respuesta);
    }

    if(e.getSource()==bBuscarAsunto){
      String envia= act.getCorreo();
      String asunto= tfAsunto.getText();
      respuesta=correo.consultarAsunto(asunto, envia);
      taDatos.setText(respuesta);
    }


    if(e.getSource()==bSalir){
      panel2.setVisible(false);
    }
  }
}
