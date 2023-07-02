
@FunctionalInterface
public interface RunSomething {

    //추상 메서드가 하나 있으면 함수형 인터페이스
    abstract void doIt();

    //static method 정의할 수 있음
    public static void printName(){
        System.out.println("Belk");
    }

    /* default method도 정의 할 수 있음. 함수형 인터페이스에는 다양한 형태의 mehod를 추가 할 수 있다
        중요한 것은 추상 method가 하나여야 함.
     */
    default void printAge() {
        System.out.println("32");
    }
}
