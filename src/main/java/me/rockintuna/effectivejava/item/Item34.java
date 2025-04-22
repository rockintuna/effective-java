package me.rockintuna.effectivejava.item;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/*
int 상수 대신 열거 타입을 사용하라
 */
public class Item34 {
    /*
    열거 타입의 장점
    - 값이 아닌 인스턴스로 존재하므로 여러 기능을 제공
    - 싱글턴임이 보증 됨
    - 컴파일 타임 타입 안정성
    - 상수를 추가하거나 순서를 바꿔도 다시 컴파일 하지 않아도 됨
    - 임의의 메서드나 필드를 추가할 수 있음
    - 임의의 인터페이스를 구현하게 할 수 있음

    필요한 원소를 컴파일타임에 다 알 수 있는 상수 집합이라면 항상 열거 타입을 사용하자.
    상수의 개수가 불변일 필요는 없다.
     */
    enum DAY {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    //enum 팁 1 : 상수별 메서드 구현
    //switch-case 보다 안전하고 유연하다
    //만약 상수끼리 구현의 공유가 필요하다면 중첨 enum을 사용하고 내부 enum에서 메서드를 구현하자
    //(전략 열거 타입 패턴)
    enum Operation {
        PLUS() {
            @Override
            public double apply(double a, double b) {
                return a + b;
            }
        },
        MINUS {
            @Override
            public double apply(double a, double b) {
                return a - b;
            }
        },
        MULTIPLY {
            @Override
            public double apply(double a, double b) {
                return a * b;
            }
        },
        DIVIDE {
            @Override
            public double apply(double a, double b) {
                return a / b;
            }
        };

        public abstract double apply(double a, double b);

        //enum 팁 2 : fromString 메서드
        //toString을 재정의한 경우 있으면 좋음
        private static final Map<String, Operation> stringToEnum =
                Stream.of(values()).collect(toMap(Enum::toString, operation -> operation));

        public static Optional<Operation> fromString(String string) {
            return Optional.ofNullable(stringToEnum.get(string));
        }


    }


}
