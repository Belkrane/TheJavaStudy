import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Dateandtime {

    public static void main(String[] args) throws InterruptedException {
        /* 기존 유틸의 불편함
         * 기본적으로 이름이 잘못되어있음
         * thread safe하지 않다
         * 버그 발생 여지가 많음 타입안정성이 없거나, 월이 0부터 시작하거나..
         */
        Date date = new Date();
        long time = date.getTime();
        System.out.println(date);
        System.out.println(time);

        Thread.sleep(1000 * 3);
        Date after3Second = new Date();
        System.out.println(after3Second);
        after3Second.setTime(time);
        System.out.println(after3Second);

        Calendar myBirthday = new GregorianCalendar(1982, Calendar.JULY, 15);
        
        /*
         * 크게 기계용 시간과 사람용 시간으로 나눌 수 있음
         */
    }
}
