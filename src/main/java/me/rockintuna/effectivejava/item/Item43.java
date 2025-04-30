package me.rockintuna.effectivejava.item;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
람다보다는 메서드 참조를 사용하라
 */
public class Item43 {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("ab", "b", "ce", "dfe", "easd");

        //람다
        Collections.sort(words, (o1, o2) -> Integer.compare(o1.length(), o2.length()));

        //메서드 참조
        Collections.sort(words, Comparator.comparingInt(String::length));

        //람다
        Collections.sort(words,
                (o1, o2) -> {
                    //first order by length, and order by first character
                    int compare = Integer.compare(o1.length(), o2.length());
                    if (compare == 0) {
                        return Character.compare(o1.charAt(0), o2.charAt(0));
                    } else {
                        return compare;
                    }
        });

        //람다로 작성되는 코드를 메서드로 만들고 람다 대신 그 메서드 참조를 사용해보자
        //메서드 참조
        Collections.sort(words, Item43::compareByLengthAndFirstCharacter);
    }

    private static int compareByLengthAndFirstCharacter(String o1, String o2) {
        //first order by length, and order by first character
        int compare = Integer.compare(o1.length(), o2.length());
        if (compare == 0) {
            return Character.compare(o1.charAt(0), o2.charAt(0));
        } else {
            return compare;
        }
    }

    /*
        메서드 참조는 람다보다 더 간결하다.
        특히 매개변수가 많을 때 더욱 효과가 크다.

        항상 그런것은 아니다.
        - 메서드와 람다가 같은 클래스에 있는 경우
        - 정적 팩터리 메서드
        의 경우 람다가 더 짧고 명확하다. o
    */

    /*
    메서드 참조 유형
     */
    private void methodReferenceType() {
        List<String> words = Arrays.asList("ab", "b", "ce", "dfe", "easd");

        // 정적 메서드 참조
        List<Integer> list1 = words.stream().map(Integer::parseInt).toList();

        //한정적 인스턴스 메서드 참조
        List<Boolean> list2 = words.stream().map(new String("abcdefg")::contains).toList();

        //비한정적 인스턴스 메서드 참조
        List<String> list3 = words.stream().map(String::toLowerCase).toList();
        //정적 메서드 참조와 비슷해보이지만
        //정적 메서드 참조는 인스턴스가 메서드 인수로 사용되고
        //비한정적 인스턴스 메서드 참조는 주어진 인스턴스의 메서드를 사용한다.

        //클래스 생성자
        class Tag {
            private String name;

            public Tag(String name) {
                this.name = name;
            }
        }

        List<Tag> list4 = words.stream().map(Tag::new).toList();

        //배열 생성자
        List<char[]> list5 = words.stream().map(str -> str.length())
                .map(char[]::new).toList();
        //생성자 참조는 팩터리 객체로 사용된다.
    }
}
