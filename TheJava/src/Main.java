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


        /*
            Lambda의 Method 레퍼런스
            똑같은 일을 하는 Method를 사용해서 간결하게 작성 가능
         */
        Greeting greeting = new Greeting();
        UnaryOperator<String> hi = Greeting::hi; //메소드가 생성된게 아님.. apply를 해야 메서드가 동작함
        UnaryOperator<String> hello = greeting::hello;
        Supplier<Greeting> greetingSupplier = Greeting::new; //실제 인스턴스가 만들어지는게 아님
        Function<String, Greeting> greetingFunction = Greeting::new; //레퍼런스는 같아 보이지만 서도 다른 생성자를 참조 하고 있음

        Greeting belk = greetingFunction.apply("belk");
        System.out.println("belk.getName() = " + belk.getName());


        /*
        Default Method
        인터페이스에 method가 추가되었을때, 구현체에 Override를 하지 않으면 구현체에서
        컴파일 오류가 발생한다. 하지만 모든 구현체에 다 추가 할 수는 없기 때문에
        defualt function을 인터페이스에 구현하면
        모든 구현체에서 해당 function을 override를 안해도 된다.

        defalut method는 구현체가 알 수 없기 때문에, @ImplSpec 으로 문서화하는 것이 좋다.
        두개의 인터페이스를 동시에 구현하는 구현체에서 같은 default method가 있는 경우에는 컴파일 에러 발생, override해야함
        */

        Foo foo = new DefaultFoo("belk");
        foo.getName();
        foo.printName();
        foo.printNameUpperCase();

        Foo.printAnything();
    }



}