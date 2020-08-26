package Biz.NBAPedia.repository;

import Biz.NBAPedia.entity.PlayerAwards;
import Biz.NBAPedia.entity.Players;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerAwardsRepository extends JpaRepository<PlayerAwards, Integer> {
  List<PlayerAwards> findPlayerAwardsByPlayer(Players player);
}
