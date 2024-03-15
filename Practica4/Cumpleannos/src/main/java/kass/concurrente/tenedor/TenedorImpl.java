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
    private PetersonLock candado = new PetersonLock();

    public TenedorImpl(int id){
        this.id = id;
        this.vecesTomado = 0;
    }

    @Override
    public void tomar() {
        System.out.println("Tenedor "+this.id+" tomado");
        this.vecesTomado++;
        candado.lock();
    }

    @Override
    public void soltar() {
        candado.unlock();
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getVecesTomado() {
        return this.vecesTomado;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setVecesTomado(int vecesTomado) {
        this.vecesTomado = vecesTomado;
    }
    
}
