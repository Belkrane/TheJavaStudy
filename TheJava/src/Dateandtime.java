import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

        System.out.println("============= API 사용 ===========");
        //기계적인 시간을 사용하는 방법
        Instant instant = Instant.now(); //기준시 UTC, GMT
        System.out.println(instant);
        //어느 존을 기준으로 시간을 볼거냐
        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);
        ZonedDateTime zonedDateTime = instant.atZone(zone);
        System.out.println(zonedDateTime);
        
        //사람위주의 시간 객체 -> 서버가 설치되어있는 Zone 의 시간이 쓰임
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        LocalDateTime todayGuitarLesson = LocalDateTime.of(1982, Month.JULY, 12, 20, 00, 00);
        System.out.println("todayGuitarLesson = " + todayGuitarLesson);

        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println("nowInKorea = " + nowInKorea);

        //기간을 표현하는 방법
        //2가지가 있다. Period(Human용 시간 비교), Duration(Machine용 시간 비교)

        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2020, Month.JULY, 31);

        Period period = Period.between(today, thisYearBirthday);
        System.out.println("period.getDays() = " + period.getDays());

        Period until = today.until(thisYearBirthday);
        System.out.println("until.get(ChronoUnit.DAYS) = " + until.get(ChronoUnit.DAYS));


        Instant now2 = Instant.now();
        Instant plus = now2.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(now2, plus);
        System.out.println(between.getSeconds());

        //시간을 출력할때 쓰는 Formatting, DateTimeFormatter 객체에 자주쓰는 패턴들이 정의되어있음
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(dateTime.format(MMddyyyy));
        
        //Parsing
        LocalDate parse = LocalDate.parse("07/15/1982", MMddyyyy);
        System.out.println(parse);
    }
}
