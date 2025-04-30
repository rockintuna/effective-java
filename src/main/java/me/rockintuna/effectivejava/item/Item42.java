package me.rockintuna.effectivejava.item;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
익명 클래스보다는 람다를 사용하라
 */
public class Item42 {


    public static void main(String[] args) {
        List<String> words = Arrays.asList("ab", "b", "ce", "dfe", "easd");

        //익명 클래스로 생성한 함수 객체
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        /*
        예전 버전의 자바에서는 위와 같이 작성해야 했으나 코드가 길다는 단점이 있었다.
        자바 8부터 람다로 함수형 인터페이스를 구현하는 기능을 사용할 수 있다.

        함수형 인터페이스 :
        추상 메서드 하나만 있는 인터페이스,
        ex) Comparator
         */
        //람다식으로 변경
        Collections.sort(words, (o1, o2) -> Integer.compare(o1.length(), o2.length()));
        /*
        람다식은 작은 규모의 함수 객체 구현을 매우 간결하게 해준다.
        타입 추론을 통해 함수 객체 타입 Comparator, 매개변수 타입 String, 반환값 타입 int가 추론되었다.
         */

        //비교자 생성 정적 메서드, method reference, List.sort() 사용
        words.sort(Comparator.comparingInt(String::length));

        /*
        람다식을 안써야할 때
        - 코드가 세줄 이상
        - 코드에 설명이 필요할 때
        람다식을 못쓰는 상황
        - 인터페이스가 아닐 때
        - 인터페이스인데 추상 메서드가 여러개일 때
        - 인스턴스 자신을 참조(this)할 필요가 있을 때
        => 익명 클래스

        주의 : 람다, 익명 클래스는 직렬화 하면 안됨
         */

    }
}
