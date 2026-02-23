import com.sun.net.httpserver.HttpServer;
import controller.GameHandler;
import model.Game;
import repository.GameRepository;
import service.GameService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {
        GameRepository repository = new GameRepository();
        GameService service = new GameService(repository);
//        GameHandler handler = new GameHandler();
//
//        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        service.create(new Game(null,
                "Overwatch",
                "Blizzard Entertainment",
                "FPS",
                2016,
                true));

        service.create(new Game(null,
                "Infinity Nikki",
                "Papergames",
                "Action-aventure",
                2025,
                true));

        service.update(2L, new Game(null,
                "Infinity Nikki",
                "Papergames",
                "Action-aventure",
                2024,
                true));

        service.delete(2L);

        System.out.println(service.getAll());

        System.out.println(service.getById(2L));

    }

}
