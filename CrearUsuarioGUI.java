import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CrearUsuarioGUI extends JFrame implements ActionListener{
  private JTextField tfNombre, tfApellido, tfCorreo, tfContrasena;
  private JButton bregistrar, bSalir;
  private JPanel panel1, panel2;

  private CorreoAD correo= new CorreoAD();

  public CrearUsuarioGUI(){
    tfNombre = new JTextField();
    tfApellido = new JTextField();
    tfCorreo = new JTextField();
    tfContrasena = new JTextField();

    bregistrar = new JButton("Registrar");
    bSalir = new JButton("Salir");

    panel1 = new JPanel();
    panel2 = new JPanel();

    //Agregamos Action Listener
    bregistrar.addActionListener(this);
    bSalir.addActionListener(this);

    //Creamos la configuracion del panel
    panel1.setLayout(new GridLayout(5,2));
    panel2.setLayout(new FlowLayout());

    panel1.add(new JLabel("Nombre"));
    panel1.add(tfNombre);

    panel1.add(new JLabel("Apellido"));
    panel1.add(tfApellido);

    panel1.add(new JLabel("Correo"));
    panel1.add(tfCorreo);

    panel1.add(new JLabel("Contrasena"));
    panel1.add(tfContrasena);

    panel1.add(bregistrar);
    panel1.add(bSalir);

    panel2.add(panel1);

    this.add(panel2);
    setSize(500,500);
    setVisible(true);

  }

  public String obtenerDatos(){
    String datos="";

    String nombre= tfNombre.getText();
    String apellido= tfApellido.getText();
    String correo= tfCorreo.getText();
    String contrasena= tfContrasena.getText();

    if(nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || contrasena.isEmpty()){
      datos="VACIO";
    }
    else{
      datos=nombre+"_"+apellido+"_"+correo+"_"+contrasena;
    }

    return datos;
  }

  public void actionPerformed(ActionEvent e){
    String datos;

    if(e.getSource()==bregistrar){
      datos = obtenerDatos();

      if(datos.equals("VACIO"))
        JOptionPane.showMessageDialog(null, "Algun campo esta vacio");
      else{
        datos=correo.capturarNuevoUsuario(datos);
        if(datos.equals("EXISTENTE")){
          JOptionPane.showMessageDialog(null, "Usuario ya registrado");
        }
        else{
          JOptionPane.showMessageDialog(null, "Registro completado, puede cerrar esta ventana");
        }
      }
    }

    if(e.getSource()== bSalir){
			setVisible(false);
		}
  }

  public static void main (String args[]){
    CrearUsuarioGUI inicio = new CrearUsuarioGUI();
    inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

}
