import java.util.Arrays;

@Chicken("양념")
@Chicken("마늘간장")
public class ChangeAboutAnnotation {
    public static void main(String[] args) {
        //타입선언부에도 사용할 수 있게 됨
        // 중복해서 사용할 수 있게 됨

        Chicken[] chickens = ChangeAboutAnnotation.class.getAnnotationsByType(Chicken.class);
        Arrays.stream(chickens).forEach(c -> {
            System.out.println(c.value());
        });

        ChickenContainer chickenContainer = ChangeAboutAnnotation.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(c -> {
            System.out.println(c.value());
        });

    }

    static class FeelsLikeChicken< T>{
        //<C> -> Type Parameter
        // C -> Type
        public static <C>void print(C c){

        }

    }
}
