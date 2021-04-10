package youtubetutorial.semaphores;

import java.util.concurrent.Semaphore;

public class BankAccount {
    private int balance;
    private final Semaphore mutex;

    BankAccount() {
        balance = 0;
        mutex = new Semaphore(1);
    }

    public void deposit(int amount) {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        balance += amount;
        mutex.release();
    }

    // ana kaslan w bdon aml ma b3ref ashtghel shi
    // aml kter shatoora w btdaresni kl youm

    public void withdraw(int amount) {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        balance -= amount;
        mutex.release();
    }

    public int getBalance() {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int b = balance;
        mutex.release();
        return b;
    }
}
