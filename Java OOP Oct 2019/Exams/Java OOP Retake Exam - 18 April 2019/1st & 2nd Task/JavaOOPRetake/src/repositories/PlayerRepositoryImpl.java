package repositories;

import models.players.interfaces.Player;
import repositories.interfaces.PlayerRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class PlayerRepositoryImpl implements PlayerRepository {
    private Map<String, Player> players;

    public PlayerRepositoryImpl() {
        this.players = new LinkedHashMap<>();
    }

    @Override
    public int getCount() {
        return this.players.size();
    }

    @Override
    public List<Player> getPlayers() {
        return new ArrayList<>(this.players.values());
    }

    @Override
    public void add(Player player) {
        if (player.getUsername() == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }

        if (players.containsKey(player.getUsername())) {
            throw new IllegalArgumentException(String.format("Player %s already exists!", player.getUsername()));
        }

        this.players.put(player.getUsername(), player);

    }

    @Override
    public boolean remove(Player player) {
        if (player.getUsername() == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }

        return this.players.remove(player.getUsername(), player);
    }

    @Override
    public Player find(String name) {
        return this.players.values().stream().filter(player -> player.getUsername().equals(name)).findFirst().orElse(null);
    }
}
