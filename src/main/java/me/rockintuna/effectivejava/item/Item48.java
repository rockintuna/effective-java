package me.rockintuna.effectivejava.item;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Stream;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;

/*
스트림 병렬화는 주의해서 적용하라
 */
public class Item48 {
    public static void main(String[] args) {
        List<String> list = List.of("a", "b", "c", "d", "e", "f", "g", "h");

        /*
        parallel() 메서드를 사용하면 스트림 파리프라인을 병렬 실행할 수 있게 된다.
         */
        list.stream()
                .map(String::toLowerCase)
                .parallel()
                .forEach(System.out::println);

        /*
        하지만 스트림 병렬화는 주의해서 사용해야 하는데,
        아래 코드처럼
        데이터 소스가 Stream.iterate거나
        중간 연산으로 limit을 쓰면
        파이프라인 병렬화로는 성능 개선을 기대할 수 없다.
         */
        primes().map(p -> TWO.pow(p.intValueExact()).subtract(ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                .parallel()
                .forEach(System.out::println);
    }

    static Stream<BigInteger> primes() {
        return Stream.iterate(TWO, BigInteger::nextProbablePrime);
    }

    /*
    일반적으로 병렬화를 사용하면 좋은 스트림
    - 데이터 소스가 ArrayList, HashMap, HashSet, ConcurrentHashMap의 인스턴스
    - 또는 배열, int 범위, long 범위일
    - 축소 종단 연산 (reduce, min, max, count, sum, anyMatch, allMatch, noneMatch)
    (반면 가변 축소 collect는 병렬화에 적합하지 않다.)

    스트림을 잘못 사용하면 성능뿐만 아니라 결과 자체가 잘못되거나 예상치 못한 동작이 발생할 수 있다.(safety failure)

    스트림 병렬화는 오직 성능 최적화 수단이다.
    계산이 정확한지, 그리고 성능이 좋아졌는지를 꼭 테스트 해야한다.
     */
}
