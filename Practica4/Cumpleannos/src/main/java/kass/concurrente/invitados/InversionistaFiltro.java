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

    @Override
    public void entraALaMesa() throws InterruptedException {
        
        tomaTenedores();
        candado.lock();
        come();
        candado.unlock();
        sueltaTenedores();


    }

    @Override
    public void tomaTenedores() {
        filtro.acquire();
        tenedorL.tomar();
        tenedorR.tomar();

        /**
         * Aqui va tu codigo
         */
    }

    @Override
    public void sueltaTenedores() {
        filtro.release();
        tenedorL.soltar();
        tenedorR.soltar();
       


        /**
         * Aqui va tu codigo
         */
    }
    
}
