package Exceptions;

public class InsufficientFunds extends Exception
{
    public InsufficientFunds(String msg) {
        super(msg);
    }

    public InsufficientFunds() {
        this("Insufficient Funds for bet. You poor sad loser.");
    }
}