package me.rockintuna.effectivejava.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

/*
이왕이면 제네릭 메서드로 만들자
 */
public class Item30 {

    /*
    입력값이나 반환 값을 명시적으로 형변환해야 하는 메서드보다 제네릭 메서드가 더 안전하며 사용하기 쉽다.
     */

    //로 타입을 매개변수나 반환 타입으로 사용하면 경고가 발생하며, 타입 안전하지 않다.
    public List addRawTypeList(List list1, List list2) {
        return new ArrayList<>(list1.size() + list2.size());
    }

    /*
    제네릭 클래스가 아니어도 제네릭 메서드를 정의할 수 있다.
    접근제어자와 반환 타입 사이에 메서드에서 사용할 매개변수화 타입 선언 '<E>'
    경고 없음, 타입 안전, 형변환이 불필요하므로 사용이 쉬움
     */
    public <E> List<E> addGenericList(List<E> list1, List<E> list2) {
        return new ArrayList<>(list1.size() + list2.size());
    }

    //제네릭 메서드 팁
    public static void main(String[] args) {
        String string = "Hello World";
        //불변 객체를 여러 타입으로 활용 예시
        //제네릭 싱글턴 팩터리 패턴으로
        //String을 UnaryOperator로 활용
        UnaryOperator<String> stringIdentityFunction = IdentityFunctionFactory.getIdentityFunction();
        String apply = stringIdentityFunction.apply(string);
        assert apply.equals(string);

        //Integer를 UnaryOperator로 활용
        UnaryOperator<Integer> integerIdentityFunction = IdentityFunctionFactory.getIdentityFunction();
        Integer integer = 1;
        Integer apply2 = integerIdentityFunction.apply(integer);
        assert integer.equals(apply2);

        Optional<String> max = Item30.max(List.of("d", "e", "c", "a"));
        max.ifPresent(System.out::println);
    }

    //재귀적 타입 한정
    //매개변수 타입 E는 Comparable<E의 상위 클래스>를 구현해야 한다.
    //즉, 서로 비교 가능한 것이어야 함
    //타입 안전하면서 비교 가능한 타임임을 보장
    public static <E extends Comparable<? super E>> Optional<E> max(List<E> list) {
        if (list.isEmpty()) {
            return Optional.empty();
        }
        E max = list.getFirst();

        for (E e : list) {
            if (max.compareTo(e) < 0) {
                max = e;
            }
        }
        return Optional.ofNullable(max);
    }
}

//제네릭 싱글턴 팩터리 패턴
class IdentityFunctionFactory {
    private static final UnaryOperator<Object> IDENTITY_FUNCTION = o -> o;

    //입력되는 값이 그대로 변하지 않는 다는 것이 위 선언에서 보장되므로
    //@SuppressWarnings("unchecked")으로 경고 제거
    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> getIdentityFunction() {
        return (UnaryOperator<T>) IDENTITY_FUNCTION;
    }
}
