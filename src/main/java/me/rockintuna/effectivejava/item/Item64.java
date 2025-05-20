package me.rockintuna.effectivejava.item;

import java.util.HashMap;
import java.util.Map;

/*
객체는 인터페이스를 사용해 참조하라
 */
public class Item64 {

    /*
    적합한 인터페이스가 있다면
    매개변수, 반환값, 변수, 필드를
    클래스가 아닌 인터페이스 타입으로 선언하자.
     */

    public static void main(String[] args) {

        //프로그램이 훨씬 유연해진다.
        Map<String, String> set = new HashMap<>();

        //ConcuurentHashMap으로 구현체를 바꿔야하는 경우 생성자만 바꾸면 된다.
//        Map<String, String> set = new ConcurrentHashMap<>();
        //이후 참조를 사용하는 코드는 변경할게 없다.
        //using set code...

        /*
        인터페이스 타입을 사용했다면 주변코드에서는 이 인터페이스의 일반 규약 내에서의 기능에 의존하여 사용하자.
        그렇지 않다면 구현체를 바꿀 때 오류가 발생할 수 있다.

        인터페이스 대신 클래스 타입을 사용해도 되는 예
        - String, BigInteger ... 등의 값 클래스
        - 클래스 기반으로 작성된 프레임워크 객체들 (OutputStream 등)
        - 인터페이스에는 없는 특별한 메서드를 제공하는 클래스의 메서드를 꼭 사용해야 할 때
        (ex) PriorityQueue.comparator())

        되도록이면 적절한 인터페이스를 먼저 찾아보고 사용하면 프로그램을 유연하게 만들 수 있다.
        적합한 인터페이스가 없다면 클래스 계층구조 상 필요한 기능을 만족하는 가장 덜 구체적인(가장 상위의) 클래스를 타입으로 사용하자.
         */
    }
}
