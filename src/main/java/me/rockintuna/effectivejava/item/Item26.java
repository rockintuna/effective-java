package me.rockintuna.effectivejava.item;

import java.util.ArrayList;
import java.util.List;

/*
로 타입은 사용하지 말자
 */
public class Item26 {

    public static void main(String[] args) {

        //제네릭 타입 List<E>
        //타입 매개변수를 선언하면 타입 안정성을 확보할 수 있다.
        List<String> stringList = new ArrayList<>();

        //제네릭 인터페이스 List<E> 의 로 타입 List
        //로 타입은 이전 Java 버전과의 호환성 때문에 제공됨
        //런타임에서 오류(ClassCastException 등) 발생 가능성이 높다!
        List rawTypeList = new ArrayList<>();
        rawTypeList.add(1L);
        rawTypeList.add("abc");

        /*
        로타입을 절대 쓰면 안돼는 이유
        - 로타입을 쓰면 제네릭이 안겨주는 안정성과 표현력을 모두 잃게 된다.
         */
    }

    //어떤 타입이든 넣고 싶다면 대신 List<Object>를 사용하자
    //List<Object>를 매개변수로 받으면 List<String> 등은 인자값으로 사용할 수 없다.
    //(List<String>은 List<Object>의 하위 타입이 아니다.)
    public static void demo0(List<Object> objectTypeList) {
        //unchecked warning이 발생하지 않는다.
        objectTypeList.add(10);
    }

    /*
    매개변수로 어떤 타입 매개변수의 제네릭 클래스든 받고 싶다면?
    로 타입 보다는 wildcard 타입을 대신 사용하자
    wildcard는 원소를 사용하거나 입력할 수 없게 만들어 타입 안정성을 보장한다.
     */
    public static void demo1(List rawTypeList) {
        //unchecked warning
        rawTypeList.add(10);
    }

    public static void demo2(List<?> wildcardList) {
        //입력시 컴파일 에러
        //wildcardList.add(Integer.valueOf(10));
    }

}
