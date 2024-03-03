public class Interruptor {
    private boolean encendido;

    public Interruptor() {
        this.encendido = Math.random() < 0.5; // Estado inicial pseudoaleatorio
    }

    public void encender() {
        this.encendido = true;
        System.out.println("Interruptor encendido.");
    }

    public boolean estaEncendido() {
        return encendido;
    }
}
