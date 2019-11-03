import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class EnviarGUI extends JFrame implements ActionListener{
  private JTextField tfRemite, tfDestino, tfAsunto;
  private JButton bEnviar, bEnviados, bCancelar, bSalir;
  private JTextArea taDatos;
  private JPanel panel1, panel2;

  private CorreoAD correo= new CorreoAD();

  public EnviarGUI(){

    tfRemite    =   new JTextField();
    tfDestino   =   new JTextField();
    tfAsunto    =   new JTextField();
    bEnviar     =   new JButton("Enviar");
    bEnviados   =   new JButton("Enviados");
    bCancelar   =   new JButton("Limpiar");
    bSalir      =   new JButton("Salir");
    taDatos     =   new JTextArea(20,40);
    panel1      =   new JPanel();
    panel2      =   new JPanel();

    bEnviar.addActionListener(this);
    bEnviados.addActionListener(this);
    bCancelar.addActionListener(this);
    bSalir.addActionListener(this);

    panel1.setLayout(new GridLayout(5,2));
    panel2.setLayout(new FlowLayout());

    panel1.add(new JLabel("Remitente: "));
    panel1.add(tfRemite);
    panel1.add(new JLabel("Destinatario: "));
    panel1.add(tfDestino);
    panel1.add(new JLabel("Asunto: "));
    panel1.add(tfAsunto);
    panel1.add(bEnviar);
    panel1.add(bEnviados);
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

    String remite= tfRemite.getText();
    String destino= tfDestino.getText();
    String asunto= tfAsunto.getText();
    String mensaje= taDatos.getText();

    if(remite.isEmpty() || destino.isEmpty() || asunto.isEmpty() || mensaje.isEmpty()){
      datos="VACIO";
    }
    else{
      datos=remite+"_"+destino+"_"+asunto+"_"+mensaje;
    }

    return datos;
  }

  public JPanel getPanel2(){
    return this.panel2;
  }

  public void actionPerformed(ActionEvent e){
    String datos, resultado, respuesta;

    if(e.getSource()==bEnviar){
      datos = obtenerDatos();

      if(datos.equals("VACIO"))
          taDatos.setText("Algun campo esta vacio...");
      else{
        respuesta= correo.captura(datos);
        taDatos.setText(respuesta);
      }
    }

    if(e.getSource()==bEnviados){
      datos=correo.consultar();
      taDatos.setText(datos);
    }

    if(e.getSource()==bSalir){
      panel2.setVisible(false);
      respuesta=correo.datosListaArchivo();
    }
  }

  public static void main(String args[]){
    EnviarGUI enviar= new EnviarGUI();
    enviar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
