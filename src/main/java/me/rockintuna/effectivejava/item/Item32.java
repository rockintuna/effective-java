package me.rockintuna.effectivejava.item;

import java.util.List;

/*
제네릭과 가변인수를 함께 쓸 때는 신중하라
 */
public class Item32 {

    /*
    제네릭과 가변인수를 함께 쓸 때는 타입 안정성이 깨진다.
    이게 허용되는 이유는 실무에서 매우 유용하기 때문,
    사용하고자 한다면 타입 안전한지 확인이 필요하다.
     */

    //제네릭과 가변인수를 함께 쓸 때 타입 안정성이 깨진다.
    public static void dangerous(List<String>... stringLists) {
        //내부적으로 제네릭 배열이 만들어지며, 외부로 노출되는데
        Object[] objects = stringLists;
        //이 배열에 값을 저장하는 것은 안전하지 않다.
        List<Integer> intList = List.of(42);
        objects[0] = intList; //힙 오염 발생
        String s = stringLists[0].toString(); //런타임 오류 ClassCastException
    }

    //@SafeVarargs 어노테이션은 이 제네릭 가변인수 메서드가 타임 안전함을 보장한다.
    //메서드 작성 측에서의 경고도 없애고
    //클라이언트에서 @SuppressWarnings 같은 어노테이션으로 경고를 숨기는 작업을 하지 않아도 된다.
    @SafeVarargs
    public static void safe(List<String>... stringLists) {
        /*
        varargs 매개변수 배열에 아무것도 저장하거나 덮어쓰지 않고,
        그 배열의 참조가 밖으로 노출되지 않는다면 (@SafeVarargs가 달린 정말 안전한 메서드 제외)
        안전하다고 보장할 수 있다.
         */
        String s = stringLists[0].toString();
    }

    /*
    @SafeVarargs 사용 원칙
    - varargs 매개변수 배열에 아무것도 저장하거나 덮어쓰지 않기
    - 그 배열의 참조가 신뢰할 수 없는 코드에 노출되지 않도록 하기
    - 위 규칙을 모든 메서드에 적용하고 제네릭이나 매개변수화 타입의 varargs 매개변수를 받는 모든 메서드에 @SafeVarargs 달기

    varargs 매개변수 대신 List로 구현하는 것도 방법
     */
}
