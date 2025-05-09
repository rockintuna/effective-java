package me.rockintuna.effectivejava.item;

import java.util.ArrayList;
import java.util.List;

/*
다중정의는 신중히 사용하라
 */
public class Item52 {
    /*
    다중정의 = 오버로딩

    재정의한 메서드는 동적으로 선택되는 한편
    다중정의한 메서드는 정적으로 선택된다.
    즉, 컴파일 타임에 입력되는 매개변수의 타입에 따라 이미 정해진다.

    이게 문제가 되는 이유는
    개발자가 선택되길 의도한 메서드가 선택되지 않을 수 있기 때문이다.
    따라서 프로그램이 오동작하기 쉬워진다.

    다중정의가 선택되는 알고리즘이 어렵고
    개발자가 이를 이해하고 어떤 메서드가 선택될지를 구분하는 것이 어려워지기 때문에 개발이 어려워진다.
     */
    public static String classify(String string) {
        return "String";
    }

    public static String classify(Integer integer) {
        return "Integer";
    }

    public static String classify(Object object) {
        return "Object";
    }

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        String string = "String";
        list.add(string);
        Integer integer = Integer.valueOf(1);
        list.add(integer);
        Object object = new Object();
        list.add(object);

        /*
        결과는 String Integer Object
         */
        System.out.println(classify(string));
        System.out.println(classify(integer));
        System.out.println(classify(object));

        /*
        결과는 Object Object Object
         */
        for (Object o : list) {
            System.out.println(classify(o));
        }
    }
    /*
    혼동을 일으키는 다중정의를 피하려면
     - 매개변수의 개수가 같은 다중정의는 만들지 말자
     (대신 다른 이름의 메서드 사용하며, 생성자라면 정적 팩터리 메서드를 사용하자)
     - 매개변수의 개수가 같아야 한다면 서로 근본적으로 다른 타입을 사용하자.
     (근본적으로 다르다는 것은 서로 형변환 될 수 없다는 것을 의미함)
     - 근본적으로 같은 타입을 궂이궂이 사용해야 한다면 같은 객체를 입력받는 다중정의 메서드 모두 동일하게 동작해야 한다.
     이 방법은 덜 특수한(더 일반적인) 메서드로 일을 넘기는 것 (forward)
     - 참조 타입을 사용하는 다중정의 조심 (int, Object)
     - 서로 다른 함수형 인터페이스이더라도 같은 위치의 인수로 받으면 안된다.
     */
}
