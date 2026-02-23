import com.sun.net.httpserver.HttpServer;
import controller.GameHandler;
import repository.GameRepository;
import service.GameService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {
        GameRepository repository = new GameRepository();
        GameService service = new GameService();
        GameHandler handler = new GameHandler();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
    }

}
