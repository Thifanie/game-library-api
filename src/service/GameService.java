package service;

import model.Game;
import repository.GameRepository;

import java.util.List;

public class GameService {
    private final GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public List<Game> getAll() {
        return repository.findAll();
    }

    public Game getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found"));
    }

    public Game create(Game game) {

        validateGame(game);

        game.setId(null); // Sécurité : éviter injection d'id
        return repository.save(game);
    }

    public Game update(Long id, Game updatedGame) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Game not found");
        }

        validateGame(updatedGame);

        updatedGame.setId(id);
        return repository.save(updatedGame);
    }

    public void delete(Long id) {

        if (!repository.deleteById(id)) {
            throw new RuntimeException("Game not found");
        }
    }

    private void validateGame(Game game) {

        if (game.getTitle() == null || game.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }

        if (game.getStudio() == null || game.getStudio().isBlank()) {
            throw new IllegalArgumentException("Studio is required");
        }

    }
}
