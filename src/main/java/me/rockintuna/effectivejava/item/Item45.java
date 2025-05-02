package me.rockintuna.effectivejava.item;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/*
스트림은 주의해서 사용하라
 */
public class Item45 {

    /*
    스트림 : 원소의 시퀀스
    스트림 파이프라인 : 원소들로 수행하는 연산 단계

    스트림 파이프라인의 단계
    소스 스트림 - (중간 연산) - 종단 연산
    종단 연사이 없는 스트림은 아무 작업도 하지 않는다.

    스트림은 잘 쓰면 프로그램을 짧고 깔끔하게 만들 수 있지만,
    그렇다고 스트림을 너무 과용하면 프로그램이 읽거나 유지보수하기 어려워진다.
    모든 반복문을 스트림으로 변경하지는 않아도 되니, 가독성이 더 좋은 경우에만 스트림으로 변환하자.
     */

    /*
    스트림 주의사항
     */

    //도우미 메서드를 활용하는 것은 스트림을 깔끔하게 만드는 것에 도움이 된다.

    //스트림은 원소로 기본 타입 int, long, double만 지원한다.
    //char는 지원하지 않으며
    //String의 chars() 메서드에서 반환하는 스트림의 원소는 int이다.
    //char 값을 처리할 때는 스트림을 삼가는 편이 좋다.
    public static void main(String[] args) {
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        LongStream longStream = LongStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        DoubleStream doubleStream = DoubleStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        IntStream chars = "Lee JeongIn".chars();
    }

    /*
    스트림은 함수 객체로 처리 표현
    반복문은 코드 블록으로 처리 표현

    함수 객체로는 불가능한 것들
    - 범위 안의 지역변수를 읽고 수정할 수 없다. (final or 사실상 final인 변수만 읽을 수 있고 수정은 불가능)
    - return, break, continue 사용 불가능
    - 코드 블록은 메서드 선언에 명시된 검사 예외를 던질 수 있음

    스트림이 잘 맞는 경우
    - 원소들의 시퀀스를 일관되게 변환
    - 원소들의 시컨스를 필터링
    - 원소들의 시퀀스를 하나의 연산을 사용해 결합
    - 원소들의 시퀀스를 컬렉션에 모은다.
    - 원소들의 시퀀스에서 특정 조건을 만족하는 원소를 찾는다.

    이전 단계의 원소가 필요한 코드의 경우, 스트림과는 어울리지 않는다.

    스트림 사용 팁
    - 스트림을 반환하는 메서드 이름은 원소의 복수 명사로 (ex) primes
     */
}
