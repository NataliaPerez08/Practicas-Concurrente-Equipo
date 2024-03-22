package kass.concurrente.invitados;

import kass.concurrente.tenedor.Tenedor;
import kass.concurrente.candadosImpl.Filtro;
import kass.concurrente.candadosImpl.PetersonLock;


/**
 * Clase abstracta que modela al inversionista.
 * El inversionista tiene 2 tenedores a sus lados.
 * El inversionista posee un ID para que se pueda identificar.
 * El inversionista tiene una variable que indica el numero de veces que ha comido.
 * @version 1.0
 * @author Kassandra Mirael
 */
public abstract class Inversionista implements Runnable {
    Tenedor tenedorL;
    Tenedor tenedorR;
    int id;
    int vecesComido=0;
    PetersonLock candado = new PetersonLock();
    

    @Override
    public void run() {
        /**
         * El inversionista debe pensar y entrar a la mesa un periodo de veces
         * puesto en el test, agrega el valor aqui.
         */
        for(int i=0;i<500;i++){
            
            try {
                
                piensa();
                entraALaMesa();
                

                //System.out.println("Inversionista "+id+" ingresando por "+i+" vez. y comió por "+vecesComido+" vez.");
                
            } catch (InterruptedException e) {
                System.out.println("Fallo en hilo "+id);
            }
            
        }
    }

    /**
     * Metodo que nos permite entrar a la mesa.
     * El inversionista por fin dejo de pensar y de escribir en su
     * servilleta y se digna en entrar.
     * PRIMERO toma los tenedores.
     * DESPUES come.
     * FINALMENTE los suelta para que los demas los puedan usar.
     * @throws InterruptedException <Escribe porque se lanzaria esta exception>
     */
    public void entraALaMesa() throws InterruptedException{
        /**
         * Aqui va tu codigo
         */
        tomaTenedores();
        come();
        sueltaTenedores();
    }

    /**
     * Una vez que termino de pensar sobre finanzas el inversionista
     * se prepara para comer.
     * El inversionista le toma un par de milisegundos comer.
     * ESTA ES LA SECCION CRITICA, SIGNIFICA PELIGRO
     * Incrementa el numero de veces que ha comido.
     * @throws InterruptedException <Escribe porque se lanzaria esta exception>
     */
    public void come() {
        /**
         * Aqui va tu codigo
         */
        
        //System.out.println("Inversionista "+this.id+" comiendo :p");
       
        vecesComido+=1;
        try{
            Thread.sleep(generaTiempoDeEspera());
        }catch ( InterruptedException e){
            System.out.println("Fue en comer");
        }
        
        
        
    }

    /**
     * Metodo que hace que el inversionista piense.
     * El inversionista pensara por una par de milisegundos.
     * Esto pasa antes de que tome sus tenedores.
     * @throws InterruptedException <Escribe porque se lanzaria esta exception>
     */
    public void piensa() {
        /**
         * Aqui va tu codigo
         */
        //System.out.println("Inversionista "+this.id+" pensando...");
        try{
            Thread.sleep(generaTiempoDeEspera());
        }catch ( InterruptedException e){
            System.out.println("Fue en pensar");
        }
        
    }

    /**
     * Metodo que nos permite tomar los tenedores.
     * Los toma uno por uno.
     */
    public abstract void tomaTenedores();

    /**
     * Metodo que hace que el inversionista suelte ambos tenedores una vez
     * que terminara de comer. 
     * De esta manera otro los puede usar.
     * Suelta los tenedores uno por uno.
     */
    public abstract void sueltaTenedores();
    
    /**
     * Metodo que genera un numero pseudoaleatorio entre 1 y 10
     * @return El tiempo de espera
     */
    private long generaTiempoDeEspera(){
        double i=Math.random()*10.0;
        return (long)i ;
    }

    /*
     * Rellena Getter and Setters primero
     * Documenta los metodos.
     * Cuando acabes borra estew comentario
     */
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;

    }

    public Tenedor getTenedorIzq(){
        return tenedorL;
    }

    public void setTenedorIzq(Tenedor t){
        tenedorL = t;

    }

    public Tenedor getTenedorDer(){
        return tenedorR;
    }

    public void setTenedorDer(Tenedor t){
        tenedorR=t;

    }

    public int getVecesComido(){
        return vecesComido;
    }

    public void setVecesComido(int vecesComido){
        this.vecesComido=vecesComido;
        
    }
}
