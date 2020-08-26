package Biz.NBAPedia.repository;

import Biz.NBAPedia.entity.PlayerSeasons;
import Biz.NBAPedia.entity.Teams;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerSeasonsRepository extends JpaRepository<PlayerSeasons, Integer> {

  public List<PlayerSeasons> findPlayerSeasonsByTeamAndSeason(Teams team, String season);

}
