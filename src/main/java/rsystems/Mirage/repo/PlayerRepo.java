package rsystems.Mirage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rsystems.Mirage.domain.Player;

public interface PlayerRepo extends JpaRepository<Player, Long> {

    Player findByCharacterName(String characterName);
    Player findPlayerById(Long id);
}
