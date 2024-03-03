package kass.concurrente.modelos;

/**
 * Clase que modela un prisioner
 * @version 1.0
 * @author <Su equipo>
 */
public class Prisionero {
    protected Integer id;
    protected Boolean esVocero;
    protected Boolean marcado;
    protected int vecesPasadas=0;

    /**
     * Metodo constructor para generar un prisionero
     * @param id El identificador del prisionero
     * @param esVocero true si es Vocero false en otro caso
     * @param marcado true si ya paso
     */
    public Prisionero(Integer id, Boolean esVocero, Boolean marcado){
        this.id=id;
        this.esVocero=esVocero;
        this.marcado=marcado;
    }

    //Annadir getter and setter, toString.

    public Boolean getEsVocero() {
        return esVocero;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getMarcado() {
        return marcado;
    }

    public int getVecesPasadas() {
        return vecesPasadas;
    }

    public void setEsVocero(Boolean esVocero) {
        this.esVocero = esVocero;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setMarcado(Boolean marcado) {
        this.marcado = marcado;
    }

    public void setVecesPasadas(int vecesPasadas) {
        this.vecesPasadas = vecesPasadas;
    }

    public void aumentarVecesPasadas(){
        this.vecesPasadas++;
    }

    public void aumentarContador(){

    }

    public int getContador(){
        return 0;
        
    }
}
