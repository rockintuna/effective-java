package me.rockintuna.effectivejava.item;

/*
이왕이면 제네릭 타입으로 만들자
 */
public class Item29 {
    /*
    기존 타입 중 제네릭이었어야 하는 게 있다면 제네릭 타입으로 변경하자.
    제네릭 타입은 하위 버전의 Java에서 호환이 가능하기 때문에
    기존 클래스를 제네릭 타입으로 바꾸더라도
    원래 클래스를 사용하던 클라이언트에는 영향이 없다.
    동시에 새로운 사용자는 훨씬 편하고 타입 안전하게 사용할 수 있다.
     */
}

//제네릭 배열 생성을 우회하는 방법 1
class Item29GenericArray1<E> {
    private final E[] array;

    //제네릭 타입 배열로 형변환
    //요소를 입력할 때 E 타입만 입력되는 것으로 제한 할 수 있다면
    //@SuppressWarnings("unchecked")으로 경고 제거
    @SuppressWarnings("unchecked")
    public Item29GenericArray1() {
        this.array = (E[]) new Object[10];
    }

    public void add(E e) {
        //add 구현
    }

    public E get(int index) {
        return array[index];
    }
}

//제네릭 배열 생성을 우회하는 방법 2
class Item29GenericArray2<E> {
    private final Object[] array;

    public Item29GenericArray2() {
        this.array = new Object[10];
    }

    public void add(E e) {
        //add 구현
    }

    //뽑은 요소를 형변환
    //마찬가지로 입력이 E로 제한된다고 확정할 수 있다면
    //요소 뽑기에서 @SuppressWarnings("unchecked")으로 경고 제거
    public E get(int index) {
        //get 구현
        @SuppressWarnings("unchecked")
        E e = (E) this.array[index];
        return e;
    }
}

/*
1번 방법을 주로 쓰지만 (가독성, 생성자에서만 형변환), 힙 오염이 있음
List를 사용할 수 있다면 좋지만 사용하지 못하는 경우, 또는 성능이 중요한 경우 위와 같이 구현!
 */