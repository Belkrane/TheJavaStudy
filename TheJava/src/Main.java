import java.util.Arrays;
import java.util.function.*;

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

        /*
            Java에서 기본적으로 제공해주는 함수Interface들

         */

        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(1));

        UnaryOperator<Integer> plus10_2 = (i) -> i + 10;
        Function<Integer, Integer> multiply2 = (i) -> i * 2;

        System.out.println(plus10_2.apply(1));

        Function<Integer, Integer> mulitply2AndPlus10 = plus10_2.compose(multiply2);

        System.out.println("plus10_2 = " + mulitply2AndPlus10.apply(2));

        System.out.println(" = " + plus10_2.andThen(multiply2).apply(2));

        Consumer<Integer> printT = (i) -> System.out.println("i = " + i);
        printT.accept(10);

        Supplier<Integer> get10 = () -> 10;
        System.out.println("get10.get() = " + get10.get());

        Predicate<String> startsWithBelk = (s) -> s.startsWith("belk");
        Predicate<Integer> isEven = (i) -> i % 2 == 0;

        /*
            Lambda 표현식
            (인자) -> {바디}
            인자가 없으면 생략 가능
            인자가 여러개일 때 (one, two)
            바디가 한 줄이면 {} 생략 가능

            로컬 변수는 final인 경우에만 참조할 수 있다.
            혹은 effective final(사실상 파이널) 파이널 선언하지 않았지만, 파이널처럼 수정이 없는 경우  
         */
    }
}