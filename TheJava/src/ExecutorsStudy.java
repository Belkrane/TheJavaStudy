import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsStudy {

    //Executor가 Thread를 만들고, 우리는 runnable만 제공해주면 된다.

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread " + Thread.currentThread().getName());
            }
        });

        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        executorService2.submit(() -> System.out.println("Thread2 " + Thread.currentThread().getName()));

        //ExecutorService는 실행이 되고나면, 다음 호출까지 기다리기 때문에 shutdown을 해줘야함.
        //shutdown은 현재 진행중인 서비스는 다 끝나고 끝냄, 즉시 끝내려면 shutdownNow()를 쓰면 됨.

        //executorService2.shutdown();
        executorService.shutdownNow();

        executorService2.submit(getRunnable("1"));
        executorService2.submit(getRunnable("2"));
        executorService2.submit(getRunnable("3"));
        executorService2.submit(getRunnable("4"));
        executorService2.submit(getRunnable("5"));

        executorService2.shutdown();

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(getRunnable("Hello"), 1, 2, TimeUnit.SECONDS);
    }


    private static Runnable getRunnable(String message){
        return ()-> System.out.println(message + Thread.currentThread().getName());
    }
}
