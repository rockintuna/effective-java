package me.rockintuna.effectivejava.item;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

/*
ordinal 인덱싱 대신 EnumMap을 사용하라
 */
public class Item37 {
    /*
    ordinal 사용의 단점
    - 타입 안전하지 않음
    - 컴파일러는 ordinal과 배열의 인덱스의 관계를 알 수 없다.
    -> IndexOutOfBounds, NullPointer 등 런타임 에러가 발생 가능
     */

    private String name;
    private Style style;

    public String getName() {
        return name;
    }

    public Style getStyle() {
        return style;
    }

    public enum Style {
        BOLD,
        ITALIC,
        UNDERLINE;
    }

    public Item37(String name, Style style) {
        this.name = name;
        this.style = style;
    }

    public static void main(String[] args) {
        List<Item37> itemList = new ArrayList<>();
        itemList.add(new Item37("A", Style.BOLD));
        itemList.add(new Item37("B", Style.ITALIC));
        itemList.add(new Item37("C", Style.ITALIC));
        itemList.add(new Item37("D", Style.UNDERLINE));

        /*
        EnumMap
        - 열거 타입 상수를 키로 사용
        - 타입 안전
        - 비검사 형변환 없음
         */
        //생성자는 한정적 타입 토큰
        Map<Style, Set<Item37>> styleWithItemSetMap = new EnumMap<>(Style.class);
        for (Style style : Style.values()) {
            styleWithItemSetMap.put(style, new HashSet<>());
        }
        for (Item37 item37 : itemList) {
            styleWithItemSetMap.get(item37.style).add(item37);
        }

        //stream을 사용한 코드
        Map<Style, Set<Item37>> map = itemList.stream().collect(groupingBy(i -> i.getStyle(),
                () -> new EnumMap<>(Style.class), toSet()));

        /*
        ordinal 사용 X
        -> 대신 인스턴스 필드 및 EnumMap을 사용하자
         */
    }
}
