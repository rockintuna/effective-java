package me.rockintuna.effectivejava.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
null이 아닌, 빈 컬렉션이나 배열을 반환하라
 */
public class Item54 {

    private List<String> names;
    /*
    null을 반환하면
    null을 반환하는 쪽, 받는 쪽 모두에서 null에 대해 특별한 취급을 하는
    추가적인 코드를 작성해야 한다.
     */
    public List<String> names() {
        if (names.isEmpty()) {
            return null;
        } else {
            return names;
        }
    }

    public static void main(String[] args) {
        Item54 item = new Item54();
        if ( item.names() != null ) {
            //todo
        }
    }

    /*
    빈 컬렉션 / 배열을 반환해도 괜찮을 이유
    - 성능적으로 큰 차이 없음
    - 매번 새로 할당하지 않고도 반환할 수 있음 (빈 불변 컬렉션)
    Collections.emptyList()
    Collections.emptySet()
    Collections.emptyMap()
     */
    public List<String> names2() {
        if ( names.isEmpty() ) {
            //성능 최적화에 해당하니 꼭 필요한 경우만 사용하자
            return Collections.emptyList();
        }
        return new ArrayList<>(names);
    }

    //배열 버전
    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    public String[] names3() {
        //리스트가 toArray에 매개변수로 입력되는 배열보다 크면
        //리스트 크기의 배열을 반환
        //그렇지 않으면 매개변수로 입력된 배열의 크기로 반환
        return names.toArray(new String[0]);
    }

    public String[] names4() {
        //또는 미리 할당한 배열 리턴
        return names.toArray(EMPTY_STRING_ARRAY);
    }
}
