import java.util.StringTokenizer;
import java.util.ArrayList;

public class CarpetaDP{

  private ArrayList<String> correos = new ArrayList<String>();
  private String cuenta, nombreCarpeta;

  public CarpetaDP(){
    cuenta = "";
    nombreCarpeta = "";
  }

  public CarpetaDP(String datos){
    StringTokenizer st = new StringTokenizer(datos,"_");

    this.cuenta = st.nextToken();
    this.nombreCarpeta = st.nextToken();

    while(st.hasMoreTokens()){
      correos.add(st.nextToken());
    }
  }

  public String getCuenta(){
    return this.cuenta;
  }

  public String getNombreCarpeta(){
    return this.nombreCarpeta;
  }

  public String[] getCorreos(){
    String[] arreglo = new String[correos.size()];
    arreglo = correos.toArray(arreglo);

    return arreglo;
  }

  public void setCuenta(String datos){
    this.cuenta = datos;
  }

  public void setNombreCarpeta(String datos){
    this.nombreCarpeta = datos;
  }

  public void addCorreo(String asunto){
    this.correos.add(asunto);
  }

  public void setCorreos(ArrayList<String> correos){
    this.correos = correos;
  }

  public String toString(){
    String datos = "";
    int i = 0;
    datos = datos + this.cuenta + "_" + this.nombreCarpeta;

    while(i < correos.size()){
      datos = datos + "_" + this.correos.get(i);
      i++;
    }

    return datos;
  }

}
