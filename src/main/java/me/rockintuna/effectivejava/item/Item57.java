package me.rockintuna.effectivejava.item;

import java.util.Iterator;
import java.util.List;

/*
지역변수의 범위를 최소화하라
 */
public class Item57 {

    /*
    지역변수 범위가 최소화되었을 때 장점
    - 가독성
    - 유지보수성
    - 오류 방지

    지역변수 범위를 줄이는 방법
    - 가장 처음 쓰일 때 선언하기
    - 선언과 동시에 초기화하기 (try-catch 예외)
    - 메서드를 작게 유지하고 한 가지 기능에 집중하기

    컬렉션을 순회할 때 while보다 for문을 권장
    - for문은 반복 변수의 범위가 반복문 몸체와 괄호 안으로 제한된다.
    - 따라서 while보다 for문을 쓰는 편이 낫다.
    - 똑같은 이름의 변수를 여러 반복문에서 써도 서로 아무런 영향을 주지 않는다.
    - while보다 짦아서 가독성이 좋다.
    - 만약 반복이 종료된 이후에도 변수를 써야 하는게 아니라면 for문을 사용하자.
     */

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        for (Integer i : list) {
            System.out.println(i);
        }
        //compile error
//        i = 3;

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println(next);
        }
        iterator = list.iterator();
    }
}
