package me.rockintuna.effectivejava.item;

/*
메서드 시그니처를 신중히 설계하라
 */
public class Item51 {

    /*
    메서드 설계 요령

    1. 메서드 이름을 신중히
    - 표준 명명 규칙 따르기
    - 동일한 패키지에 속한 다른 메서드와 일관되게
    - 개발자들에게 일반적으로 받아들여지는 이름 사용
    - 긴 이름 피하기

    2. 편의 메서드를 너무 많이 만들지 말자
    클래스 또는 인터페이스에 메서드 수가 너무 많아지면 이해, 사용, 문서화, 테스트, 유지보수가 어려워진다.
    따라서 편의를 위한 메서드는 아주 자주 쓰이는게 아니라면 만들지 말자

    3. 매개변수 목록은 짧게
    - 4개 이하
    - 동일한 타입의 매개변수 여러개는 사용하기 어렵고 오류 발생 가능성 있음
    => 여러 메서드로 쪼개기
    => 도우미 클래스 (일반적으로 정적 멤버 클래스)
    => 빌더 패턴 응용

    4. 매개변수의 타입으로 클래스보다는 인터페이스
    인터페이스를 매개변수로 사용하면 메서드 사용이 유연해진다.

    5. boolean 보다는 원소 2개짜리 enum 이 낫다.
    */
    private TemperatureScale temperatureScale;
    private boolean isFahrenheit;

    public enum TemperatureScale { FAHRENHEIT, CELSIUS }

    public static Item51 of(boolean isFahrenheit) {
        Item51 item51 = new Item51();
        item51.isFahrenheit = isFahrenheit;
        return item51;
    }

    public static Item51 of(TemperatureScale temperatureScale) {
        Item51 item51 = new Item51();
        item51.temperatureScale = temperatureScale;
        return item51;
    }

    /*
    위 boolean 메서드 보다는 아래 enum 메서드가 더 직관적이다.
    선택지를 추가하기도 쉽다.
    enum 의 장점을 살릴 수도 있다. (상수별 메서드 구현, 전략 열거 타입 패턴 등)
     */
}
