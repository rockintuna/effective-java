package me.rockintuna.effectivejava.item;

/*
다른 타입이 적절하다면 문자열 사용을 피하라
 */
public class Item62 {

    /*
    문자열을 쓰면 안되는 사례
     */

    /*
    1. 문자열은 다른 값 타입을 대신하기에 적합하지 않다.
    입력 받을 데이터가 수치형이라면 -> int, float, BigInteger
    "예 / 아니오" 같은 질문의 답이라면 -> enum, boolean

    2. 문자열은 열거 타입을 대신하기에 적합하지 않다.
    상수를 열거할 때는 enum이 String보다 월등히 낫다.

    3. 문자열은 혼합 타입을 대신하기에 적합하지 않다.
     */
    private String key;
    String compoundKey = this.getClass().getName() + "#" + key;
    /*
     오류 발생 가능성이 높다.
     각 요소를 개별로 접근하기 어렵고 느리다.
     적절한 공통 메서드 (equals, toString, compareTo)를 구현할 수 없다.
     ==>>> 전용 클래스(보통 private 정적 멤버 클래스)를 만들자

    4. 문자열은 권한을 표현하기에 적합하지 않다.
    문자열을 key로 사용한 권한 사용 시나리오는 적합하지 않다.
    서로 동일한 key를 사용하여 오류가 날 수 있고 보안에도 취약하다.

    문자열은 텍스트를 표현하고자 할 때만 사용하자!
     */

}
