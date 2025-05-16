package me.rockintuna.effectivejava.item;

import java.math.BigDecimal;

/*
정확한 답이 필요하다면 float와 double은 피하라
 */
public class Item60 {

    /*
    float과 double은 과학/공학 계산용으로 이진 부동소수점 연산에 쓰이며
    넓은 범위의 수를 정밀한 근사치로 계산하도록 설계되어있다.
    따라서 정확한 결과가 필요할 때는 사용하면 안된다.
     */
    public static void main(String[] args) {
        System.out.println(1.03 - 0.42);
        //결과는 0.61이 아닌 0.6100000000000001

        System.out.println(1 - 9 * 0.1);
        //결과는 0.1이 아닌 0.09999999999999998

        /*
        정확한 계산이 필요하다면 BigDecimal, int 혹은 long을 사용하자
         */
        BigDecimal decimal1 = BigDecimal.valueOf(1.03);
        BigDecimal decimal2 = BigDecimal.valueOf(0.42);
        System.out.println(decimal1.subtract(decimal2));
        //0.61

        /*
        BigDecimal 단점
        - 기본타입보다 사용하기 불편함
        - 훨씬 느림

        따라서 성능이 매우 중요하다면 소수점을 직접 관리하여 int나 long을 사용하는 것도 방법이다.
         */
    }
}
