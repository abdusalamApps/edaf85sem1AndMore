package youtubetutorial.semaphores

import kotlin.Throws
import java.lang.InterruptedException
import kotlin.jvm.JvmStatic

object Allowance {
    fun csn(account: BankAccount) {
        for (i in 0..9999) {
            account.deposit(1)
        }
    }

    fun student(account: BankAccount) {
        for (i in 0..9999) {
            account.withdraw(1)
        }
    }

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val bankAccount = BankAccount()
        val t1 = Thread { csn(bankAccount) }
        val t2 = Thread { student(bankAccount) }
        t1.start()
        t2.start()
        t1.join()
        t2.join()
        println("Money remaining = " + bankAccount.balance)
    }
}