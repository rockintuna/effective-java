package me.rockintuna.effectivejava.item;

/*
추상 클래스보다는 인터페이스
 */
public class Item20 extends AbstractItem20Interface{
    /*
    JAVA 추상클래스 대비 인터페이스의 장점
    - 기존 클래스가 있는 경우 추상클래스를 구현하는 것 보다 인터페이스를 구현하는 것이 쉽다.
    - 믹스인 정의에 안성맞춤이다. [선택적 기능 추가]
    - 계층구조가 없는 타입 프레임워크를 만들 수 있다. (단순하게 인터페이스 조합)
     */
}

interface Item20Interface {
    String getStart();

    /*
    골격 구현
     - 가능한한 디폴트 메서드를 사용
     - 필요한 경우 골격 구현 클래스 사용
     */
    default void log(String message) {
        System.out.println(message);
    }
}

/*
골격 구현 클래스
 */
abstract class AbstractItem20Interface implements Item20Interface {

    //골격 구현 클래스를 확장하는 것 만으로 인터페이스를 구현하는 데 필요한 일이 대부분 완료된다.
    //설계가 잘 되어있을수록 효율적으로 사용할 수 있다.
    @Override
    public String getStart() {
        return "";
    }
}