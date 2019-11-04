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

}
