package me.rockintuna.effectivejava.item;

/*
멤버 클래스는 되도록 static 으로 만들자
 */
public class Item24 {

    private String name;
    /*
    중첩 클래스 : 다른 클래스 안에서 정의된 클래스
    중첨 클래스는 자신을 감싸는 바깥 클래스에서만 내부적으로 사용되어야 한다.
    중첨 클래스 종류
     - 정적 멤버 클래스
     - (비정적) 멤버 클래스
     - 익명 클래스
     - 지역 클래스
     정적 멤버 클래스를 제외한 3가지는 모두 내부 클래스(inner class) 이다.
     */

    private String getName() {
        return name;
    }

    /*
         정적 멤버 클래스 : 바깥 클래스의 private 멤버에 접근할 수 있다는 것을 제외하면 일반 클래스와 동일하다
         주로 바깥 클래스와 함께 쓰일 때만 유용한 public 도우미 클래스로 쓰인다.
         */
    public static class Item24StaticMemberClass {
        private String outerClassName;

        public String getName() {
            Item24 item24 = new Item24();
            //바깥 클래스의 private 멤버에 접근할 수 있다
            outerClassName = item24.name;
            return item24.getName();
        }
    }

    /*
     비정적 멤버 클래스 :
     비정적 멤버 클래스의 인스턴스는 바깥 클래스의 인스턴스와 암묵적으로 연결된다.
     바깥 인스턴스 없이는 독립적으로 비정적 멤버 클래스 인스턴스를 생성할 수 없다.
     어댑터를 정의할 때 자주 쓰인다.
     ex) ArrayList의 iterator()
     public Iterator<E> iterator() {
        return new Itr();
    }
    Itr는 ArrayList의 비정적 멤버 클래스다.
     */
    public class Item24NonStaticMemberClass {
        private String outerClassName;

        public String getName() {
            //정규화된 this를 통해 바깥 클래스 인스턴스의 참조를 가져올 수 있다.
            outerClassName = Item24.this.name;
            return Item24.this.getName();
        }

    }

    //비정적 멤버 클래스의 인스턴스는 보통 바깥 클래스 인스턴스 메서드 안에서 생성된다.
    public void createItem24NonStaticMemberClass() {
        Item24NonStaticMemberClass item24NonStaticMemberClass = new Item24NonStaticMemberClass();
    }

    /*
    멤버 클래스에서 바깥 인스턴스에 접근할 일이 없다면 무조건 static을 붙여서 정적 멤버 클래스로 만들자
    - 암묵적 외부 참조 : 참조 저장 공간 등 불필요한 시간 공간 소비, 눈에 보이지 않는 참조 때문에 발생하는 오류 디버깅 어려움
    - 바깥 클래스 인스턴스를 가비지 컬렉터에서 수거하지 못하는 메모리 누수 가능성

    익명 클래스는 즉석에서 작은 함수 객체나 처리 객체를 만들때 (람다로 대체) 또는 정적 팩터리 메서드를 구현할 때 사용
     */

    /*
    중첩 클래스가 한 메서드 안에서만 쓰이면서 &&
    그 인스턴스를 생성하는 지점이 단 한 곳이고 &&
    해당 타입으로 쓰기에 적합한 클래스나 인터페이스가 이미 있는 경우에만
    익명 클래스로 생성
    그렇지 않다면 지역 클래스 사용

    지역 클래스
     */
    private String getLocalClass() {
        class LocalClass {
            private String name;

            public String getName() {
                return Item24.this.name;
            }
        }
        return new LocalClass().getName();
    }
}
