package SemaphoresTutorial

import java.util.*
import java.util.concurrent.Semaphore

class BlockingQueue {

}
object ProducerConsumer {
    @JvmStatic
    fun main(args: Array<String>) {
        val signal = Semaphore(0)
        val mutex = Semaphore(1);
        val list = LinkedList<String>()
        var number = 0;
        val scanner = Scanner(System.`in`)
        Thread {
            while (true) {
                val scanned = scanner.nextLine().trim()

                mutex.acquire()
                list.addLast(scanned)
                mutex.release()

                signal.release()
            }
        }.start()

        Thread {
            while (true) {
                signal.acquire()

                mutex.acquire()
                val first = list.removeFirst()
                mutex.release()

                println("got $first")
                Thread.sleep(3000)
            }
        }.start()
    }
}