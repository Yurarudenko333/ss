import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;


public class FirstWarTask {

    static AtomicInteger BaseCost=new AtomicInteger(3);
    static AtomicInteger FinishCost=new AtomicInteger(3);

    static Random rnd = new Random();

    static int CostOfWeapon =0;
    static final int AmountOfWeapon = 10000;

    static BlockingQueue<Integer> PetrQ = new ArrayBlockingQueue<Integer>(9);
    static BlockingQueue<Integer> NecherQ = new ArrayBlockingQueue<Integer>(9);

    static class Ivanov implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < AmountOfWeapon; i++) {
                int temp = rnd.nextInt(5);
                try {
                    BaseCost.addAndGet(temp);
                    PetrQ.put(temp);
                    System.out.println("Ivanov add: " + temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Petrov implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < AmountOfWeapon; i++) {
                try {
                    int temp = PetrQ.take();
                    NecherQ.put(temp);
                    System.out.println("Petrov get: " + temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Necher implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < AmountOfWeapon; i++) {
                try {
                    int temp = NecherQ.take();
                    CostOfWeapon += temp;
                    System.out.println("Nech cost: " + CostOfWeapon);
                    FinishCost.addAndGet(temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        var Iv= new Thread(new Ivanov());
        var Pet=new Thread(new Petrov());
        var Nech=new Thread(new Necher());
        Iv.start();
        Pet.start();
        Nech.start();
        Iv.join();
        Pet.join();
        Nech.join();
        System.out.println("Base cost: " + BaseCost);
        System.out.println("Finish cost: "+FinishCost);
    }
}
