package me.rockintuna.effectivejava.item;

import org.apache.commons.collections4.collection.SynchronizedCollection;

import java.util.ArrayList;
import java.util.Collection;

/*
인터페이스는 구현하는 쪽을 생각해 설계하자
 */
public class Item21 implements Item21Interface{

    public static void main(String[] args) {
        /*
        모든 상황에 대해 불변식을 지키는 디폴트 메서드를 작성하기는 어렵다.
         */
        Collection<String> memberList = SynchronizedCollection.synchronizedCollection(new ArrayList<>());
        /*
        예를들어 Collection 인터페이스의 removeIf 디폴트 메서드를
        Collection 인터페이스를 구현하는 apache commons 의 SynchronizedCollection에서 사용하면
        SynchronizedCollection 가 가지는 동기화 기능을 적용받지 못한다.
        즉, thread-safe 하지 못하게 동작하거나 오류가 발생한다.
         */
        memberList.removeIf(s -> s.startsWith("#"));

        /*
        최신 버전의 SynchronizedCollection 은 이를 보완하기 위해 removeIf를 재정의했다.
        public boolean removeIf(Predicate<? super E> filter) {
            synchronized(this.lock) {
                return this.decorated().removeIf(filter);
            }
        }
         */
    }

    /*
    기존에 사용하던 인터페이스에 디폴트 메서드를 추가하는 것은 되도록 피해야 한다.
    이를 구현하는 구체 클래스의 기존 구현과 충돌하여 오류가 발생할 수 있다.

    Item21Interface에 새로 isEmpty(String item) 디폴트 메서드가 추가되면서
    아래 메서드는 컴파일 오류가 발생한다.
    새로운 인터페이스 설계 단계에서는 잘 사용하면 좋다.
     */
    public String isEmpty(String item) {
        return item.isEmpty() ? "true" : "false";
    }
}

/*
인터페이스를 설계할 때는
구현 테스트, 인터페이스 인스턴스 활용 테스트 등
주의를 기울여 용도에 맞는지 확인해야 한다.
 */
interface Item21Interface {

    default boolean isEmpty(String item) {
        return item.isEmpty();
    }
}
