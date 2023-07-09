import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Optional {
    /*
     * 값 하나만을 가지고 있을 수 있는 컨테이너, 인스턴스
     * 클라이언트 코드가 특정 상황에 따라 코딩하던 것을
     * 명시적으로 표현 할 수 있음
     * Optional은 여러가지 상황에서 쓸 수 있지만 리턴 타입에만 쓰는 것을 권장
     * 의도적으로 클라이언트 코드에서 null을 넘길 수 있기 때문에.. 더 번거로워짐
     */

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        OnlineClass spring_boot = new OnlineClass(1, "spring_boot", true);
        Duration studyDuration = spring_boot.progress.getStudyDuration();
        System.out.println("studyDuration = " + studyDuration);
    }
}
