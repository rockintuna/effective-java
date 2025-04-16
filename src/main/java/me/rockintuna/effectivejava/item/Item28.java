package me.rockintuna.effectivejava.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/*
배열보다는 리스트를 사용하자
 */
public class Item28 {

    public static void main(String[] args) {
        /*
        배열은 공변 리스트는 불공변
         */
        Object[] array = new String[]{"a","b","c"};
        //리스트는 불공변이 때문에 아래 코드는 컴파일 에러 발생
//        List<Object> list = new ArrayList<String>();

        /*
        언제나 런타임 에러보다는 컴파일에러가 낫다는 측면에서
        위처럼 List는 컴파일에서 에러가 발생하지만
        배열은 아래 코드에서 런타임에 에러(ArrayStoreException)가 발생한다.
         */
//        array[0] = 30;

        /*
        배열은 실체화되지만
        제네릭은 런타임에서 타입 정보가 소거된다.
         */
        //제네릭은 컴파일 타임에서 타임 안정성을 지키지만 런타임에서 타입 정보가 소거되어 안전하지 않음
        // (List<String> 이든 List<Integer>든 런타임에서는 둘다 List
        // 제네릭 배열이나 Raw type을 사용하는 경우 문제 발생)
        //아래 코드 컴파일 에러
//        List<Object> list = new ArrayList<String>();
        //배열은 컴파일 타임에서 타입 안전하지 않음
        Object[] objects = new String[1]; // 배열은 공변(covariant) 허용
        objects[0] = 123; // 💣 컴파일 통과! 하지만 런타임에 예외 발생

        /*
        쉽게 말하면 제네릭은 컴파일 타임때 배열보다 더 타입 체크를 엄격하게 한다.
        하지만 런타임에서 타입 정보 소거 때문에 문제가 발생할 수 있다.
         */

        /*
        제네릭 배열은 불가능하다.
        아래 코드는 컴파일 에러가 발생한다.
         */
//        new ArrayList<String>[];

        /*
        배열로 형변환할 때 제네릭 배열 생성 오류나 비검사 형변환 경고가 뜨는 경우
        보통 배열 대신 List를 사용하면 해결된다.
         */

        Collection<String> collection = new ArrayList<>();
        collection.add("a");
        //아래 코드는 런타임 오류(ClassCastException) 발생
        //Object[]를 String[]으로 캐스팅 할 수 없기 때문
        String[] array2 = (String[]) collection.toArray();

        //이 코드는 런타임 오류 없는대신 아래 Chooser 클래스에서 경고 발생
        Chooser<String> stringChooser = new Chooser<>(collection);
        System.out.println(stringChooser.choose());

        //경고는 아래처럼 타입 안정성을 보장하지 못하는 경우를 말함
        List<Integer> ints = Arrays.asList(1, 2, 3);
        Chooser<String> chooser = new Chooser(ints);
        //여기서 런타임 에러(ClassCastException) 발생
        String choose = chooser.choose();
    }
}

class Chooser<T> {
    private final T[] array;
    private final List<T> list;

    Chooser(Collection<T> array) {
        //아래 코드는 위처럼 오류가 발생하지 않는데,
        //런타임에서는 타입 정보가 소거되므로 경고만 하고 그냥 허용함
        //대신 컴파일러는 타입 안전을 보장하지 못한다는 경고를 한다.
        this.array = (T[]) array.toArray();

        //배열 대신 리스트를 사용하면 컴파일 오류도 없고 경고도 없음.
        this.list = new ArrayList<>(array);
    }

    public T choose() {
        return array[0];
    }

    public T choose2() {
        return list.getFirst();
    }
}