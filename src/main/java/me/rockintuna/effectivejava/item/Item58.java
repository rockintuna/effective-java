package me.rockintuna.effectivejava.item;

import java.util.Iterator;
import java.util.List;

/*
전통적인 for 문보다는 for-each 문을 사용하라
 */
public class Item58 {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //전통적인 배열 순회 for문
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
        }
        //전통적인 collection 순회 for문
        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
            Integer next = iterator.next();
            System.out.println(next);
        }

        /*
        전통적인 for 문의 단점
         - 반복자와 인덱스 변수는 코드를 지저분하게 한다. (어차피 필요한건 담고있는 원소들임)
         - 요소가 많아서 코드 실수로 오류가 발생하기 쉽다.
         - 컬렉션이냐 배열이냐에 따라 형태가 많이 달라진다.

        for-each 문 (향상된 for 문)의 장점
        - 위 문제가 모두 해결됨
        - 중첩 for 문 시 실수가 줄어듦
        - 더 간단 명료하며 성능은 그대로
         */
        for (Integer i : list) {
            System.out.println(i);
        }
        for (String arg : args) {
            System.out.println(arg);
        }

        /*
        for-each 문을 사용할 수 없는 상황
        - 파괴적인 필터링 : 순회하며 remove 할 때 => removeIf 사용
        - 변형 : 순회하며 원소의 값 일부 혹은 전체를 교체해야 할 때 (인덱스 필요)
        - 병렬 반복 : 여러 컬렉션을 병렬로 순회할 때 (인덱스 필요)

        이 상황이 아니면 for-each문을 사용하자.
         */
    }

}
