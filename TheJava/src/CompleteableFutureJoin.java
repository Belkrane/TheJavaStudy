import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompleteableFutureJoin {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Futre만 가지고는 어떤 작업들을 이어서 작업하는게 쉽지 않았다.
        //비동기 작업 두개를 연결하기 쉽지 않았음, 왜냐면 콜백을 줄 수 없었기 때문에

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = hello.thenCompose(CompleteableFutureJoin::getWorld);

        System.out.println(world.get());

        CompletableFuture<String> world2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("World2 " + Thread.currentThread().getName());
            return "World2";
        });

        CompletableFuture<String> future = hello.thenCombine(world2, (h, w) -> h + " " + w);

        CompletableFuture.allOf(hello, world2)
                .thenAccept((result) -> {
                    System.out.println(result);
                });
    }

    private static CompletableFuture<String> getWorld(String message){
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + " World";
        });
    }
}
