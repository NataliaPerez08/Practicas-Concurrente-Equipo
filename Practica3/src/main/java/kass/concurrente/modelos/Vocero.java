package kass.concurrente.modelos;

/**
 * Este ess quien lleva la cuenta de los prisioneros que han entrado a la habitacion
 * a parte de los atributos de Prisionero, tambien posee un contador
 * @author <Su equipo>
 * @version 1.0
 */
public class Vocero extends Prisionero{
    protected Integer contador;
    public Vocero(Integer id, Boolean esVocero, Boolean marcado) {
        super(id, esVocero, marcado);
        //Completar y hacer documentacion
    }
    //Mismo caso que el otro, annadir getter and setter
    public Integer getContador() {
        return contador;
    }
    
    public void setContador(Integer contador) {
        this.contador = contador;
    }
    @Override
    public String toString(){
        return "Vocero: " + id + " " + esVocero + " " + marcado + " " + contador;
    }
}

