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
}
