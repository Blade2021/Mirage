package rsystems.Mirage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rsystems.Mirage.domain.Role;
import rsystems.Mirage.repo.PlayerRepo;
import rsystems.Mirage.domain.Player;
import rsystems.Mirage.repo.RoleRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional
public class PlayerServiceImpl implements PlayerService{
    private final PlayerRepo playerRepo;
    private final RoleRepo roleRepo;

    @Override
    public Player savePlayer(Player player) {
        return playerRepo.save(player);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String characterName, String roleName) {
        Player player = playerRepo.findByCharacterName(characterName);
        Role role = roleRepo.findRoleByName(roleName);

        player.getRoles().add(role);
    }

    @Override
    public void addRoleToUser(Long id, String roleName) {
        Player player = playerRepo.findPlayerById(id);
        Role role = roleRepo.findRoleByName(roleName);

        player.getRoles().add(role);
    }


    @Override
    public Player getPlayer(String characterName) {
        return playerRepo.findByCharacterName(characterName);
    }

    @Override
    public List<Player> getPlayersByUID(Long uid) {

        ArrayList<Player> players = (ArrayList<Player>) playerRepo.findAll();
        Iterator it = players.iterator();
        while(it.hasNext()){
            Player player = (Player) it.next();

            if(!player.getDUID().equals(uid)){
                it.remove();
            }
        }

        return players;
    }

    @Override
    public List<Player> getPlayers() {
        return playerRepo.findAll();
    }
}
