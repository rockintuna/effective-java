package me.rockintuna.effectivejava.item;

/*
태그 달린 클래스보다는 클래스 계층구조를 활용하자
 */
public class Item23 {

    //의미와 관계없는 필드
    private final String name;

    //특정 의미에서만 사용되는 필드
    private final Integer dimensionLines;
    private final Integer texture;

    //태그
    enum Type {
        DRAWING_2D, DRAWING_3D
    }

    //현재 인스턴스의 의미를 태그로 선택
    private Type type;

    //태그와 상관 없이 동일한 메서드
    public String getName() {
        return this.name;
    }

    //태그에 따라 분기되는 메서드
    public void action() {
        switch (this.type) {
            case DRAWING_2D -> System.out.println("DRAWING_2D");
            case DRAWING_3D -> System.out.println("DRAWING_3D");
        }
    }
    /*
    이런 클래스 설계의 단점
    - 쓸데없는 코드 [enum, 태그 필드, switch-case, 등]
    - (분기로 나뉘게 될)여러 구현이 한 클래스에 있어서 가독성이 안좋음
    - 의미 마다 특별하게 쓰이는 필드더라도 모두 한 클래스에 있어야 한다, final이라면 굳이 입력까지(null이라도) 해야한다.
    - 다른 의미의 태그가 추가되면 코드가 수정된다.
    - 실수로 case 구현을 안하더라도 컴파일 오류는 발생하지 않고 비즈니스 로직 오류나 런타임 에러가 발생한다.
    - 인스턴스의 의미를 알려면 꼭 태그를 참조해야 한다.
     */
    public Item23(String name, Integer dimensionLines, Integer texture) {
        this.name = name;
        this.dimensionLines = dimensionLines;
        this.texture = texture;
    }
}

// ==>> 클래스 계층구조를 활용한 서브타이핑으로 리팩터링 강추
abstract class Item23Root {
    private final String name;
    public String getName() {
        return name;
    }
    abstract void action();

    public Item23Root(String name) {
        this.name = name;
    }
}

class Item23Drawing2D extends Item23Root {
    private final Integer dimensionLines;
    @Override
    void action() {
        System.out.println("DRAWING_2D");
    }

    public Item23Drawing2D(String name, Integer dimensionLines) {
        super(name);
        this.dimensionLines = dimensionLines;
    }
}

class Item23Drawing3D extends Item23Root {
    private final Integer texture;
    @Override
    void action() {
        System.out.println("DRAWING_3D");
    }

    public Item23Drawing3D(String name, Integer texture) {
        super(name);
        this.texture = texture;
    }
}


