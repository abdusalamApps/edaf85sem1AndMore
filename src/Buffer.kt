import java.util.*
import java.util.concurrent.Semaphore

class Buffer() {
    private var mutex: Semaphore = Semaphore(1)
    private var free: Semaphore = Semaphore(8)
    private var available: Semaphore = Semaphore(0)
    private var data = LinkedList<String>()

    fun putLine(input: String) {
        free.acquire()
        mutex.acquire()
        data.addLast(input)
        mutex.release()
        available.release()
    }

    fun getLine(): String {
        available.acquire()
        mutex.acquire()
        val d = data.removeFirst()
        mutex.release()
        free.release()
        return d
    }

}