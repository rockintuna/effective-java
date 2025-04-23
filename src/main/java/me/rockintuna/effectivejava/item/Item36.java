package me.rockintuna.effectivejava.item;

import java.util.EnumSet;
import java.util.Set;

/*
비트 필드 대신 EnumSet을 사용하라
 */
public class Item36 {
    //비트 열거 패턴
    public static final int STYLE_BOLD = 1 << 0;
    public static final int STYLE_ITALIC = 1 << 1;
    public static final int STYLE_UNDERLINE = 1 << 2;

    private int styles;

    //styles = 여러 비트 값들을 or 연산으로 모은 값 : 비트 필드
    public void applyStyle(int styles) {
        this.styles = styles;
    }

    public static void main(String[] args) {
        //비트 필드
        Item36 item36 = new Item36();
        item36.applyStyle(STYLE_BOLD | STYLE_ITALIC);

        //EnumSet
        Set<Style> styleSet = EnumSet.of(Style.UNDERLINE, Style.ITALIC);
        item36.applyStyles(styleSet);
    }

    /*
    비트 필드는 비트 연산을 통한 교집합/합집합 같은 집한 연산이 가능하지만
    아래와 같은 단점이 있다.
    - 정수 열거 상수의 단점
    - 값 자체를 통해서는 해석하기 어려움
    - 비트 필드의 요소들을 순회하기 어려움
    - 최대 몇 비트가 필요한지 예측하여 적절한 타입을 선택해야 함

    EnumSet 클래스는 비트 필드의 대안이 된다.
    - 타입 안전
    - Set 인터페이스 구현체
    - 내부적으로는 비트 벡터로 구현되어 성능이 좋음
    - 비트/집합 연산을 직접하는 것 보다 쉬운 API를 제공
     */

    public enum Style {
        BOLD,
        ITALIC,
        UNDERLINE;
    }

    private Set<Style> styleSet;

    public void applyStyles(Set<Style> styleSet) {
        this.styleSet = styleSet;
    }
}
