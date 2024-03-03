package kass.concurrente.modelos;
import kass.concurrente.constantes.*;
import java.util.Random;

/**
 * Clase que fungira como habitacion
 * Se sabe que existe un interruptor que nos dice
 * si el foco esta prendido o no
 * Se desconoce el estado inicial del foco (Usar un random, para que sea
 * pseudoaleatorio el estado inicial)
 * @author <Equipo>
 * @version 1.0
 */
public class Habitacion {
    private Boolean prendido;
    private Boolean voceroYaPuedeHablar = true;

    /**
     * Metodo Constructor
     * Aqui se define el como estara el foco inicialmente
     */
    public Habitacion(){
        Random rnd = new Random();
        prendido= rnd.nextBoolean();

    }

    public Boolean getPrendido() {
        return prendido;
    }

    public void setPrendido(Boolean prendido) {
        this.prendido = prendido;
    }


    /**
     * Metodo que permite al prisionero entrar a la habitacion
     * Recordemos que solo uno pasa a la vez, esta es la SECCION CRITICA
     * En este caso se controla desde fuera
     * Es similar al algoritmo que progonan y similar al de su tarea
     * El prisionero espera una cantidad finita de tiempo
     * @param prisionero El prisionero que viene entrando
     * @return false si ya pasaron todos, true en otro caso
     * @throws InterruptedException Si falla algun hilo
     */
    public Boolean entraHabitacion(Prisionero prisionero) throws InterruptedException{
        int contador=0;
        if(!prendido && prisionero.getVecesPasadas()<2 && !prisionero.getEsVocero()){
            System.out.println("PRISIONERO "+prisionero.getId()+" ENTRANDO");
            prendido=true;
            prisionero.aumentarVecesPasadas();

            prisionero.setMarcado(true);
            

        }if(prendido && prisionero.getEsVocero()){
            System.out.println("VOCERO ENTRANDO");

            prendido=false;
            ((Vocero) prisionero).aumentarContador();
            voceroYaPuedeHablar= !(((Vocero) prisionero).getContador()>=(2*(Contante.PRISIONEROS-1)));       


            
        }
        return  voceroYaPuedeHablar;
    }
}
