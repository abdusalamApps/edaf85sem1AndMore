import java.util.concurrent.Semaphore

class Buffer {
    private val mutex = Semaphore(1)
    private val free = Semaphore(1)
    private val available = Semaphore(0)
    private var data = ""

    fun putLine(input: String) {
        free.acquire()
        mutex.acquire()
        data = input
        mutex.release()
        available.release()
    }

    fun getLine(): String {
        available.acquire()
        mutex.acquire()
        val d = data
        mutex.release()
        free.release()
        return d;
    }

}