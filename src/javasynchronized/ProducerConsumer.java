package javasynchronized;

import java.util.LinkedList;
import java.util.Scanner;

public class ProducerConsumer {
    private final LinkedList<String> messages;
    public ProducerConsumer() {
        messages = new LinkedList<>();
    }

    public synchronized void addMessage(String message) {
        messages.addLast(message);
        notifyAll();
    }
    public synchronized String getMessage() throws InterruptedException {
        while (messages.isEmpty()) wait();
        return messages.removeFirst();
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        Scanner scanner = new Scanner(System.in);

        // Producer
        new Thread(() -> {
            while (true) {
                pc.addMessage(scanner.nextLine().trim());
            }
        }).start();

        // Consumer
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("got: " + pc.getMessage());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
