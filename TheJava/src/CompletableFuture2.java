import java.util.concurrent.*;
import java.util.concurrent.CompletableFuture;

public class CompletableFuture2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //CompletableFuture 는 외부에서 Complete을 명시적으로 시킬 수 있음
        //가령 몇 초 이내에 응답이 안오면 특정 값을 리턴 -> 이런식으로 만들 수 있음
        //CompletableFuture를 쓰면 Executor를 만들어 쓸 필요가 없음
        //CompletableFuture만 가지고 비동기 작업을 만들어 쓸 수 있음

        CompletableFuture<String>future = new CompletableFuture<>();

        future.complete("belk");

        System.out.println(future.get());


        //리턴이 없는 경우 -> runAsync를 사용
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
        });
        future2.get();

        //리턴이 있는 경우 -> supplyAsyunc를 사용
        //Callback이 주고 싶다 -> thenApply를 쓰면된다
        //리턴이 없는 경우 -> thenAccept
        //결과값도 필요 없는 경우 -> thenRun

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello2 " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply((s) -> {
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });

        System.out.println(future3.get());

    }
}
