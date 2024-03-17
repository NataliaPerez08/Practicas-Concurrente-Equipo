package kass.concurrente.invitados;

import kass.concurrente.candadosImpl.Filtro;

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
    Filtro sem =new Filtro(4, 1);



    @Override
    public void entraALaMesa() throws InterruptedException {
        tomaTenedores();
        come();
        sueltaTenedores();

    }

    @Override
    public void tomaTenedores() {

        tenedorL.tomar();
        tenedorR.tomar();
        sem.acquire();

        /**
         * Aqui va tu codigo
         */
    }

    @Override
    public void sueltaTenedores() {

        tenedorL.soltar();
        tenedorR.soltar();
        sem.release();
        /**
         * Aqui va tu codigo
         */
    }
    
}
