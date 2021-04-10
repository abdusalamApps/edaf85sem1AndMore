package javasynchronized;

import java.util.concurrent.Semaphore;

public class BankAccount {
    private int balance;

    public BankAccount() {
        balance = 0;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ana kaslan w bdon aml ma b3ref ashtghel shi
    // aml kter shatoora w btdaresni kl youm

    public synchronized void withdraw(int amount) {
        balance -= amount;
    }

    public synchronized int getBalance() {
        return balance;
    }
}
