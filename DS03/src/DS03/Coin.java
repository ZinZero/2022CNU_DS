package DS03;

public class Coin {
    private static final int DEFALUT_VALUE = 0 ;

    private int _value ; // 동전의 금액

    public Coin() {
        this.setValue(Coin.DEFALUT_VALUE);
    }
    public Coin(int givenValue) {
        this.setValue(givenValue);
    }

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
}
