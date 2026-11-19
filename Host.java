import java.util.ArrayList;
import java.util.List;

public class Host {
    private String ipAddress;
    private List<Integer> openPorts;

    public Host(String ipAddress) {
        this.ipAddress = ipAddress;
        this.openPorts = new ArrayList<>();
    }

    public synchronized void addOpenPort(int port) {
        openPorts.add(port);
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public List<Integer> getOpenPorts() {
        return openPorts;
    }

    @Override
    public String toString() {
        if (openPorts.isEmpty()) {
            return "Узел " + ipAddress + " (открытых портов не найдено)";
        }
        return "Узел " + ipAddress + " | Открытые порты: " + openPorts;
    }
}