import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        System.out.println("==============");
        List<String> names = new ArrayList<>();
        names.add("belk");
        names.add("belkrane");
        names.add("foo");
        names.add("java");

        /*
         * forEach -> 각각의 Element에 접근
         * spliterator -> iterator와 비슷, 쪼갤 수 있는 기능을 가지고 있는 interator
         * stream -> collection 을 stream으로 만들어서 처리
         * removeIf -> 특정 조건 element 삭제
         *
         */
        //name.forEach(System.out::println);
        Spliterator<String> spliterator = names.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit();
        while(spliterator.tryAdvance(System.out::println));
        System.out.println("==============");
        while(spliterator1.tryAdvance(System.out::println));
        System.out.println("==============");
        long k = names.stream().map(String::toUpperCase).filter(s -> s.startsWith("B"))
                .count();

        System.out.println("k = " + k);

        names.removeIf(s -> s.startsWith("b"));

        /*
         * 연속된 데이터들을 처리하는 opertaion들의 모음?(저장소가 아님!)
         *
         */
        List<String> names2 = new ArrayList<>();
        names2.add("belk");
        names2.add("belkrane");
        names2.add("foo");
        names2.add("java");

        Stream<String> stringStream = names2.stream().map(String::toUpperCase);

        names2.forEach(System.out::println);

        System.out.println("==================");

        List<String> collect = names2.stream().map((s)->{
            System.out.println("s = " + s);
            return s.toUpperCase();
        }).collect(Collectors.toList());

        collect.forEach(System.out::println);

        System.out.println("==================");

        List<String> collect1 = names2.parallelStream().map((s)->{
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());


        collect1.forEach(System.out::println);


        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("spring 으로 시작하는 수업");
        // TODO
        springClasses.stream().filter(oc -> oc.getTitle().startsWith("spring"))
                        .forEach(oc -> System.out.println(oc.getTitle()));

        System.out.println("close 되지 않은 수업");
        // TODO
        springClasses.stream().filter(Predicate.not(OnlineClass::isClosed))
                        .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("수업 이름만 모아서 스트림 만들기");
        // TODO
        springClasses.stream().map(OnlineClass::getTitle)
                .forEach(System.out::println);

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> belkEvents = new ArrayList<>();
        belkEvents.add(springClasses);
        belkEvents.add(javaClasses);

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        // TODO
        belkEvents.stream().flatMap(Collection::stream).forEach(oc -> System.out.println(oc.getId()));

        System.out.println("10부터 1까지 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        // TODO
        Stream.iterate(10, i-> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);
        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        boolean test = javaClasses.stream().anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println("test = " + test);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 것만 제목만 모아서 List로 만들기");
        List<String> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().contains("spring"))
                .map(oc -> oc.getTitle())
                .collect(Collectors.toList());

        spring.forEach(System.out::println);


    }



}