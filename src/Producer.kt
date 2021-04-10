class Producer(private val buffer: Buffer) : Thread() {
    override fun run() {
        var producedData = "";
        try {
            while (true) {
                if (producedData.length > 50) break
                producedData = "Hi $producedData"
                sleep(1000)
                buffer.putLine(producedData)
            }

        } catch (e: Exception) {
            return
        }

    }
}

class Consumer(private val buffer: Buffer) : Thread() {
    override fun run() {
        try {
            sleep(1000)
            while (true) {
                println(buffer.getLine())
            }
        } catch (e: Exception) {
            return
        }
    }
}