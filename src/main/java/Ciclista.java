public class Ciclista implements Runnable {
    String nombre;
    static PasoCebra pasoCebra;

    public Ciclista(String nombre, PasoCebra pasoCebra) {
        this.nombre = nombre;
        this.pasoCebra = pasoCebra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void pasar() {
        System.out.println("El ciclista " + nombre + " ha accedido a la carretera.");
    }

    public void parar() {
        System.out.println("El ciclista " + nombre + " ha parado en el paso de cebra.");
    }

    @Override
    public void run() {
        pasoCebra.llegaCiclista();
        pasar();
        pasoCebra.saleCiclista();
    }
}
