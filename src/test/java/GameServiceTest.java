import model.Game;
import org.junit.Test;
import repository.GameRepository;
import service.GameService;

import static org.junit.Assert.assertEquals;

public class GameServiceTest {

    @Test
    public void shouldCreateGame() {
        GameRepository repository = new GameRepository();
        GameService service = new GameService(repository);
        Game game = new Game(null,
                "Overwatch",
                "Blizzard Entertainment",
                "FPS",
                2016,
                true);
        service.create(game);

        assertEquals(1, service.getAll().size());
        assertEquals("Overwatch", service.getAll().get(0).getTitle());
    }

}
