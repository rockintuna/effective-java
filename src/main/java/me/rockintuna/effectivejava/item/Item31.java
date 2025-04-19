package me.rockintuna.effectivejava.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
한정적 와일드카드를 사용해 API 유연성을 높이라
 */
public class Item31<E> {
    List<E> list = new ArrayList<>();

    public void addAll(List<E> array) {
        this.list.addAll(array);
    }

    public static void main(String[] args) {
        Item31<Number> item = new Item31<>();
        List<Integer> integerList = new ArrayList<>();
        /*매개변수화 타입은 불공변이기 때문에
        List<Number> 타입의 인자값으로 List<Integer> 타입을 입력할 수 없음.
        아래 코드는 오류 발생 (incompatible types)
        item.addAll(integerList);
        List<Number> 타입의 인자값으로 List<Object> 타입을 입력할 수 없음.
        item.addAllTo(new ArrayList<Object>());
        */

        /*
        이런 상황을 대처하기 위해
        "한정적 와일드카드 타입" 이라는 특별한 매개변수화 타입 지원
        ? extends E 는 E의 하위 타입,
        List<? extends E>는 E의 하위 타입의 리스트,
        ? super E 는 E의 상위 타입,
        List<? super E>는 E의 상위 타입의 리스트,
         */
        item.addAllExtendsE(integerList);
        item.addAllToSuperE(new ArrayList<Object>());
    }

    public void addAllExtendsE(List<? extends E> array) {
        this.list.addAll(array);
    }

    public void addAllTo(List<E> list) {
        list.addAll(this.list);
    }

    public void addAllToSuperE(List<? super E> list) {
        list.addAll(this.list);
    }

    /*
    유연성을 극대화하려면 메서드 또는 생성자의 입력 매개변수에 와일드카드 타입을 사용하라.
    PECS : producer-extends, consumer-super
    입력 매개변수가 원소의 생산자일 때 : extends
    입력 매개변수가 원소의 소비자일 때 : super
    입력 매개변수가 원소의 생산자이면서 소비자일 때는 와일드카드 타입을 쓰지 말아야 한다.

    타입 매개변수에도 위의 공식을 적용할 수 있다.
    Comparable는 E의 소비자 이기 때문에 super를 사용한 와일드 카드 타입으로 바꾼다.
    E 또는 E의 상위 클래스에서 Comparable를 확장한 것임을 확정한다.
     */
    public static <E extends Comparable<? super E>> Optional<E> min(List<E> list) {
        //todo
        return list.stream().sorted().findFirst();
    }

    /*
    와일드카드와 타입 매개변수 둘 중 하나를 선택하여 제네릭 메서드를 구현할 수 있다.
     */
    //1) 비한정적 타입 매개변수
    public static <E> void swap1(List<E> array, int i, int j) {}
    //2) 비한정적 와일드카드
    public static void swap2(List<?> array, int i, int j) {}

    /*
    적절한 선택 기준 및 사용법
    - 메서드 선언에 타입 매개변수가 한 번만 나오면 와일드카드로 대체
    - List<?>를 사용하여 실제 타입을 알 수 없기 때문에 문제가 발생한다면, List<E>를 사용하는 private 도우미 제네릭 메서드 추가
     */
}