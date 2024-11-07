import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ArrayList<Ciclista> ciclistas = new ArrayList<>();
        ArrayList<Senyora> senyoras = new ArrayList<>();
        ArrayList<Thread> hiloCiclistas = new ArrayList<>();
        ArrayList<Thread> hiloSenyoras = new ArrayList<>();

        PasoCebra pasoCebra = new PasoCebra();

        for (int i = 0; i <= 10; i++) {
            ciclistas.add(new Ciclista(("Ciclista " + i), pasoCebra));
        }
        for (int i = 0; i <= 5; i++) {
            senyoras.add(new Senyora(("Señora " + i), pasoCebra));
        }

        for(Ciclista ciclista : ciclistas) {
            hiloCiclistas.add(new Thread(ciclista));
        }

        for(Senyora senyora : senyoras) {
            hiloSenyoras.add(new Thread(senyora));
        }

        for(Thread hiloCiclista : hiloCiclistas) {
            hiloCiclista.start();
        }

        for(Thread hiloSenyora : hiloSenyoras) {
            hiloSenyora.start();
        }

        for(Thread hiloCiclista : hiloCiclistas) {
            try {
                hiloCiclista.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(Thread hiloSenyora : hiloSenyoras) {
            try {
                hiloSenyora.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
