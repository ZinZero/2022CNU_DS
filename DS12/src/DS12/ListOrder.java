package DS12;

public enum ListOrder {
    Ascending,      // 오름차순 데이터의 리스트의 유형을 표현
    Descending,     // 내림차순 데이터의 리스트의 유형을 표현
    Random;         // 무작위 데이터 리스트의 유형을 표현

    public static final String[] ORDER_NAMES = {"오름차순", "내림차순", "무작위"};

    public String orderName(){
        return ListOrder.ORDER_NAMES[this.ordinal()];
    }
}
