package Biz.NBAPedia.service;


import Biz.NBAPedia.entity.PlayerAwards;
import Biz.NBAPedia.entity.Players;
import Biz.NBAPedia.repository.PlayerAwardsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerAwardsService {

  @Autowired
  private PlayerAwardsRepository playerAwardsRepository;

  public List<PlayerAwards> findAwardsForPlayer(Players player) {
    return playerAwardsRepository.findPlayerAwardsByPlayer(player);
  }

}
