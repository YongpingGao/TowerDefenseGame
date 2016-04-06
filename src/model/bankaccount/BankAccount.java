package model.bankaccount;

/**
 * Created by yongpinggao on 3/16/16.
 */
public class BankAccount {

    public static int INITIAL_BALANCE = 5000;
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double gold) {
        balance += gold;
    }

    public void withDraw(double gold) {
        balance -= gold;
    }
}
