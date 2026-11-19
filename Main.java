import java.net.InetSocketAddress;
import java.net.Socket;

public class Main {

    public static boolean scanPort(String ip, int port, int timeout) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(ip, port), timeout);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String targetIp = "127.0.0.1";
        int timeout = 200;

        System.out.println("Начинаем сканирование узла: " + targetIp);

        int[] portsToCheck = {80, 443, 3306, 8080};

        for (int port : portsToCheck) {
            boolean isOpen = scanPort(targetIp, port, timeout);

            if (isOpen) {
                System.out.println("[+] Порт " + port + " ОТКРЫТ");
            } else {
                System.out.println("[-] Порт " + port + " закрыт");
            }
        }
    }
}