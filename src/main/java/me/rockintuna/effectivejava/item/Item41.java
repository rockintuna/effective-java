package me.rockintuna.effectivejava.item;

import java.io.Serializable;

/*
정의하려는 것이 타입이라면 마커 인터페이스를 사용하라
 */
public class Item41 implements Serializable {

    /*
    마커 인터페이스:
    아무 메서드 없이 단지 자신을 구현하는 클래스가 특정 속성을 가짐을 표시해주는 인터페이스
    ex) Serializable 는 아무 메서드가 없는 마커 인터페이스이다.
    어떤 객체가 특정 속성이나 능력을 가진 것임을 표시하고 싶을 때 사용한다.
    */
    public interface Serializable {}  // 마커 인터페이스

    /*

    vs 마커 애너테이션
    - 마커 인터페이스는 타입으로 사용할 수 있음 => 컴파일 타임 검증에 유용하게 사용
    - 적용 대상을 더 정밀하게 지정할 수 있음
    예를들어 인터페이스 MajorType을 구현한 클래스에만 마커를 적용하고 싶다면
    마커 애너테이션은 이렇다할 방법이 없지만
    마커 인터페이스를 사용할 때는 MajorType을 상속받은 마커 인터페이스를 구현하면 된다.
     */
    //제한
    public interface MajorType {
        String majorMethod();
    }

    //마커 인터페이스
    public interface Major extends MajorType {
    }

    //마킹된 클래스
    class MajorTypeConcreteClass implements Major {
        @Override
        public String majorMethod() {
            return "";
        }
    }


    /*
    이런 장점을 사용할 일이 없다면 마커 애너테이션이 나을 수 있다.

    내가 사용하는 프레임워크에서 애너테이션 시스템을 강력하게 사용할 수 있다면 마커 애너테이션,
    타입 시스템을 더 잘 사용하고, 인스턴스 타입으로 사용하고 싶다면 마커 인터페이스
     */
}
