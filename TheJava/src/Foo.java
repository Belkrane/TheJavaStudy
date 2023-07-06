public interface Foo {

    void printName();

    default void printNameUpperCase(){
        System.out.println(getName().toUpperCase());
    };

    String getName();
}
