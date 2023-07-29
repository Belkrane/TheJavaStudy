public class MetaspaceTest {
    public static void main(String[] args) {
        /**
         * PermGen
         * permanent generation, 클래스 메타데이터를 담는 곳(Heap 영역의 일부, 클래스를 많이 쓰면 많이 쓸수록 커짐)
         * 제한된 크기를 가지고 있음
         * -XX:PermSize=N, PerGen 초기 사이즈 설정
         * -XX:MaxPermSize=N, PermGen 최대 사이즈 설정
         * OS 가 제공하는 메모리에서 자바 영역이 생기고, 그 안에 Heap 영역이 생김
         * Eden -> Old -> PermGen
         */

        /**
         * Java 8부터는 JVM Heap에서 PermGen 영역이 없어지고
         * Eden -> Old 가 끝이고
         * 네이티브 영역에 Metaspace가 자리잡음(Heap이 아니라 별도로)
         * 고정된 사이즈가 없음, 필요한 만큼 계속 늘어난다.
         * -XX:MetaspaceSize=N, Metaspace 초기 사이즈 설정
         * -XX:MaxMetaspaceSize=N, Metaspace 최대 사이즈 설정(서버 메모리 전체를 잡아먹지 않도록..)
         */
    }
}
