package me.rockintuna.effectivejava.item;

/*
톱래밸 클래스는 한 파일에 하나만
 */
class Item25 {
    public static void main(String[] args) {
        System.out.println(Item25Other1.NAME + Item25Other2.NAME);
        /*
        class Item25Other1 {
           public static final String NAME = "pan";
        }

        class Item25Other2 {
            public static final String NAME = "cake";
        }

        동일한 문장의 클래스 파일 2개 Item25Other1.java, Item25Other2.java에 위와 같이 2개의 클래스를 작성하면
        한 클래스에 대한 정의가 여러 개 만들어지기 때문에 컴파일러에 소스 파일을 전달하는 순서에 따라 동작이 달라질 수 있다.
        컴파일러가 이름이 중복되는 것을 알게 되는 순간 컴파일 에러가 발생한다.

        톱레벨 클래스를 한 파일당 하나만 담으면 위와 같은 문제는 발생하지 않는다.

        굳이 여러 톱레벨 클래스를 한 파일에 담고 싶다면(다른 클래스에 달린 부차적인 클래스) 정적 멤버 클래스를 사용

         */
    }
}