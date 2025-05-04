package me.rockintuna.effectivejava.item;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/*
반환 타입으로는 스트림보다 컬렉션이 낫다
 */
public class Item47 {

    /*
    스트림은 for-each 문에서 사용될 수 없고 iterable은 스트림으로 사용할 수 없다.
    자바는 이 둘의 변환을 지원해주지 않지만 쉽게 구현할 수 있다.
     */

    //stream -> iterable
    public static <E> Iterable<E> iterableOf(Stream<E> stream) {
        return stream::iterator;
    }
    //iterable -> stream
    public static <E> Stream<E> streamOf(Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    /*
    굳이 반환이 필요 없는 경우,
    즉, 오직 스트림 파이프라인에서만 사용될 걸 알거나, 반복문에서만 쓰일 걸 안다면
    각각 Stream, Iterable로 반환하자

    만약 공개 API라면 만약 둘 다 지원하게 해주는게 옳다.
    Collection 인터페이스는 Iterable의 하위 타입이면서 stream 메서드를 제공한다.
    따라서 원소 시퀀스를 반환하는 공개 API의 반환 타입은 Collection이나 그 하위타입을 쓰자.

    위에 있는 메서드 처럼 클라이언트에서 변환을 할 수는 있겠지만,
    Collection을 반환하여 사용하는 것 보다 성능적으로 좋지 않다.
     */
}
