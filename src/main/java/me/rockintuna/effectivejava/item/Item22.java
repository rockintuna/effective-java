package me.rockintuna.effectivejava.item;

/*
인터페이스는 타입을 정의하는 용도로만 사용하자
 */
public class Item22 implements Item22Interface{

    public static void main(String[] args) {

        //인터페이스를 구현한 클래스의 인스턴스는 그 인터페이스 타입이 된다.
        Item22Interface demo = new Item22();
    }
}

interface Item22Interface {

}

class Item22NoConstants implements Item22InterfaceConstants {
    public static void main(String[] args) {
        Item22InterfaceConstants demo = new Item22NoConstants();
        System.out.println(AMOUNT);
    }
}

/*
상수 인터페이스 : 메서드 없이 상수 전달을 위해 상수만 정의한 인터페이스, 인터페이스를 잘못 사용한 것
내부 구현인 상수를 외부 API로 제공하는 것이기 때문에 변경이 어렵다.
이 인터페이스를 구현하는 클래스가 있다면 이 상수들에 종속될 수 있다.
 */
interface Item22InterfaceConstants {
    static final double AMOUNT = 100.0;
    static final double BALANCE = 100.0;
}
/*
상수를 공개할 필요가 있다면 위 방식 대신
- 상수와 특별히 관련되어 있는 클래스 또는 인터페이스에서 상수를 추가
- enum 사용
- 인스턴스화 할 수 없는 유틸리티 클래스 내부
 */
class Item22Utility {
    private Item22Utility() {};

    public static final double AMOUNT = 100.0;
    public static final double BALANCE = 100.0;
}