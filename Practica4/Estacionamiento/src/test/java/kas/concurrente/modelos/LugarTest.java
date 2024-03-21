package kas.concurrente.modelos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LugarTest {
    Semaphore semaforo;
    Lugar lugar;
    static final int numHilos = 15;

    @BeforeEach
    void setUp() throws InterruptedException{
        lugar = new Lugar(1);
        semaforo = new Semaphore(1);
    }

    @Test
    void constructorTest(){
        assertTrue(lugar.getId() == 1 && lugar.getDisponible() == true);
    }

    @Test
    void estacionaTest() throws InterruptedException {
        System.out.println("Estado inicial del lugar: " + lugar.getDisponible());
        lugar.estaciona();
        System.out.println("Estado del lugar después de estacionar: " + lugar.getDisponible());
        if (lugar.getDisponible()) {
            throw new AssertionError("El lugar debería estar no disponible después de estacionar un carro");
        }
    }
    
    /**
     * AGREGA 2 TEST MAS
     * TEST bien hechos
     */
}


