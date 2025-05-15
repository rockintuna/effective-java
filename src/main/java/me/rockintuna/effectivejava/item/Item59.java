package me.rockintuna.effectivejava.item;

import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;

/*
라이브러리를 익히고 사용하라
 */
public class Item59 {

    static Random random = new Random();

    static int random(int n) {
        return Math.abs(random.nextInt()) % n;
    }
    /*
    이 코드의 문제
    1. n이 2의 제곱수면 같은 수열이 반복된다.
    2. n이 2의 제곱수가 아니면 몇몇 숫자가 더 자주 반환된다.
    3. 지정한 범위 바깥의 수가 종종 튀오나올 수 있다.
    int의 최소값 -2147483648을 Math.abs()에 입력하면 절대값이 반환되지 않는다.
     */

    public static void main(String[] args) {
        int value = Integer.MIN_VALUE;
        System.out.println(value); // -2147483648
        System.out.println(Math.abs(-2147483647)); // -2147483648
    }

    //Random.nextInt()는 위와 같은 오류가 없다.
    static int random2(int n) {
        return random.nextInt(n);
    }

    public static void main2(String[] args) {
        //java 7 부터는 Random 보다 ThreadLocalRandom 을 사용하자
        ThreadLocalRandom random = ThreadLocalRandom.current();
        random.nextLong();

        //thread safe가 필요한 경우 SplittableRandom 을 사용하자
        SplittableRandom splittableRandom = new SplittableRandom();
        splittableRandom.nextLong();
    }

    /*
    ==== 바퀴를 다시 발명하지 말자 ====

    표준 라이브러리(java.util, java.lang, java.io 등)를 사용하면
    - 그 코드를 작성한 전문가의 지식과 다른 프로그래머들의 경험을 활용할 수 있다.
    - 그리고 애플리케이션의 핵심 기능 개발에 집중할 수 있다.
    - 지속적으로 알아서 성능이 개선된다.
    - 추가 기능이 계속 개발된다.
    - 재사용, 공유, 유지보수하기 쉬운 코드가 된다.

     표준 라이브러리는 있는지 몰라서 못쓰는 경우가 많으니
     JAVA 메이저 릴리즈 설명 공시를 읽어보면 좋다.
     특히
     컬렉션 프레임워크 / 스트림 라이브러리
     java.util.concurrent의 동시성 제어

     표준 라이브러리에서 필요한 기능을 찾지 못했다면
     구아바 같은 서드파티 라이브러리를 사용하는 것도 좋다.
     */
}
