package Biz.NBAPedia.service;


import Biz.NBAPedia.entity.Players;
import Biz.NBAPedia.repository.PlayersRepository;
import Biz.NBAPedia.repository.PlayersRepository.PlayerChampionships;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayersService {

  @Autowired
  private PlayersRepository playersRepository;

  public Players findPlayerById(int id) {
    return playersRepository.findById(id).orElse(null);
  }

  public List<Players> findAllPlayers() {
    return playersRepository.findAll();
  }

  public List<PlayerChampionships> findPlayerChampionships(int playerId) {
    return playersRepository.findPlayerChampionships(playerId);
  }

}
