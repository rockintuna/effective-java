package me.rockintuna.effectivejava.item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.*;

/*
표준 함수형 인터페이스를 사용하라
 */
public class Item44 {

    /*
    java.util.function 패키지에는 다양한 용도의 표준 함수형 인터페이스가 있다.
    용도에 맞는게 있다면 직접 구현하지 말고 이걸 활용하자

    표준 함수형 인터페이스의 장점
     - API가 다루는 개념의 수가 줄어들어 익히기 쉬워진다.
     - 유용한 디폴트 메서드를 많이 제공하여 다른 코드와 상호운용성이 좋다.

     */

    // 기본적인 표준 함수형 인터페이스
    //UnaryOperator : 반환값과 인수의 타입이 동일 (인수 1개)
    UnaryOperator<String> identityOperator = UnaryOperator.identity();

    //UnaryOperator : 반환값과 인수의 타입이 동일 (인수 2개)
    BinaryOperator<String> maxByOperator = BinaryOperator.maxBy(String::compareTo);

    //Predicate : 인수하나를 받아 boolean 반환
    Predicate<String> isEmptyPredicate = o -> !o.isEmpty();

    //Function : 인수와 반환타입이 다름
    Function<String, Integer> stringToIntFunction = Integer::valueOf;
    //Operator는 Function<T, T>를 상속받는다.

    //Supplier : 인수 없음
    Random random = new Random();
    Supplier<Integer> randomIntegerSupplier = () -> random.nextInt();

    //Consumer : 인수하나를 받고 반환값이 없음
    List<String> list = new ArrayList<>();
    Consumer<String> addToListConsumer = a -> list.add(a);

    //기본 인터페이스들은 저마다 기본 타입 int, long, double용으로 변형 인터페이스가 존재한다.
    //ex) IntPredicate
    IntPredicate isNotZeroPredicate = value -> value != 0;

    //Function 인터페이스는 기본 타입용 변형이 추가로 더 있다.
    // 입력 및 출력이 모두 기본타입
    LongToIntFunction longToIntFunction;
    IntToDoubleFunction intToDoubleFunction;
    // 등등...
    // 출력이 기본타입
    ToLongFunction<String> toLongFunction;
    ToIntFunction<Item44> toIntFunction;

    //기본 인터페이스에서 인수를 하나 추가로 받는 변형
    BiPredicate<String, Long> biPredicate;
    BiConsumer<String, Integer> biConsumer;
    BiFunction<String, Integer, Long> biFunction;

    //Supplier boolean 반환 변형
    BooleanSupplier booleanSupplier = () -> random.nextBoolean();

    /*
    주의점
    - 기본 함수형 인터페이스에 박싱된 기본 타입을 넣어 사용하지 말자
    --> 성능 저하

    표준 함수형 인터페이스 대신 직접 구현해야 하는 경우
    - 요구사항을 지원하지 않을 때
    - 훨신 명확한 명칭을 사용하는게 더 나을 때 (ex) Comparator
    - 반드시 따라야 하는 규약이 추가로 있을 때
    - 유용한 디폴트 메서드가 있으면 좋을 때
     */


    /*
    @FunctionalInterface 애너테이션의 의미
    - 이 인터페이스는 람다용으로 설계됨
    - 추상 메서드 하나 (컴파일러가 검사)
    - 메서드 추가 불가능 (컴파일러가 검사)

    만약 함수형 인터페이스를 직접 구현한다면, @FunctionalInterface를 꼭 달자.
     */
}