public class CompletableFuture {
    public static void main(String[] args) {
        /* Cuncurrent S/W
         * 동시에 여러 작업을 할 수 있는 S/W
         * -> Java에서는 멀티프로세싱, 멀티쓰레드 두 가지 방법으로 이런 기능을 지원함
         * 자바에서 멀티쓰레드 프로그래밍을 하려면 어떻게 해야하나
         */

        //Thread를 상속받아서 구현
        MyThread myThread = new MyThread();
        myThread.start();

        System.out.println("Hello main: " + Thread.currentThread().getName());

        //그냥 Thread를 씀 -> Runnable을 줘서 줌
         Thread thread = new Thread(new Runnable() {
             @Override
             public void run() {

                 System.out.println("Thread: " + Thread.currentThread().getName());
                 try{
                     Thread.sleep(3000L);
                 } catch (InterruptedException e) {
                     System.out.println("Interrupted!!");
                 }
             }
         });

         thread.start();

         //람다식으로 만들기
        Thread threadLambda = new Thread(() -> System.out.println("Lambda Thread: " + Thread.currentThread().getName()));
        threadLambda.start();

        //Thread의 주요한 기능 3가지
        //1. sleep : 현재 쓰래드를 대기 시킨다.
        //2. interrupt : 다른 쓰레드를 깨우는 것
        //3. join : 다른 스레드를 기다리는 것,이것도 대기하는것이기 때문에 대기하는 도중에 Interrupt 발생할 수 있음
        //Multi Thread프로그램의 문제점!! -> 쓰레드가 늘어날수록 대기하고 깨우고.. 복잡해짐!
        //사실상 프로그래머가 수십 수백개의 쓰레드를 코딩으로 관리할수는 없음

        try {
            thread.join();
            System.out.println(thread + " is finished");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //thread.interrupt();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello: " + Thread.currentThread().getName());
        }
    }
}
