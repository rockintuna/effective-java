package me.rockintuna.effectivejava.item;

import java.math.BigInteger;

/*
변경 가능성을 최소화하라
 */
public final class Item17 {

    /*
     불변 객체 : 생성된 시점부터 파괴될 때 까지 상태가 변경되지 않는 객체

    클래스를 불변으로 만들기
    1. setter가 없어야함
    2. 클래스를 확장할 수 없어야함 (대표적으로 final 클래스)
    3. 모든 필드를 private final로 선언
    4. 가변 객체를 참조하는 필드가 있다면 해당 필드를 사용하는 생성자, getter, readObject 메서드에서 방어적 복사를 한다.

    불변으로 만들 수 없다면 변경할 수 있는 부분을 최소화하자
    - 별다른 합당한 이유가 없다면 모든 필드는 private final
    - 생성자는 초기화 작업, 불변식 설정이 완료된 객체를 생성
     */

    private final int x;
    private final int y;

    Item17(int x, int y) {
        this.x = 0;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Item17 plus(int x, int y) {
        return new Item17(this.x + x, this.y + y);
    }
    //함수형 프로그래밍
    //피연산자에 함수를 적용하여 결과를 반환하지만 피연산자는 그대로인 패턴
    //함수형 프로그래밍을 사용하여 개발하면 불변이 되는 영역의 비율이 높아진다.

    /*
    불변 객체의 장점
    설계, 구현, 사용이 쉽고 오류 발생을 줄일 수 있고 안전함
     - 근본적으로 thread-safe, 추가적인 동기화 작업 불필요
     - 정적 팩터리 메서드를 사용하여 동일한 객체의 중복 생성을 방지할 수 있다.
      => 모든 생성자를 private으로 하고 정적 팩터리 메서드를 제공하면 클래스를 확장할 수 없게 만들수도 있음
     - 방어적 복사가 필요없다.
     - 자유롭게 공유할수 있고 불변 객체끼리는 내부 데이터를 공유할 수 있다. (공유할 내부 데이터가 가변이더라도 수정할 방법이 없으므로 상관 없음)
     - Map,Set 등의 객체에서 불변 객체를 구성요소로 사용하면 불변식을 유지하기 쉽다.
     - 실패 원자성(예외가 발생해도 객체 상태는 유효해야 함)을 제공한다.

    불변 객체의 단점
     - 다른 값의 객체가 필요하면 반드시 새로운 객체로 만들어야 함 (내부 값이 여러 종류일수록 비효율적)
        => 가변 동반 클래스 (불변 객체 String 을 생성하는 다단계 연산을 제공하는 StringBuilder)
     */

    private static final Item17 start = new Item17(0, 0);

    public Item17 getStart() {
        return start;
    }
    //쓰임이 동일한 불변 객체의 중복 생성을 방지한다. => 메모리 절약

}
