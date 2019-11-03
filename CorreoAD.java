import java.io.*;
import java.util.StringTokenizer;
import java.util.Date;
import java.nio.file.*;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;

public class CorreoAD{
  //Atributos
  private CorreoDP cPrimero, cActual, cUltimo;
  private NuevoCorreoDP primero, actual, ultimo;
  private NuevoCorreoDP posActual;

  //Atributos de LinkedList
  private LinkedList listaCorreos= new LinkedList();
  private LinkedList listaUsuarios = new LinkedList();
  private int nodoActual;

  //Constructor
  public CorreoAD(){
    datosArchivoListaCorreo();
    datosArchivoListaUsuarios();
  }

  //Registrar Usuario
  public void capturarNuevoUsuario(String datos){
    listaUsuarios.add(new CorreoDP(datos));
    System.out.println("Registro");
    datosUsuarioArchivo();
  }

  public void capturarUsuario(String datos){
    listaUsuarios.add(new CorreoDP(datos));
  }

  //Creacion de Conexion
  public CorreoDP crearConexion(String bCorreo, String bContrasena){
    String datos="";
    int i=0;
    cPrimero = null;
    datosArchivoListaUsuarios();

    if(listaUsuarios.isEmpty()){
      return cPrimero;
    }
    else{
      while(i<listaUsuarios.size()){
        cActual=(CorreoDP)listaUsuarios.get(i);
        System.out.println(cActual.getCorreo());
        if(cActual.getCorreo().equals(bCorreo)){
          System.out.println(bCorreo);
          if(cActual.getContrasena().equals(bContrasena)){
            System.out.println("Valido");
            cPrimero = cActual;
            break;
          }
        }

        i++;
      }
    }
    return cPrimero;
  }

  public String capturaInicio(String datos){

    listaCorreos.addFirst(new NuevoCorreoDP(datos));
    datosListaArchivo();

    return "Nuevo Auto anclado al inicio";
  }


  public String captura(String datos){
    listaCorreos.add(new NuevoCorreoDP(datos));
    return "Nuevo correo enviado";
  }

  public String capturaNueva(String datos){
    listaCorreos.add(new NuevoCorreoDP(datos));
    datosListaArchivo();
    return "Correo enviado";
  }

  public String consultar(){
    String datos="";
    int i=0;

    if(listaCorreos.isEmpty()){
      datos="Lista vacia";
    }
    else{
      while(i<listaCorreos.size()){
        actual=(NuevoCorreoDP)listaCorreos.get(i);
        datos=datos+actual.toString()+ "\n";
        i++;
      }
    }
    return datos;
  }

  //Generacion de una lista de correos
  public JPanel consultarJP(String cuenta){
    JPanel pCorreo = new JPanel();
    int i =0;
    boolean isEmpty = true;

    if(listaCorreos.isEmpty()){
      pCorreo.add(new JLabel("No tiene ningun correo"));
      return pCorreo;
    } else {
      pCorreo.setLayout(new GridLayout(listaCorreos.size(),1));

      while(i < listaCorreos.size()){
        actual=(NuevoCorreoDP) listaCorreos.get(i);
        if(actual.getRecibe().equals(cuenta)){
          pCorreo.add(new CorreoMGUI((NuevoCorreoDP) listaCorreos.get(i)));
          isEmpty = false;
        }

        System.out.println("Entro");
        i++;
      }

      if(isEmpty){
        pCorreo.add(new JLabel("No tiene ningun correo"));
      }

      System.out.println("Termino");
      return pCorreo;
    }
  }


//Control de textos y archivos
  public String datosListaArchivo(){
    String resultado="", datos="";
    PrintWriter archivoOut;
    int i=0;

    if(listaCorreos.isEmpty()){
      datos="Lista vacia";
      try{
        Files.deleteIfExists(Paths.get("Enviados.txt"));
      }
      catch(NoSuchFileException nsfe){
        System.out.println(nsfe);
      }
      catch(IOException ioe){
        System.out.println(ioe);
      }
    }
    else{
      try{
        archivoOut= new PrintWriter(new FileWriter("Enviados.txt"));
        while(i<listaCorreos.size()){
          actual=(NuevoCorreoDP)listaCorreos.get(i);
          archivoOut.println(actual.toString());
          i++;
        }
        archivoOut.close();
        resultado="Datos almacenados en Archivo";
      }
      catch(IOException ioe){
				resultado ="ERROR: "+ioe;
				System.out.println("Error: +ioe");
			}
    }

    return resultado;
  }


  public void datosUsuarioArchivo(){
    String resultado="", datos="";
    PrintWriter archivoOut;
    int i=0;

    if(listaUsuarios.isEmpty()){
      datos="Lista vacia";
      try{
        Files.deleteIfExists(Paths.get("Usuarios.txt"));
        System.out.println("Entro if");
      }
      catch(NoSuchFileException nsfe){
        System.out.println(nsfe);
      }
      catch(IOException ioe){
        System.out.println(ioe);
      }
    }
    else{
      try{
        archivoOut= new PrintWriter(new FileWriter("Usuarios.txt", false));
        System.out.println("Entro else");
        while(i<listaUsuarios.size()){
          cActual=(CorreoDP)listaUsuarios.get(i);
          archivoOut.println(cActual.toString());
          i++;
          System.out.println("Entro");
        }
        archivoOut.close();
        resultado="Datos almacenados en Archivo";
      }
      catch(IOException ioe){
        resultado ="ERROR: "+ioe;
        System.out.println("Error: +ioe");
      }
    }

  }

  public void datosArchivoListaCorreo(){
    try{
			BufferedReader archivoIn = new BufferedReader(new FileReader("Enviados.txt"));
			while(archivoIn.ready())
				captura(archivoIn.readLine());

				archivoIn.close();
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("Error: "+fnfe);
		}
		catch(IOException ioe)
		{
			System.out.println("Error: "+ioe);
		}
  }

  public void datosArchivoListaUsuarios(){
    try{
  		BufferedReader archivoIn = new BufferedReader(new FileReader("Usuarios.txt"));
  		while(archivoIn.ready())
				capturarUsuario(archivoIn.readLine());

				archivoIn.close();
  		}
  		catch(FileNotFoundException fnfe)
  		{
  			System.out.println("Error: "+fnfe);
  		}
  		catch(IOException ioe)
  		{
  			System.out.println("Error: "+ioe);
  		}
  }
}
