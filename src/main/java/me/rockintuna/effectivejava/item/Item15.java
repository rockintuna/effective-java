package me.rockintuna.effectivejava.item;

import java.util.List;

/*
클래스와 멤버의 접근 권한을 최소화 하라
*/
public class Item15 {

    /*
    정보 은닉, 캡슐화의 장점
    컴포넌트들을 서로 독립시킨다. => 개발,테스트,최적화,적용,분석,수정을 개별적으로 할 수 있게 해준다.
    - 개발 속도 (병렬 개발)
    - 관리 비용 (유지보수 쉬움)
    - 유닛 테스트를 통한 컴포넌트 성능 최적화
    - 재사용성
    - 거대한 시스템 개발을 쉽게 해줌

    Java의 정보 은닉 장치 [접근 제한자]를 통해 모든 클래스와 멤버의 접근성을 가능한 좁히자.

    public 접근 제한자 =>
    외부 접근을 허용한다는 것 (API) =>
    수정, 교체, 제거에 제한이 생김
    */
    public int id;
    // 위 멤버 변수는 외부에서 접근할 수 있으므로 변수 이름 또는 타입을 변경하면
    // 이 변수를 사용하는 클라이언트에 문제를 발생시킬 수 있다.

    public List<String> getMemberList() {
        // 구현
        return null;
    }
    // 이 메서드도 마찬가지로 메서드 응답 타입이나 이름 또는 구현이 바뀐다면
    // 클라이언트에 문제를 발생시킬 수 있다.

    private static interface Item15Interface {
        void method();
    }
    private static class Item15TopLevel {
        void method() {
            //
        }
    }
    //하나의 클래스에서만 사용하는 인터페이스나 탑레벨 클래스는
    //클래스 내부에서 private static으로 중첩하여 생성하자
    //이렇게 하면 method()의 접근제한자가 package-private 이더라도 동일 패키지의 다른 클래스에서 사용할 수 없다.

    interface Item15Interface2 {
        void method();
        //public 일 필요가 없다면 package-private을 사용하자
        //접근 제한이 패키지 외부인지 내부인지에 따라서
        //public은 완전하게 패키지의 API이며
        //package-private은 내부 구현이다.
    }

    private String name;
    //공개 API를 제외한 모든 멤버는 private으로 만들자.
    //그 후 필요한 경우 package-private으로 풀어주자.
    // ::: Serializable을 구현한 클래스는 private 필드들도 API가 될 수 있다.

    String title;
    //package-private은 테스트 코드에서 활용할 수 있다.

    public String content;
    //public 클래스에는 public 필드를 사용하지 말자
    //public 가변 필드는 불변식을 보장할 수 없으며 일반적으로 thread-safe 하지 않다.

    //예외로,
    public static final String DEFAULT_NAME = "jilee";
    //클래스가 표현하는 추상개념을 완성하는데 꼭 필요한 구성요소인 상수
    //꼭 기본 타입 또는 불변 객체를 사용해야 한다
}
