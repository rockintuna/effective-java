package me.rockintuna.effectivejava.item;

import org.springframework.util.StopWatch;

/*
문자열 연결은 느리니 주의하라
 */
public class Item63 {

    public static void main(String[] args) {
        //문자열 연결
        String comb = "abc" + "def";

        /*
        문자열 연결 연산자로 문자열 n개를 잇는 시간은 n^2에 비례한다. (O(N^2))
        문자열은 불변이기 때문에 연결되는 문자열 모두를 복사해야 한다.

        성능적으로 매우 좋지 못하므로
        연결을 자주 시키거나 여러 문자열을 연결해야 한다면
        문자열 연결 대신 StringBuilder를 사용하자.

        이 둘의 성능 차이는 연결하는 문자열의 수가 많아질수록 커진다.
         */
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String result1 = "";
        for (int i = 0; i < 100000; i++) {
            result1 += i;
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
        //2356 ms

        stopWatch = new StopWatch();
        stopWatch.start();
        //tip.
        //StringBuilder를 초기화할 때 크기를 미리 지정해줄 수 있다면 더 빨리 작업할 수 있다.
        StringBuilder result2 = new StringBuilder(500000);
        for (int i = 0; i < 100000; i++) {
            result2.append(i);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
        //3 ms
    }
}
