import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class RedaccionGUI extends JPanel implements ActionListener{
  private JTextField tfRemite, tfDestino, tfAsunto;
  private JButton bEnviar, bEnviados, bCancelar, bSalir;
  private JTextArea taDatos;
  private JPanel panel1, panel2;

  private CorreoAD correo= new CorreoAD();

  private CorreoDP act;

  public RedaccionGUI(CorreoDP actual){

    //tfRemite    =   new JTextField();
    act         =   actual;
    tfDestino   =   new JTextField();
    tfAsunto    =   new JTextField();
    bEnviar     =   new JButton("Enviar");
    bEnviados   =   new JButton("Enviados");
    bCancelar   =   new JButton("Limpiar");
    bSalir      =   new JButton("Salir");
    taDatos     =   new JTextArea(11,40);
    panel1      =   new JPanel();
    panel2      =   new JPanel();

    bEnviar.addActionListener(this);
    bEnviados.addActionListener(this);
    bCancelar.addActionListener(this);
    bSalir.addActionListener(this);

    panel1.setLayout(new GridLayout(5,2));
    panel2.setLayout(new GridLayout(2,1));

    //panel1.add(new JLabel("Remitente: "));
    //panel1.add(tfRemite);
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
    setVisible(true);
  }

  public String obtenerDatos(){
    String datos="";

    String remite= act.getCorreo();
    String destino= tfDestino.getText();
    String asunto= tfAsunto.getText();
    String mensaje= taDatos.getText();

    if(destino.isEmpty() || asunto.isEmpty() || mensaje.isEmpty()){
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
        respuesta= correo.consultarExistente(datos);
        if(respuesta.equals("ENCONTRADO")){
          JOptionPane.showMessageDialog(null,"Correo Enviado");
        }
        if(respuesta.equals("NOT_FOUND")){
          JOptionPane.showMessageDialog(null,"Correo Destino No Encontrado");
        }
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
}
