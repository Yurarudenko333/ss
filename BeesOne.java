
import java.util.concurrent.Executors                                                                                           ;
import java.util.concurrent.atomic.AtomicBoolean                                                                                ;
import java.util.Random                                                                                                         ;
import java.util.concurrent.ExecutorService                                                                                     ;


public class BeesOne                                                                                                            {
    static int MaxLines = 10                                                                                                    ;
    static int LineLen = 10                                                                                                     ;

    static int forest[][] = new int[MaxLines][LineLen]                                                                          ;

    volatile static AtomicBoolean found = new AtomicBoolean(false)                                                   ;

    static int ansY                                                                                                             ;
    static int ansX                                                                                                             ;

    static class Worker implements Runnable                                                                                     {
        int line                                                                                                                ;
        boolean complete = false                                                                                                ;

        public Worker(int _line)                                                                                                {
            line = _line                                                                                                        ;}

        @Override
        public void run()                                                                                                       {
            if (!found.get())                                                                                                   {
                for (int j = 0; j < LineLen && (!found.get()); j++)                                                             {
                    if (forest[line][j] == 1)                                                                                   {
                        ansY = line                                                                                             ;
                        found.set(true)                                                                                         ;
                        complete = true                                                                                         ;
                        System.out.println("found")                                                                             ;
                        ansX = j                                                                                                ;
                                                                                                                                }
                                                                                                                                }
                                                                                                                                }
            if (!complete)                                                                                                      {
                System.out.println("not found")                                                                                 ;}

            try                                                                                                                 {
                Thread.sleep(1)                                                                                             ;}
            catch (InterruptedException e)                                                                                      {
                e.printStackTrace()                                                                                             ;}
                                                                                                                                }
                                                                                                                                }

    public static void main(String[] args)                                                                                      {
        Random rnd = new Random()                                                                                               ;
        forest[ rnd.nextInt(LineLen)][rnd.nextInt(MaxLines)] = 1                                                                ;

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1)        ;
        System.out.println(Runtime.getRuntime().availableProcessors()+1)                                                        ;
        for (int i = 0; i < MaxLines; i++)                                                                                      {
            Runnable worker = new Worker(i)                                                                                     ;
            executor.execute(worker)                                                                                            ;
                                                                                                                                }

        executor.shutdown()                                                                                                     ;
        while (!executor.isTerminated())                                                                                        ;
        System.out.println(ansY + " " + ansX)                                                                                   ;
                                                                                                                                }
                                                                                                                                }
