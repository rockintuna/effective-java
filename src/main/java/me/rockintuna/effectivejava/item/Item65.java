package me.rockintuna.effectivejava.item;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/*
리플렉션보다는 인터페이스를 활용하라
 */
public class Item65 {

    /*
    리플렉션으로 하고자 하는 것
    - 클래스의 생성자, 메서드, 필드에 접근 및 조작

    단점
    - 컴파일타임의 검사를 하지 못함
    - 코드가 지저분해지고 장황해짐
    - 성능 저하

    리플렉션은 아주 제한된 형태로만 사용해야 그 단점을 피하고 이점만 취할 수 있다.

    컴파일 타임에 이용할 수 없는 클래스를 사용하는 프로그램에서는
    인스턴스 생성에서만 리플랙션을 사용하고
    인스턴스를 인터페이스 또는 상위 클래스로 참조하여 사용할 수 있다.
     */

    //Set<String>로 참조되는 인스턴스를 사용할 프로그램
    //컴파일타임에는 클래스를 알 수 없음
    public static void main(String[] args) {

        Class<? extends Set<String>> cl = null;
        try {
            //클래스 로딩 리플렉션
            //비검사 형변환
            cl = (Class<? extends Set<String>>) Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Constructor<? extends Set<String>> cons = null;
        try {
            //생성자 사용 리플렉션
            cons = cl.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        Set<String> set;
        try {
            //인스턴스 생성 리플렉션
            //Set 인터페이스 타입으로 참조함.
            set = cons.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        /*
        이 코드에서 보여지는 리플렉션의 단점
        1. 리플렉션에서 발생할 수 있는 런타임 예외가 많다.
        2. 코드가 매우 장황하다. (정말 단순한 기능인 것에 비해)

        리플렉션 tip.
        - 모든 리플렉션 예외를 작성할때는 ReflectiveOperationException 을 사용하면 편하다.

       리플렉션이 적합한 경우
        - 컴파일 타임에 알 수 없는 클래스를 사용해야 할 때
        - 런타임에 존재하지 않을 수도 있는 다른 클래스, 메서드, 필드와의 의존성 관리
         */
    }

}
