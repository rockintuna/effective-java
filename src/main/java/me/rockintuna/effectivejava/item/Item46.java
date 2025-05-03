package me.rockintuna.effectivejava.item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/*
스트림에서는 부작용 없는 함수를 사용하라
 */
public class Item46 {

    /*
    스트림의 각 변환 단계는 순수 함수여야 한다.

    순수 함수 :
    오직 입력만이 결과에 영향을 주는 함수
    함수 안에서 외부에 가변 참조를 하지 않아야 하며
    이렇게 하려면 모든 스트림 연산에 건네는 모든 함수 객체는 부작용이 없어야 한다.
    */
    public static void main(String[] args) {
        /*
        아래 코드는 정상적으로 동작하긴 하지만
        스트림 답지 못하다. 즉, 반복문을 단순히 스트림으로 표현한 것 뿐이다.
        스트림 내부에서 외부 상태 (freq 맵)을 변경하고 있다.
        */
        File file = null;
        Map<String, Long> freq = new HashMap<>();
        try (Stream<String> words = new Scanner(file).tokens()) {
            words.forEach(
                    word -> {
                        freq.merge(word.toLowerCase(), 1L, Long::sum);
                    }
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        /*
        변경된 아래 코드는 결과는 동일하지만 스트림 다운 진행을 보여준다.
        */
        Map<String, Long> freq2;
        try (Stream<String> words = new Scanner(file).tokens()) {
            freq2 = words.collect(groupingBy(String::toLowerCase, counting()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //스트림 팁
        // forEach 연산은 스트림 연산중 가장 "덜" 스트림답다.
        // forEach 연산은 스트림 계싼 결과를 보고할 때만 사용하고 계산에는 쓰지 말자.


        /*
        스트림에서 수집기(collector)는 collect()에서 주로 사용된다.
        수집기는 스트림에서 사용될 때 스트림의 원소들을 객체 하나(일반적으로 collection)에 취합하는 역할을 한다.

        스트림에서 Collection을 생성하는 수집기를 반환하는 메서드
        - toList()
        - toSet()
        - toCollection(collectionFactory)
         */
        List<String> list = freq2.keySet().stream()
                .sorted(Comparator.comparingLong(freq2::get).reversed())
                .limit(10)
                .collect(toList());
    /*
            스트림에서 Map을 생성하는 수집기를 반환하는 메서드
            - toMap(keyMapper, valueMapper)
            - toMap(keyMapper, valueMapper, mergeFunction)
            - toMap(keyMapper, valueMapper, mergeFunction, mapFactory)
            */
        Map<String, String> map = freq2.keySet().stream()
                .sorted(Comparator.comparingLong(freq2::get).reversed())
                .limit(10)
                .collect(toMap(Object::toString, s -> s));
        /*
            - toConcurrentMap(keyMapper, valueMapper)
            - groupingBy(분류 함수 classifier) : 분류함수는 요소의 키를 구하는데 사용되며, Map의 value는 요소들의 List이다.
            - groupingBy(분류 함수 classifier, downstream) : 추가로 downstream 수집기를 입력하면
            수집기가 생성하는 맵의 value로서 요소 리스트 이외에 다른 구조, 값을 사용할 수 있다.
            - groupingBy(분류 함수 classifier, mapFactory, downstream)
            - groupingByConcurrent(분류 함수 classifier)
            - partitioningBy(predicate) : 키가 boolean인 Map을 반환하는 수집기
            downstream 전용 수집기 메서드
            - counting()
            - summing + Int() || Long() || Double()
            - averaging + Int() || Long() || Double()
            - summarizing + Int() || Long() || Double()
            - ...
            CharSequence 요소 전용 문자열 생성기
            - joining()
            - joining(delimiter)
         */
    }
}
