import java.io.*;
import java.util.StringTokenizer;
import java.util.Date;
import java.nio.file.*;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CorreoAD{
  //Atributos
  private CorreoDP cPrimero, cActual, cUltimo;
  private CarpetaDP carpetaPrimero, carpetaActual, carpetaUltimo;
  private NuevoCorreoDP primero, actual, ultimo;
  private NuevoCorreoDP posActual;

  //Atributos de LinkedList
  private LinkedList listaCorreos = new LinkedList();
  private LinkedList listaUsuarios = new LinkedList();
  private LinkedList listaCarpetas = new LinkedList();
  private int nodoActual;

  //Constructor
  public CorreoAD(){
    datosArchivoListaCorreo();
    datosArchivoListaUsuarios();
    datosArchivoListaCarpetas();
  }

  //Registrar Usuario
  public String capturarNuevoUsuario(String datos){
    String respuesta="";
    respuesta=consultarCorreo(datos);
    if(respuesta.equals("ENCONTRADO")){
      datos="EXISTENTE";
    }
    else{
      listaUsuarios.add(new CorreoDP(datos));
      System.out.println("Registro");
      datosUsuarioArchivo();
      datos="REGISTRO";
    }

    return datos;
  }

  //Consulta de si el correo ya esta registrado
  public String consultarCorreo(String datos){
    StringTokenizer st = new StringTokenizer(datos,"_");

    String nombre  = st.nextToken();
    String apellido = st.nextToken();
    String correo = st.nextToken();

    Boolean encontrado=false;
    int i=0;

    if(listaUsuarios.isEmpty()){
        datos="LISTA_VACIA";
    }

    else{
      while(i<listaUsuarios.size() && !encontrado){
        cActual = (CorreoDP)listaUsuarios.get(i);

        if(cActual.getCorreo().equals(correo)){
          nodoActual = i;
          encontrado=true;
          datos="ENCONTRADO";
        }
        i++;
      }

        if(encontrado==false){
          datos="NOT_FOUND";
        }
      }
      return datos;
  }

  //Añadir usuario a lista
  public void capturarUsuario(String datos){
    listaUsuarios.add(new CorreoDP(datos));
  }

  //Añadir carpeta a lista
  public void capturarCarpeta(String datos){
    listaCarpetas.add(new CarpetaDP(datos));
  }

  //Añadir una nueva carpeta
  public void capturarCarpetaNueva(String datos){
    listaCarpetas.add(new CarpetaDP(datos));
    datosCarpetaArchivo();
  }

  //Añadir correo a carpeta
  public void agregarCorreoCarpeta(String mail, String nombreCarpeta, String asunto){
  int i = 0;

  while(i<listaCarpetas.size()){
    carpetaPrimero=(CarpetaDP)listaCarpetas.get(i);

    if(carpetaPrimero.getCuenta().equals(mail)){

      if(carpetaPrimero.getNombreCarpeta().equals(nombreCarpeta)){
        carpetaPrimero.addCorreo(asunto);
        datosCarpetaArchivo();
        JOptionPane.showMessageDialog(null, "Correo agregado a " + nombreCarpeta);
        break;
      }
    }

    i++;
    }
  }

  //Enviar nombre de carpetas
  public ArrayList<String> getCarpetas(String usuario){
    ArrayList<String> carpetas = new ArrayList<String>();
    int i = 0;

    while(i < listaCarpetas.size()){
      carpetaActual = (CarpetaDP)listaCarpetas.get(i);
      if(carpetaActual.getCuenta().equals(usuario))
        carpetas.add(carpetaActual.getNombreCarpeta());
      i++;
    }

    return carpetas;
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

  //Registrar los correos
  public String captura(String datos){
    listaCorreos.add(new NuevoCorreoDP(datos));
    return "Nuevo correo enviado";
  }

  //Registrar nuevos correos
  public void capturaNueva(String datos){
    listaCorreos.add(new NuevoCorreoDP(datos));
    datosListaArchivo();
    //return "Correo enviado";
  }

  //Consulta de si el destino existe en el registro
  public String consultarExistente(String datos){
    StringTokenizer st = new StringTokenizer(datos,"_");

    String remitente  = st.nextToken();
    String destino = st.nextToken();

    Boolean encontrado=false;
    int i=0;

    if(listaUsuarios.isEmpty()){
        datos="LISTA_VACIA";
    }

    else{
      while(i<listaUsuarios.size() && !encontrado){
        cActual = (CorreoDP)listaUsuarios.get(i);

        if(cActual.getCorreo().equals(destino)){
          nodoActual = i;
          encontrado=true;
          capturaNueva(datos);
          datos="ENCONTRADO";
        }
        i++;
      }

        if(encontrado==false){
          datos="NOT_FOUND";
        }
      }
      return datos;
  }

  //Consulta general
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

  //Consulta de correos enviados a cierto destino
  public String consultarDestino(String destino, String envia){
    String datos="";
    int i=0;
    boolean encontrado=false;

    if(listaCorreos.isEmpty()){
        datos="LISTA_VACIA";
    }

    else{
      while(i<listaCorreos.size()){
        actual = (NuevoCorreoDP)listaCorreos.get(i);
        if(actual.getRecibe().equals(destino) && actual.getEnvia().equals(envia)){
          datos=datos+actual.toString()+"\n";
          encontrado=true;
        }
        i++;
      }

        if(encontrado==false){
          datos="No se encontraron correos con ese destino";
        }
      }
      return datos;
  }

  //Consulta de correos enviados con determinado asunto
  public String consultarAsunto(String asunto, String envia){
    String datos="";
    int i=0;
    boolean encontrado=false;

    if(listaCorreos.isEmpty()){
        datos="LISTA_VACIA";
    }

    else{
      while(i<listaCorreos.size()){
        actual = (NuevoCorreoDP)listaCorreos.get(i);
        if(actual.getAsunto().equals(asunto) && actual.getEnvia().equals(envia)){
          datos=datos+actual.toString()+"\n";
          encontrado=true;
        }
        i++;
      }

        if(encontrado==false){
          datos="No se encontraron correos con ese Asunto";
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
          pCorreo.add(new CorreoMGUI((NuevoCorreoDP) listaCorreos.get(i), cuenta));
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

  public JPanel consultarJP(String cuenta, String carpeta){

    JPanel pCorreo = new JPanel();
    int i =0;
    boolean isEmpty = true;
    String[] asuntos = new String[0];
    carpetaActual = null;

    while(i<listaCarpetas.size()){
      carpetaPrimero=(CarpetaDP)listaCarpetas.get(i);

      if(carpetaPrimero.getCuenta().equals(cuenta)){
        if(carpetaPrimero.getNombreCarpeta().equals(carpeta)){
          carpetaActual = carpetaPrimero;
          asuntos = carpetaPrimero.getCorreos();
          break;
        }
      }

      i++;
    }

    if(carpetaActual == null){
      pCorreo.add(new JLabel("No tiene ningun correo"));
      return pCorreo;
    } else {
      pCorreo.setLayout(new GridLayout(listaCorreos.size(),1));

      while(i < listaCorreos.size()){
        actual=(NuevoCorreoDP) listaCorreos.get(i);

        for (int j = 0; j < asuntos.length ; j++) {
          if(actual.getRecibe().equals(cuenta)){
            if(actual.getAsunto().equals(asuntos[j])){
              pCorreo.add(new CorreoMGUI((NuevoCorreoDP) listaCorreos.get(i), cuenta));
              isEmpty = false;
              System.out.println("Entro");
            }
          }
        }
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

  public String datosCarpetaArchivo(){
    String resultado="", datos="";
    PrintWriter archivoOut;
    int i=0;

    if(listaCarpetas.isEmpty()){
      datos="Lista vacia";
      try{
        Files.deleteIfExists(Paths.get("Carpetas.txt"));
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
        archivoOut= new PrintWriter(new FileWriter("Carpetas.txt"));
        while(i<listaCarpetas.size()){
          carpetaActual=(CarpetaDP)listaCarpetas.get(i);
          archivoOut.println(carpetaActual.toString());
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

  public void datosArchivoListaCarpetas(){
    try{
  		BufferedReader archivoIn = new BufferedReader(new FileReader("Carpetas.txt"));
  		while(archivoIn.ready())
				capturarCarpeta(archivoIn.readLine());

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
