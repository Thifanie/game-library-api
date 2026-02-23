package repository;

import model.Game;

import java.util.*;

public class GameRepository {
//    Création du stockage des jeux
    private final Map<Long, Game> storage = new HashMap<>();
    private long nextId = 1L;

//    Méthode qui retourne un tableau contenant tous les jeux
    public List<Game> findAll() {
        return new ArrayList<>(storage.values());
    }

//    Méthode qui retourne le jeu correspondant à l'ID et gère le cas "not found"
    public Optional<Game> findById(long id) {
        return Optional.ofNullable(storage.get(id));
    }

//    Méthode qui ajoute un nouveau jeu ou remplace un jeu existant
    public Game save(Game game) {

        if (game.getId() == null) {
            // Création
            game.setId(nextId++);
        }

        storage.put(game.getId(), game);
        return game;
    }

//    Méthode qui vérifie si un ID correspond à un jeu existant
    public boolean existsById(Long id) {
        return storage.containsKey(id);
    }

//    Méthode qui supprime le jeu correspondant à l'ID et renvoie true si le jeu existe
    public boolean deleteById(Long id) {
        return storage.remove(id) != null;
    }
}
