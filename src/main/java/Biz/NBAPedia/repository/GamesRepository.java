package Biz.NBAPedia.repository;

import Biz.NBAPedia.entity.Games;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamesRepository extends JpaRepository<Games, Integer> {
  List<Games> findGamesBySeasonGreaterThanEqual(String season);

}
