import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorsStudy {

    //Executor가 Thread를 만들고, 우리는 runnable만 제공해주면 된다.

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread " + Thread.currentThread().getName());
            }
        });

        ExecutorService executorService2 = Executors.newFixedThreadPool(4);
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

        //executorService2.shutdown();

        //ScheduledExecutorService는 Executor인데 스케쥴 기능을 포함하고있다. 딜레이를 줘서 몇 초 후에 실행이 되거나
        //특정 간격으로 반복 시킬 수 있음
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(getRunnable("Hello"), 1, 2, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        //Callable => Runnable과 같은데 Return값을 가질 수 있다.

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "java";
        };

        Callable<String> belk = () -> {
            Thread.sleep(1000L);
            return "belk";
        };


        //invokeAll 을 이용해서 한 번에 여러개의 Callable을 줄수 있다.
        //-> 여러개의 쓰레드가 모두 끝날때 까지 기다렸다가 한꺼번에 return함
        System.out.println("invokeAll ==============================");
        List<Future<String>> futureList =  executorService2.invokeAll(Arrays.asList(hello, java, belk));
        for(Future<String> f : futureList){
            System.out.println(f.get());
        }

        System.out.println("invokeAny ==============================");
        //모두 기다리지 않고 하나만 응답이 와도 끝내고 싶은 경우에는 invokeAny를 사용하면 된다
        String s = executorService2.invokeAny(Arrays.asList(hello, java, belk));
        System.out.println(s);


        Future<String> submit = executorService2.submit(hello);
        System.out.println(submit.isDone());
        System.out.println("Started!");

        System.out.println(submit.get()); // 블록킹

        System.out.println("End!!");


        executorService2.shutdown();

    }


    private static Runnable getRunnable(String message){
        return ()-> System.out.println(message + Thread.currentThread().getName());
    }
}
