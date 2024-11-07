public class Senyora implements Runnable {
    String nombre;
    static PasoCebra pasoCebra;

    public Senyora(String nombre, PasoCebra pasoCebra) {
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
        System.out.println("La señora " + nombre + " ha accedido al paso de cebra.");
    }

    public void parar() {
        System.out.println("La señora " + nombre + " ha parado en el paso de cebra.");
    }

    @Override
    public void run() {
        pasoCebra.llegaSenyora();
        pasar();
        pasoCebra.saleSenyora();
    }
}
