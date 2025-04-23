package me.rockintuna.effectivejava.item;

/*
enum의 ordinal 메서드 대신 인스턴스 필드를 사용하라
 */
public class Item35 {

    enum DAY {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    public static void main(String[] args) {
        /*
        ordinal() 메서드는 정의된 열거 타입 상수의 순서를 리턴한다.
        enum의 ordinal 메서드에 의미를 두어 사용하는 건 좋지 않다.
        - enum 상수의 순서가 바뀌면 ordinal 결과도 바뀌기 때문에 오류가 발생할 수 있다.
        - 다른 상수에서 동일한 정수값을 가지게 할 수 없다.
        - 정수값은 1씩 증가해야하며
        - 정수값을 중간에 띄우려면 더미 상수를 만들어야 한다.

        이런 단점들이 있으니 ordinal 대신 인스턴스 필드에 정수값을 저장하는 것이 옳다.
        ordinal은 그냥 사용하지 말자.
         */
        int ordinal = DAY.MONDAY.ordinal();
        System.out.println(ordinal); // 0
    }
}
