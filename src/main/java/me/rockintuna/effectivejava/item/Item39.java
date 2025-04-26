package me.rockintuna.effectivejava.item;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
명명 패턴보다 애너테이션을 사용하라
 */
public class Item39 {

    /*
    명명 패턴의 단점
    - 오타로 인한 기능 오류
    - 올바른 요소에만 사용되리라 보증할 방법 없음
    - 프로그램 요소를 매개변수로 전달할 마땅한 방법이 없음
     */

    /*
    애너테이션은 위의 모든 문제를 해결해준다.

    메타 에너테이션
    - @Retention(RetentionPolicy.RUNTIME) : Runtime에도 유지
    - @Target(ElementType.METHOD) : 메서드에 사용

    매개변수 없는 애너테이션 : 마커 애너테이션
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Test {

    }

    /*
    매개변수를 받는 애너테이션
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ExceptionTest {
        Class<? extends Throwable> value();
    }

    @ExceptionTest(RuntimeException.class)
    private static void m1() {

    }

    /*
    배열 매개변수를 받는 애너테이션
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ArrayExceptionTest {
        Class<? extends Throwable>[] value();
    }

    @ArrayExceptionTest({RuntimeException.class})
    private static void m2() {

    }
    //배열 매개변수 대신 반복 가능 애너테이션을 사용할수도 있음

    /*
    다른 프로그래머가 소스코드에 추가 정보를 제공할 수 있는 도구를 만드는 일을 한다면 적당한 애너테이션 타입을 정의하여 제공하는 것이 좋다.
    이런 도구 제작자를 제외하고는 거의 애너테이션을 정의할 일이 없지만
    자바에서 제공하는 애너테이션 타입들을 사용하긴 해야하므로 이런 내용을 알고 있으면 좋다.
     */
}
