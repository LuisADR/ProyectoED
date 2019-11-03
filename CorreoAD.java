import java.io.*;
import java.util.StringTokenizer;
import java.util.Date;
import java.nio.file.*;
import java.util.LinkedList;

public class CorreoAD{
  //Atributos
  private NuevoCorreoDP primero, actual, ultimo;
  private NuevoCorreoDP posActual;

  //Atributos de LinkedList
  private LinkedList listaCorreos= new LinkedList();
  private int nodoActual;

  //Constructor
  public CorreoAD(){
    datosArchivoListaCorreo();
  }

  public String capturaInicio(String datos){

    listaCorreos.addFirst(new NuevoCorreoDP(datos));

    return "Nuevo Auto anclado al inicio";
  }


  public String captura(String datos){
    listaCorreos.add(new NuevoCorreoDP(datos));
    return "Nuevo correo enviado";
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
}
