package youtubetutorial.semaphores

import java.util.*
import java.util.concurrent.Semaphore

class BlockingQueue {
    private val signal = Semaphore(0)
    private val mutex = Semaphore(1)
    val list = LinkedList<String>()

    fun putLine(line: String) {
        mutex.acquire()
        list.addLast(line)
        mutex.release()

        signal.release()
    }

    fun getLine(): String {
        signal.acquire()

        mutex.acquire()
        val first = list.removeFirst()
        mutex.release()
        return first
    }
}
object ProducerConsumer {
    private val blockingQueue = BlockingQueue()
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        Thread {
            while (true) {
                val scanned = scanner.nextLine().trim()
                blockingQueue.putLine(scanned)
            }
        }.start()

        Thread {
            while (true) {
                val first = blockingQueue.getLine()
                println("got $first")
                Thread.sleep(3000)
            }
        }.start()
    }
}