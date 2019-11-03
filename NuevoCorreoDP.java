import java.util.StringTokenizer;

public class NuevoCorreoDP{

    // Atributos
    private String envia, recibe, asunto, texto, fecha;

    private NuevoCorreoDP next;

    // Constructors
    public NuevoCorreoDP(){
        this.envia="";
        this.recibe="";
        this.asunto="";
        this.texto="";
        this.fecha="";
    }

    public NuevoCorreoDP(String datos){
        StringTokenizer st = new StringTokenizer(datos,"_");
        this.envia=st.nextToken();
        this.recibe=st.nextToken();
        this.asunto=st.nextToken();
        this.texto=st.nextToken();
        this.fecha=st.nextToken();
    }

    // Accesors
    public String getEnvia(){
        return this.envia;
    }

    public String getRecibe(){
        return this.recibe;
    }

    public String getAsunto(){
        return this.asunto;
    }

    public String getTexto(){
        return this.texto;
    }

    public String getFecha(){
        return this.fecha;
    }

    public NuevoCorreoDP getNext(){
      return this.next;
    }


    // Mutators
    public void setEnvia(String env){
        this.envia = env;
    }

    public void setRecibe(String rec){
        this.recibe = rec;
    }

    public void setAsunto(String as){
        this.asunto = as;
    }

    public void setTexto(String txt){
        this.texto = txt;
    }

    public void setFecha(String txt){
        this.fecha = txt;
    }

    public void setNext(NuevoCorreoDP nodo){
      this.next=nodo;
    }

    public String toString(){
        return this.envia+"_"+this.recibe+"_"+this.asunto+"_"+this.texto;
    }
}
