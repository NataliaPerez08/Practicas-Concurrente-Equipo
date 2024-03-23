package kass.concurrente.invitados;

import kass.concurrente.candados.*;
import kass.concurrente.candadosImpl.PetersonLock;
import kass.concurrente.tenedor.Tenedor;
/**
 * Clase que modela al inversionista, pero esta vez
 * usando el filtro.
 * No se sobreescribe el run, si hicieron bien las cosas
 * Entonces se pasara sin problemas para aca
 * Good Luck!
 * @version 1.1
 * @author Kassandra Mirael
 */
public class InversionistaFiltro extends Inversionista {
    Semaphore filtro; 

    public InversionistaFiltro(Semaphore filtro){
        this.filtro = filtro;
    }

    /**
     * Metodo que nos permite entrar a la mesa.
     * El inversionista por fin dejo de pensar y de escribir en su
     * servilleta y se digna en entrar.
     * PRIMERO toma los tenedores.
     * DESPUES come.
     * FINALMENTE los suelta para que los demas los puedan usar.
     * @throws InterruptedException esta exception se lanza porque se hace un sleep cuando come el inversionista
     */
    @Override
    public void entraALaMesa() throws InterruptedException {
        
        tomaTenedores();
        candado.lock();
        come();
        candado.unlock();
        sueltaTenedores();


    }

    /**
     * Metodo que nos permite tomar los tenedores.
     * Los toma uno por uno.
     */
    @Override
    public void tomaTenedores() {
        filtro.acquire();
        tenedorL.tomar();
        tenedorR.tomar();

    }

    /**
     * Metodo que hace que el inversionista suelte ambos tenedores una vez
     * que terminara de comer. 
     * De esta manera otro los puede usar.
     * Suelta los tenedores uno por uno.
     */
    @Override
    public void sueltaTenedores() {
        
        tenedorL.soltar();
        tenedorR.soltar();
        filtro.release();
       
    }
    
}
