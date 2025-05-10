package me.rockintuna.effectivejava.item;

/*
가변인수는 신중히 사용하라
 */
public class Item53 {

    //가변인수 메서드
    public int min(int... values) {
        if (values.length == 0) {
            throw new IllegalArgumentException();
        }
        int min = values[0];
        for (int i = 1; i < values.length; i++) {
            min = Math.min(min, values[i]);
        }
        return min;
    }

    public static void main2(String[] args) {
        Item53 item53 = new Item53();
        //인수를 넣지 않아도 컴파일 에러가 발생하지 않는다.
        //인수의 길이가 0인 것은 런타임에 알 수 있다.
        item53.min();
    }

    //추천 변경 방식
    //필수 매개변수가 필요하다면 아래와 같이 사용하자
    public int min(int firstArg, int... remainingArgs) {
        int min = firstArg;
        for (int i = 1; i < remainingArgs.length; i++) {
            min = Math.min(min, remainingArgs[i]);
        }
        return min;
    }

    /*
    가변인수 메서드는 내부적으로 인수의 개수와 길이가 같은 배열을 생성한다.
    그렇기 때문에 성능적으로 좋지 않을 수 있다.
    이를 개선하기 위해서는 다중정의를 사용하면 좋다.

    이를테면 아래와 같은 가변인수 메서드에서
     */
    public void fooOrg(int... args) {}

    //아래와 같이 변경하는 것이다.
    //인수의 개수 3개까지는 가변인수를 사용하지 않아 별도의 배열을 생성하지 않는다
    //4개 이상인 경우에만 가변인수를 사용한다.
    public void foo(int arg1) {}
    public void foo(int arg1, int arg2) {}
    public void foo(int arg1, int arg2, int arg3) {}
    public void foo(int arg1, int arg2, int arg3, int... args) {}

    public static void main(String[] args) {
        Item53 item53 = new Item53();
        //클라이언트에서 사용할때는 동일하게 사용하면 된다.
        item53.fooOrg(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        item53.foo(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }
}
