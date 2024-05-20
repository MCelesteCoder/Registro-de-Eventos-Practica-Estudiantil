
package proyecto1;

import java.util.LinkedList;

public class Evento {

    //Se declaran los atributos de la clase evento
    private int idEvento;
    private int anioEvento;
    private String mesEvento;
    private int diaEvento;
    private String horaEvento;
    private String deporteEvento;
    private String nombreEvento;
    private String paisEvento;
    private String equipoAEvento;
    private String equipoBEvento;

    //Se declara e inicializa el atributo listaEventos de la clase evento
    public static LinkedList<Evento> listaEventos = new LinkedList<>();

    //Constructor de la clase Evento
    public Evento(int idEvento, int anioEvento, String mesEvento, int diaEvento, String horaEvento ,String deporteEvento, String nombreEvento, String paisEvento, String equipoAEvento,String equipoBEvento ) {
       
       //Se inicializan los atributos 
        this.idEvento = idEvento;
        this.anioEvento = anioEvento;
        this.mesEvento = mesEvento;
        this.diaEvento = diaEvento;
        this.horaEvento = horaEvento;
        this.deporteEvento = deporteEvento;
        this.nombreEvento = nombreEvento;
        this.equipoAEvento = equipoAEvento;
        this.equipoBEvento = equipoBEvento;    
    }
    
    //Metodos Getter's y Setter's
    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {    
        this.idEvento = idEvento;
    }

    public int getAnioEvento() {
        return anioEvento;
    }

    public void setAnioEvento(int anioEvento) {
        this.anioEvento = anioEvento;
    }

    public String getMesEvento() {
        return mesEvento;
    }

    public void setMesEvento(String mesEvento) {
        this.mesEvento = mesEvento;
    }

    public int getDiaEvento() {
        return diaEvento;
    }

    public void setDiaEvento(int diaEvento) {
        this.diaEvento = diaEvento;
    }

    public String getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(String horaEvento) {
        this.horaEvento = horaEvento;
    }

    public String getDeporteEvento() {
        return deporteEvento;
    }

    public void setDeporteEvento(String deporteEvento) {
        this.deporteEvento = deporteEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getPaisEvento() {
        return paisEvento;
    }

    public void setPaisEvento(String paisEvento) {
        this.paisEvento = paisEvento;
    }

    public String getEquipoAEvento() {
        return equipoAEvento;
    }

    public void setEquipoAEvento(String equipoAEvento) {
        this.equipoAEvento = equipoAEvento;
    }

    public String getEquipoBEvento() {
        return equipoBEvento;
    }
    public void setEquipoBEvento(String equipoBEvento) {    
        this.equipoBEvento = equipoBEvento;
    }

    //Metodo para la generacion del id consecutivo
    public int IdGenerado() {
        idEvento++;
        return idEvento;
    }

    //Metodo para agregar anioEvento a la linkedList evento
    public void AgregarEventos(Evento evento) {
        //Se agrega si anio es mayor a 1900 y si tiene 4 caracteres
        if(anioEvento > 1900 && String.valueOf(anioEvento).length() == 4){
            listaEventos.add(evento);
        } 
    }
    
    //Metodo para devolver la listaEventos
    public static LinkedList<Evento> getListadoEventos(){
        return listaEventos;
    }
}
