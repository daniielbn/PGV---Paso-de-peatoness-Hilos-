import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PasoCebra{
    private boolean haySenyora = false;
    private boolean hayCiclista = false;
    private int senyorasEsperando = 0;

    ReentrantLock monitor = new ReentrantLock();

    Condition colaCiclistas = monitor.newCondition();
    Condition colaSenyora = monitor.newCondition();

    public void llegaCiclista() {
        Random random = new Random();
        monitor.lock();
        try {
            while (haySenyora || senyorasEsperando > 0) {
                colaCiclistas.await();
            }
            hayCiclista = true;
            Thread.sleep((random.nextInt(3) + 1) * 1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            monitor.unlock();
        }
    }

    public void llegaSenyora() {
        Random random = new Random();
        monitor.lock();
        try {
            senyorasEsperando++;
            Thread.sleep((random.nextInt(4) + 3) * 1000);
            while (haySenyora || hayCiclista) {
                colaSenyora.await();
            }
            haySenyora = true;
            senyorasEsperando--;
            Thread.sleep((random.nextInt(6) + 5) * 1000);
            if (senyorasEsperando == 0) {
                colaCiclistas.signalAll();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            monitor.unlock();
        }

    }

    public void saleSenyora() {
        monitor.lock();
        try {
            haySenyora = false;
            colaSenyora.signalAll();
            colaCiclistas.signalAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            monitor.unlock();
        }
    }

    public void saleCiclista() {
        monitor.lock();
        try {
            hayCiclista = false;
            colaSenyora.signalAll();
            colaCiclistas.signalAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            monitor.unlock();
        }
    }
}
