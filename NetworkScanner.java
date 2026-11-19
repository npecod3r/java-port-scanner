import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NetworkScanner {

    private static void checkPort(Host host, int port, int timeout) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host.getIpAddress(), port), timeout);
            host.addOpenPort(port);
            System.out.println("[+] " + host.getIpAddress() + " : порт " + port + " открыт");
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        String targetIp = "google.com";
        Host targetHost = new Host(targetIp);

        ExecutorService executor = Executors.newFixedThreadPool(50);
        int timeout = 200;

        System.out.println("Начинаем многопоточное сканирование: " + targetIp);

        for (int port = 1; port <= 1000; port++) {
            final int currentPort = port;
            executor.submit(() -> checkPort(targetHost, currentPort, timeout));
        }

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println("Сканирование прервано!");
        }

        System.out.println("\n--- ИТОГОВЫЙ ОТЧЕТ ---");
        System.out.println(targetHost.toString());
    }
}