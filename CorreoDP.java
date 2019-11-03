import java.util.StringTokenizer;

public class CorreoDP{

    // Atributos
    private String nombre, apellido, correo, contrasena;

    private CorreoDP next;

    // Constructors
    public CorreoDP(){
        this.nombre  = "";
        this.apellido  = "";
        this.correo   = "";
        this.contrasena= "";
    }

    public CorreoDP(String datos){
        StringTokenizer st = new StringTokenizer(datos,"_");

        this.nombre  = st.nextToken();
        this.apellido = st.nextToken();
        this.correo   = st.nextToken();
        this.contrasena = st.nextToken();
    }

    // Accesors
    public String getNombre(){
        return this.nombre;
    }

    public String getApellido(){
        return this.apellido;
    }

    public String getCorreo(){
        return this.correo;
    }

    public String getContrasena(){
        return this.contrasena;
    }

    public CorreoDP getNext(){
      return this.next;
    }


    // Mutators
    public void setNombre(String nom){
        this.nombre = nom;
    }

    public void setApellido(String ape){
        this.apellido = ape;
    }

    public void setCorreo(String cor){
        this.correo = cor;
    }

    public void setContrasena(String con){
        this.contrasena = con;
    }

    public void setNext(CorreoDP nodo){
      this.next=nodo;
    }

    public String toString(){
        return this.nombre+"_"+this.apellido+"_"+this.correo+"_"+this.contrasena;
    }
}
