package me.rockintuna.effectivejava.item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
비검사 경고를 제거하라
 */
public class Item27 {
    public static void main(String[] args) {
        //컴파일시 -Xlint:uncheck 옵션을 추가하면 어떤 문제가 있는지 설명해준다.
        Set<String> set = new HashSet();

        /*
        비검사 경고가 발생한다는 것은 타입 안정성을 보장하지 못한다는 말이다.
        => 런타임에서 타입 관련 오류가 발생할 가능성이 있다.

        만약 경고를 제거할 수 없으나 비즈니스 로직상 타입 안전하다고 확신할 수 있다면
        @SuppressWarnings("unchecked") 어노테이션을 달아 경고를 숨길 수 있다.
        @SuppressWarnings는 가능한 한 좁은 범위에 적용하여 심각한 경고를 놓치는 일이 없도록 하자
        좀은 범위의 예 : 지역 변수
         */
        @SuppressWarnings("unchecked")
        List<String> list = new ArrayList();
        list.add("a");

        /*
        @SuppressWarnings 어노테이션을 사용할 때는 경고를 무시해도 안전한 이유를 주석으로 남겨서
        비즈니스 로직이 변경될 때 타입 안정성을 잃는 경우를 방지하자
         */

    }
}
