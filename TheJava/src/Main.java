public class Main {
    public static void main(String[] args) {
        // 익명 내부 클래스 anonymous inner class
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };
        //람다 표현식으로
        RunSomething runSomething1 = () -> System.out.println("Hello");

        //한줄이 아닌 경우
        RunSomething runSomething2 = () -> {
            System.out.println("Hello");
            System.out.println("Lambda");
        };

        runSomething2.doIt();
    }
}