package kass.concurrente.tenedor;

import kass.concurrente.candadosImpl.PetersonLock;

/**
 * Clase que implementa el tenedor
 * Tenemos una variable entera que cuenta el numero de veces que fue tomado
 * Tiene una variable que simboliza su id
 * @version 1.1
 * @author Los hilos del barrio
 */
public class TenedorImpl implements Tenedor {
    private int id;
    private int vecesTomado;
    private  PetersonLock candado = new PetersonLock();

    /**
     * Constructor de la clase TenedorImpl
     * @param id identificador del tenedor
     */
    public TenedorImpl(int id){
        this.id = id;
        this.vecesTomado = 0;
    }

    /**
     * Metodo que nos permite tomar el tenedor.
     */
    @Override
    public void tomar() {
               
        candado.lock();
        this.vecesTomado++;
        
    }

    /**
     * Metodo que nos permite soltar el tenedor.
     */
    @Override
    public void soltar() {
        candado.unlock();
    }

    /**
     * Metodo que nos retorna el id
     * @return El id
     */
    @Override
    public int getId() {
        return this.id;
    }


    /**
     * Metodo que asigna un nuevo id
     * @param id El nuevo id
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodo que nos retorna el numero de veces
     * que se tomo el tenedor.
     * @return El numero de veces que se tomó
     */
    @Override
    public int getVecesTomado() {
        return this.vecesTomado;
    }

    /**
     * Metodo que cambia el numero de veces
     * que se tomo el tenedor.
     * @param vecesTomado El nuevo número de veces que se tomó
     */
    @Override
    public void setVecesTomado(int vecesTomado) {
        this.vecesTomado = vecesTomado;
    }
    
}
