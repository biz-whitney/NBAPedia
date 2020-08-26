package Biz.NBAPedia.service;


import Biz.NBAPedia.entity.Games;
import Biz.NBAPedia.repository.GamesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamesService {

  @Autowired
  private GamesRepository gamesRepository;

  public List<Games> findRecentGames(String season) {
    return gamesRepository.findGamesBySeasonGreaterThanEqual(season);
  }

  public List<Games> findAllGames() {
    return gamesRepository.findAll();
  }

  public Games findById(int id) {
    return gamesRepository.findById(id).orElse(null);
  }

}
