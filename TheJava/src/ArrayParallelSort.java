import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ArrayParallelSort {
    public static void main(String[] args) {
        //parallelSort라는 것이 생긴
        //알고리즘이 변경된건 아니지만 조금 더 빠르게 정렬을 할 수 있음

        int size = 15000;
        int[] numbers = new int[size];
        Random random = new Random();
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

        long start = System.nanoTime();
        Arrays.sort(numbers);
        System.out.println("serial sorting took " + (System.nanoTime() - start));

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();
        Arrays.parallelSort(numbers);
        System.out.println("parallel sorting took " + (System.nanoTime() - start));
    }
}
