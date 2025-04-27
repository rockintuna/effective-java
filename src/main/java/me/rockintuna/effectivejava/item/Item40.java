package me.rockintuna.effectivejava.item;

import java.util.Objects;

/*
@Override 애너테이션을 일관되게 사용하라
 */
public class Item40 {

    private int x;
    private int y;

    public boolean equals(Item40 o) {
        if (o == null || getClass() != o.getClass()) return false;
        return x == o.x && y == o.y;
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }

    /*
    위 코드는 얼핏 제대로 동작할 것 같지만, 메서드 equals는 의도한 대로 동작하지 않을 것이다.
    위 equals 메서드는 Object equals 메서드를 제정의하지 않는다.
    Object equals 메서드를 재정의하려면 매개변수 타입을 Object로 해야 한다.
    하지만 컴파일러는 위와 같은 상황에서 이 내용에 대한 경고를 알려주지 못한다.

    우리는 컴파일러에게 이 메서드가 Object equals를 재정의한다는 의도를 명시하여,
    컴파일러가 제대로 검사를 할 수 있도록 할 수 있다.
    예를 들어 아래 코드는 컴파일 단계에서 에러가 발생한다.
    method does not override or implement a method from a supertype
     */
//    @Override
//    public boolean equals2(Item40 o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        return x == o.x && y == o.y;
//    }

    //올바르게 수정
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Item40 item40)) return false;
        return x == item40.x && y == item40.y;
    }

    public static void main(String[] args) {

    }

    /*
    즉, @Override 애너테이션을 일관되게 사용하라는 말은
    상위 클래스 또는 인터페이스의 메서드를 재정의할 때는 예외없이 @Override 애너테이션을 달라는 말이다.
    (추상 메서드를 재정의할 때는 달지 않더라도 의미 없지만 그래도 일괄적으로 붙이는게 좋다면 상관 없다.)
     */
}
