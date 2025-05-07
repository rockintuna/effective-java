package me.rockintuna.effectivejava.item;


import java.util.Date;

/*
적시에 방어적 복사본을 만들라
 */
public class Item50 {

    /*
    자바는 비교적 안전한 언어임에 분명하지만,
    그래도 방어적으로 프로그래밍 할 필요가 있다.
     */

    //Date는 가변 타입이다.
    //불변으로 하고싶으면 Date 대신 Instant 이나 LocalDateTime,ZonedDateTime 을 사용하자.
    //Date는 낡은 API이므로 사용하지 말자.
    private final Date start;
    private final Date end;
    private boolean isCopied;

    public Item50(Date start, Date end, boolean isCopied) {
        this.start = start;
        this.end = end;
        this.isCopied = isCopied;
    }

    //방어적 복사본을 사용한 생성자
    public Item50(Date start, Date end) {

        //방어적 복사에 clone을 사용하지 않는다.
        //확장 가능한 타입의 경우 clone 메서드가 재정의될 수 있어 위험하기 때문
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());

        //유효성 검사...
        //유효성 검사를 복사 후에 하는 이유는
        //이 찰나의 시간 안에 다른 스레드에서 가변 인수를 변경할 수 있기 때문 (TOCTOU 공격)
    }

    public Date start() {
        return start;
    }

    public Date copiedStart() {
        return new Date(start.getTime());
    }

    public Date end() {
        return end;
    }

    public Date copiedEnd() {
        return new Date(end.getTime());
    }

    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();
        Item50 item1 = new Item50(start, end, true);
        //가변 타입 필드는 쉽게 수정된다.
        end.setYear(1992);
        assert item1.end().getYear() == 1992;
        item1.end().setYear(1994);
        assert item1.end().getYear() == 1994;

        Item50 item2 = new Item50(start, end);
        //방어적 복사본을 사용한 생성자와 메서드는 쉽게 수정되지 않는다.
        end.setYear(2000);
        assert item2.end().getYear() != 2000;
        item2.copiedEnd().setYear(2020);
        assert item2.end().getYear() != 2020;
    }

    /*
    가변인 객체를 내부에 저장하거나 클라이언트에 반환할 때는 반드시 심사숙고해야한다.
    안심할 수 없다면 방어적 복사본을 사용하자.
    방어적 복사는 성능 저하가 따르고 항상 쓸 수 있는게 아니므로 되도록 불변 객체를 사용하자.
    신뢰할 수 있는 클라이언트라면 가변 객체를 수정하지 않도록 문서화하자.
     */
}
