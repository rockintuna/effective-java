package me.rockintuna.effectivejava.item;

/*
확장할 수 있는 열거 타입이 필요하면 인터페이스를 사용하라
 */
public class Item38 {
    /*
    대부분 열거 타입에는 확장이 필요 없으나,
    연산 코드 상수 열거의 경우에는 필요할 수 있다.
    (기본 연산 기능 외에 커스텀 연산 기능 확장)

    하지만 열거 타입은 확장을 제공하지 않기 때문에
    인터페이스를 구현할 수 있다는 점을 활용한 확장을 흉내내는 방법을 사용한다.
    1. 인터페이스 정의
    2. (기본 구현)인터페이스를 구현하는 enum 상수 정의
     */
    interface Operation {
        double apply(double a, double b);
    }

    //상수별 메서드 구현
    public enum BasicOperation implements Operation {
        PLUS {
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
        }
    }

    /*
    여기서 기본 구현 외에 기능을 확장한다면,
    (ex) 나머지 계산 기능 추가
    인터페이스 Operation을 구현하는 다른 enum을 추가한다.
     */
    public enum AddtionalOperation implements Operation {
        REMAINDER {
            @Override
            public double apply(double a, double b) {
                return a % b;
            }
        }
    }

    /*
    클라이언트에서 기본 열거 타입과 확장된 열거 타입을 적절히 사용하려면
    BasicOperation 대신 인터페이스 Operation을 타입으로 사용해야 한다.

    어디까지나 확장을 흉내낸것이므로 열거 타입끼리 구현을 상속할 수 없으며,
    코드 중복을 제거하기 위해서는 도우미 메서드로 분리하는 것이 도움이 될 수 있다.
     */
}
