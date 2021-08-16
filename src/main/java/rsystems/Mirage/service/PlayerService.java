package rsystems.Mirage.service;

import rsystems.Mirage.domain.Player;
import rsystems.Mirage.domain.Role;

import java.util.List;

public interface PlayerService {

    // Initiate
    Player savePlayer(Player player);
    Role saveRole(Role role);

    // Modification
    void addRoleToUser(String characterName, String roleName);
    void addRoleToUser(Long id, String roleName);

    // Query
    Player getPlayer(String characterName);
    List<Player> getPlayersByUID(Long uid);
    List<Player> getPlayers();
}