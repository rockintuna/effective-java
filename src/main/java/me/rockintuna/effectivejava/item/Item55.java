package me.rockintuna.effectivejava.item;

import java.util.*;
import java.util.stream.Stream;

/*
옵셔널 반환은 신중히 하라
 */
public class Item55 {
    /*
    메서드에서 특정 조건에서 값을 반환할 수 없는 경우 선택지
    - 예외 : 진짜 예외적일때만 사용해야 함
    - null : null 처리 코드를 작성해야함 (안하면 나중에 오류 발생 가능)
    - Optional

    보통은 T를 반환하지만 특정 조건에서 아무것도 반환하지 않아야 하는 메서드는
    T 대신 Optional<T>를 반환하도록 하면 된다.
    => 예외보다 유연하고 null보다 오류 가능성이 적음
     */
    public Optional<Integer> max(Collection<Integer> values) {
        if ( values == null || values.isEmpty() ) {
            //빈 Optional 생성
            return Optional.empty();
        }

        int max = Integer.MIN_VALUE;
        //todo
        //값이 있는 Optional 생성
        return Optional.of(max);
    }

    /*
    Optional을 반환하는 메서드에서는 null을 반환하면 안된다.

    Optional은 검사 예외와 취지가 비슷한데, 메서드를 사용하는 사용자에게
    메서드의 반환값이 있을수도 있고 없을수도 있다는 것을 명확하게 알려준다.
    (null이나 비검사 예외는 인지하기 어렵다.)
     */

    /*
    Optional 객체 사용
     */
    public static void main(String[] args) {
        Item55 item55 = new Item55();
        Optional<Integer> optional = Item30.max(List.of(1, 2, 3, 4, 5));

        //값 뽑기
        optional.get(); //비어있는 경우 예외 발생
        optional.orElse(defaultInt()); //비어있는 경우 기본값 (비어있지 않아도 값은 초기화 됨)
        optional.orElseGet(Item55::defaultInt); //비어있는 경우 기본값 (Suppler를 입력하여 초기화 지연)

        //etc
        // - filter
        // - map
        // - flatMap
        // - ifPresent

        //존재 여부 체크
        optional.isPresent();
        optional.isEmpty();

        //존재 여부 체크는 대부분 위 메서드들로 대체할 수 있기 때문에
        //위 메서드를 사용해보는 것이 더 Optional 용법에 맞는 코드이다.
        //이와 관련된 몇가지 팁

        if ( optional.isPresent() ) {
            Integer i = optional.get();
            System.out.println(i * i);
        } else {
            System.out.println(0);
        }
        //==== 같은 동작
        System.out.println(optional.map(i -> i * i).orElse(0));

        Stream<Optional<Integer>> streamOfOptionals = Stream.of(optional);
        streamOfOptionals
                .filter(Optional::isPresent)
                .map(Optional::get);
        //==== 같은 동작
        streamOfOptionals
                .flatMap(Optional::stream);


        /*
        Optional 주의할 점
        - 컬렉션, 스트림, 배열, 옵셔널 같은 컨테이너 타입은 옵셔널로 감싸면 안된다.
        (빈 Optional보다 빈 컨테이너를 반환하는게 좋다)
        - 성능이 매우 중요한 상황에서는 맞지 않을 수 있다.
        - 옵셔널을 컬렉션이나 배열의 키, 값, 원소로 사용하는 건 적절하지 않다.
        - 반환값 이외의 용도로 쓰는 경우는 매우 드물다.
        - 박싱된 기본 타입을 담는 Optional의 경우 특별한 타입의 Optional을 사용하자.
         */
        OptionalInt optionalInt;
        OptionalDouble optionalDouble;
        OptionalLong optionalLong;
    }

    public static int defaultInt() {
        return 1;
    }
}
