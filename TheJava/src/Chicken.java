import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
//TYPE_USE: type을 사용하는 모든곳
//TYPE_PARAMETER : type 파라미터 앞에만 사용할 수 있음
@Target(ElementType.TYPE_USE)
@Repeatable(ChickenContainer.class)
public @interface Chicken {
    String value();
}
