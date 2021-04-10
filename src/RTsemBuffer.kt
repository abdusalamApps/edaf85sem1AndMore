import java.util.concurrent.Semaphore

object RTsemBuffer {
    @JvmStatic
    fun main(args: Array<String>) {
        val buffer = Buffer()
        val producer = Producer(buffer)
        val consumer = Consumer(buffer)
        consumer.start()
        producer.start()
        println("\n\n RTsemBuffer: Threads are running ...")
        try {
            producer.join()
            Thread.sleep(10000)
            consumer.interrupt()
            consumer.join()
        } catch (e: InterruptedException) {
            return
        }
        println("\n"+"RTsemBuffer: Execution completed!")

    }
}