package me.rockintuna.effectivejava.item;

import java.math.BigInteger;
import java.util.Objects;

/*
매개변수가 유효한지 검사하라
 */
public class Item49 {

    private String name;

    /*
    오류는 빨리 발견되면 좋다.

    메서드의 매개변수에 특별한 제약이 있는 경우,
    매개변수 값이 잘못됐을 때 본문이 시작되기 전에 예외 처리를 하고 (생성자에서 특히),
    public protected 메서드는 이 예외를 문서화 해야 한다.
     */

    /**
     *
     * @param m
     * @return 현재 값 mod m
     * @throws ArithmeticException m이 0보다 작거나 같으면 발생
     */
    public BigInteger mod(BigInteger m) {
        if (m.signum() <= 0) {
            throw new ArithmeticException("Modulus must be positive");
        }
        //todo
        return m;
    }

    /*
    null 검사를 할때는 Objects.requireNonNull 메서드를 사용하면 편리하다.
     */
    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "name is null.");
    }

    public static void main(String[] args) {
        Item49 item49 = new Item49();
        item49.setName(null);
    }

    /*
    public이 아닌 메서드는 단언문(assert)을 사용해 매개변수 유효성을 검증할 수 있다.
    단언문은 실패하면 AssertionError 를 던지며,
    런타임에는 아무런 효과나 성능 저하가 없다. (java 명령줄에 -ea 또는 --enableassertions 플래그를 설정하면 영향을 준다.)

    검사를 안해도 되는 경우
    - 검사 비용이 지나치게 높거나 실용적이지 않을 때
    - 처리 과정에서 암묵적으로 검사가 수행될 때
     */
}
