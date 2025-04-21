package me.rockintuna.effectivejava.item;

import org.springframework.core.ParameterizedTypeReference;

import java.util.HashMap;
import java.util.Map;

/*
타입 안전 이종 컨테이너를 고려하라
 */
public class Item33 {
    /*
    하나의 컨테이너(제네릭 인스턴스)에서 여러 매개변수화 타입을 쓰려면?
*/
    public static void main(String[] args) {
        //String.class 리터럴의 타입은 Class<String>
        assert String.class instanceof Class<String>;

        Favorites favorites = new Favorites();
        favorites.putFavorite(String.class, "A");
    }

    /*
    타입 안전 이종 컨테이너 패턴 :
    컨테이너 대신 키를 매개변수화(Class<T>)하고
    컨테이너에 값을 넣거나 뺄 때 매개변수화한 키를 함께 제공(타입 토큰이라고 함)하는 방식
     */
    static class Favorites {
        private Map<Class<?>, Object> favorites = new HashMap<>();

        public <T> void putFavorite(Class<T> key, T value) {
            favorites.put(key, value);
        }

        public <T> T getFavorite(Class<T> key) {
            /*
            아래와 같이 비검사 형변환 하는 것 보다
            return (T) favorites.get(key);

            Class cast(Object obj) 메서드를 사용하면 타입 안전하다.
             */
            return key.cast(favorites.get(key));
        }
    }
    /*
    타입 안전 이종 컨테이너 패턴의 예시인 Favorites,
    모든 키의 타입이 제각각이라 여러 가지 타입의 원소를 담을 수 있다.

    타입 안전 이종 컨테이너의 한계
    1. key에 Class 로 타입을 입력하면 타입 안정성이 깨진다.
    2. List<String>.class는 문법 오류인 것 처럼 실체화 불가 타입에는 사용할 수 없다.
    (2번 문제는 ParameterizedTypeReference(슈퍼 타입 토큰)를 사용하면 해결할 수 있다, 완벽하지는 않음)
     */
}


