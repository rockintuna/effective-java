package me.rockintuna.effectivejava.item;

import java.util.Comparator;

/*
박싱된 기본 타입보다는 기본 타입을 사용하라
 */
public class Item61 {

    /*
    기본 타입과 박싱된 기본 타입의 차이
    1. 기본 타입은 값만 가지고 있으나 박싱된 기본 타입은 값에 더해 식별성이라는 속성을 가진다.
    "식별성", 박싱된 기본 타입의 두 인스턴스는 값이 같아도 서로 다르다고 식별될 수 있다.
    2. 박싱된 기본 타입은 유효하지 않은 값 (null)을 가질 수 있다
    3. 기본 타입이 박싱된 기본 타입보다 시간과 메모리 사용면에서 효율적이다.

    JAVA에서는 오토박싱과 오토언박싱 기능을 통해 기본 타입과 박싱된 기본 타입을 구분하지 않고 사용할 수 있지만
    위 차이 때문에 주의하며 사용해야 한다.
     */

    public static Comparator<Integer> naturalOrder =
            (i, j) -> (i < j) ? -1 : (i == j) ? 1 : 0;
    //잘못된점!
    //(i < j)는 오토언박싱되어 값을 비교한다.
    //(i == j)는 값을 비교하는게 아니고 두 객체 참조의 식별성을 검사한다.
    //곧, 박싱된 기본타입에서 == 를 사용하여 값을 비교하면 안된다.

    public static void test(String[] args) {
        Integer i1 = 42;
        Integer i2 = 42;

        int compare = naturalOrder.compare(i1, i2);
        System.out.println(compare); //0이 아닌 1을 return 한다!

        //실무에서 박싱된 기본 타입을 다루는 비교자가 필요하면 Comparator.naturalOrder() 사용
        Comparator<Integer> tComparator
                = Comparator.naturalOrder();
        int compare1 = tComparator.compare(i1, i2);
        System.out.println(compare1); // return 0
    }

    static Integer i;

    public static void main(String[] args) {
        if (i == 42) {
            System.out.println("i is 42");
        }
        //NullPointerException이 발생한다.
        // 기본 타입과 박싱된 기본 타입을 혼용한 연산에서는 박싱된 기본 타입의 박싱이 풀린다.
        // (i == 42) 연산에서 i가 int로 언박싱되면서 NullPointerException이 발생하는 것이다.

        /*
        박싱된 기본 타입은 언제쓰나?
        - 컬렉션의 원소, 키, 값으로 사용해야 할 때
        - 리플렉션을 통해 메서드를 호출할 때
         */
    }

}
