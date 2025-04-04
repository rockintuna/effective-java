package me.rockintuna.effectivejava.item;

import java.util.List;

/*
public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라
 */
public class Item16 {

    public List<String> memberList;
    /*
    public class에서 public 필드를 사용하면
    1. 내부 표현을 변경하려면 API가 변경되어야 한다.
     : public class의 public 필드 자체가 API 이면서 내부 표현이기 때문
    2. 불변식을 보장할 수 없다
     : 클라이언트에서 언제든지 필드의 인스턴스를 변경하거나 수정할 수 있다.
    3.부수 작업을 수행할 수 없다.
     : 접근자 메서드에서는 부가 작업을 추가하여 제공할 수 있다.
     */

    public int length;
    //불변 필드 이더라도 불변식만 보장할 뿐 위 문제는 동일하다.

    private Integer width;

    public int getWidth() {
        return width;
    }
    /*
    getter setter 메서드를 사용하면 모든 문제가 해결된다.
     */

    /*
    하지만 package-private이나 private 중첩 클래스에서는 public 필드를 쓰면 오히려 좋을 때도 있다.
     */
}
