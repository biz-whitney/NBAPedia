package Biz.NBAPedia.service;

import Biz.NBAPedia.entity.PlayerGameStats;
import Biz.NBAPedia.repository.PlayerGameStatsRepository;
import Biz.NBAPedia.repository.PlayerGameStatsRepository.CareerStats;
import Biz.NBAPedia.repository.PlayerGameStatsRepository.RosterPlayer;
import Biz.NBAPedia.repository.PlayerGameStatsRepository.SeasonStats;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerGameStatsService {

  @Autowired
  private PlayerGameStatsRepository playerGameStatsRepository;

  public PlayerGameStats findStatById(int id) {
    return playerGameStatsRepository.findById(id).orElse(null);
  }


  public List<SeasonStats> findPlayerSeasonStats(int playerId) {
    return playerGameStatsRepository.findPlayerSeasonStats(playerId);

  }

  public List<RosterPlayer> findRosterPlayerStats(int gameId, int teamId) {
    return playerGameStatsRepository.getTeamGameRoster(gameId, teamId);
  }

  public CareerStats findPlayerCareerStats(int playerId) {
    return playerGameStatsRepository.findCareerStatsForPlayer(playerId);
  }


}
