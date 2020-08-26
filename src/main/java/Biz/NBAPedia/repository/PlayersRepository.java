package Biz.NBAPedia.repository;

import Biz.NBAPedia.entity.Players;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayersRepository extends JpaRepository<Players, Integer> {

  interface PlayerChampionships {
    Integer getTeamId();
    String getSeason();
    String getTeamName();
  }

  @Query(value = "SELECT team_id as TeamId, season AS Season, short_name AS TeamName"
      + " FROM ((SELECT * FROM player_seasons"
      + " WHERE  player_id = :playerId) AS T"
      + " INNER JOIN champions AS C USING(team_id, season))"
      + "INNER JOIN teams ON teams.id = C.team_id;", nativeQuery = true)
  List<PlayerChampionships> findPlayerChampionships(@Param("playerId") int playerId);

}
