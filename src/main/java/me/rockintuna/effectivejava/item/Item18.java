package me.rockintuna.effectivejava.item;

/*
상속보다는 컴포지션을 사용하라
 */
public class Item18 extends Item18SuperClass {

    /*
    내가 제어할 수 없는 다른 패키지의 상위 클래스를 상속받았을 때 문제다.
    이 상위 클래스에서 상속받아 사용할 수 있는 내부 구현이 달라지는 경우,
    해당 내부 구현을 사용하는 메서드에서 오류가 발생하기 쉽다.
    상위 클래스 변화에 따라 하위 클래스 모두 변경해야 할 수 있다.
     */
    @Override
    public String getName() {
        return super.getName();
    }

    /*
    상위 클래스를 완벽히 이해하지 않는이상 재정의는 위험성이 있다.
    재정의 메서드는 의도치 않게 호출될 수 있으며 (내부 구현, 자기 사용),
    새로운 메서드가 생길 때 마다 재정의가 필요한 경우도 생긴다.
     */
    /*
    재정의를 하지 않고 다른 메서드 이름을 사용한다고 하면 다른 문제가 생긴다.
    이 메서드는 상위 클래스에서 새로운 메서드가 생길 때 시그니처가 동일한 경우
    오류가 발생하거나(리턴 타입이 다른경우) 재정의 되어버린다(리턴 타입이 같은 경우)
     */
}

//forwarding class
class Item18ForwardingClass {
    //컴포지션
    private Item18SuperClass composition;

    public Item18ForwardingClass(Item18SuperClass composition) {
        this.composition = composition;
    }

    //forwarding method
    String getName() {
        //forwarding
        return composition.getName();
    }

    /*
    컴포지션의 경우 재정의가 아니므로 자기 사용에 의해 의도치 않은 호출이 없다.
    컴포지션 클래스에 새로운 메서드가 생기는 것이 전달 클래스에는 더이상 아무런 영향을 끼치지 않는다.

    위와 같이 내부 객체의 메서드들을 대응해 응답하는 방식을 "전달" 이라고 한다.
    */
}

/*
재사용 가능한 전달 클래스를 상속받아 추가적인 기능을 부여하는 클래스를 래퍼 클래스라고 한다.
 */
class Item18RapperClass extends Item18ForwardingClass {

    public Item18RapperClass(Item18SuperClass composition) {
        super(composition);
    }

    public String subStringName(int idx) {
        return super.getName().substring(idx);
    }
}

/*
    상속의 단점
     - 상위 클래스의 내부 구현 변경에 취약함
     - 자기 사용에 의한 의도치 않은 동작
     - 상위 클래스 내부 구현이 불필요하게 노출되어야 함
     - 클라이언트가 노출된 내부에 직접 접근할 수 있음 (Override는 접근 제한자를 더 낮게 설정할 수 없음)

    상속은 A is a B, B가 A의 "진짜" 하위 타입인 경우에만 사용하자.
    다만 그런 경우가 별로 없기도 하고
    상속 자체의 단점
*/

class Item18SuperClass {

    private String name;

    protected String getName() {
        return name;
    }
}