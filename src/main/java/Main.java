import client.ClientGUI;
import server.Server;
import server.Storage;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(new Storage());
        new ClientGUI(server);
        new ClientGUI(server);
    }
}
