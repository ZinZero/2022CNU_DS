package DS06;

public class Coin implements Comparable<Coin> {

    private static final int DEFAULT_VALUE = 0 ;
    private int _value ; // 동전의 금액

    // 생성자
    public Coin() {
        this.setValue(Coin.DEFAULT_VALUE);
    }
    public Coin(int givenValue) {
        this.setValue(givenValue);
    }

    // getter, setter
    public int value(){
        return this._value;
    }
    public void setValue(int newValue){
        this._value = newValue;
    }

    public boolean equals(Coin aCoin){
        return (this.value() == aCoin.value());
    }

    @Override
    public boolean equals(Object otherCoin){
        if (otherCoin.getClass() != Coin.class){
            return false;
        }
        else {
            return (this.value() == ((Coin) otherCoin).value());
        }
    }

    @Override
    public int compareTo(Coin aCoin){
        if (this.value() < aCoin.value()){
            return -1;
        }
        else if (this.value() > aCoin.value()){
            return +1;
        }
        else {
            return 0;
        }
    }

}
