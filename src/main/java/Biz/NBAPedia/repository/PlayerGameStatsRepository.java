package Biz.NBAPedia.repository;

import Biz.NBAPedia.entity.PlayerGameStats;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerGameStatsRepository extends JpaRepository<PlayerGameStats, Integer> {

//  @Query(value = "SELECT player_season_id, AVG(fgp) AS fgp  FROM player_game_stats WHERE "
//      + "player_season_id = ?1 "
//      + "GROUP BY "
//      + "player_season_id ", nativeQuery =
//      true)
//  List<PlayerGameStats> findPlayerGameStats(int playerId);

  interface SeasonStats {
    Integer getId();
    Integer getTeamId();
    String getSeason();
    String getTeamName();
    Integer getSalary();
    Integer getFG();
    Integer getFG3();
    Integer getFT();
    Double getTRB();
    Double getAST();
    Double getBLK();
    Double getSTL();
    Double getPTS();
  }

//  @Query(value = "SELECT T.id as id, team_id, season , salary,  SUM(fg) AS FG, "
//      + "SUM(fg3) AS FG3, SUM(ft) AS FT, AVG(trb) AS TRB, AVG(ast) AS AST, AVG(blk) AS BLK, "
//      + "AVG(stl) AS STL, AVG(pts) as PTS "
//      + "FROM ((SELECT  id, team_id, season , salary FROM player_seasons "
//      + "WHERE  player_id = :playerId) AS T  "
//      + "INNER JOIN player_game_stats AS pgs ON T.id = pgs.player_season_id)"
//      + "GROUP BY T.id, season", nativeQuery = true)

  @Query(value = "SELECT T.id as id, team_id AS TeamId, season, short_name AS TeamName, salary,  "
      + "SUM(fg) AS "
      + "FG,"
      + "  SUM(fg3) AS FG3, SUM(ft) AS FT, AVG(trb) AS TRB, AVG(ast) AS AST, AVG(blk) AS BLK,"
      + "   AVG(stl) AS STL, AVG(pts) as PTS "
      + "FROM ((SELECT  * FROM player_seasons "
      + "   WHERE  player_id = :playerId) AS T "
      + "     INNER JOIN player_game_stats AS pgs ON T.id = pgs.player_season_id)"
      + "     INNER JOIN teams ON teams.id = T.team_id"
      + "      GROUP BY T.id, season, short_name;", nativeQuery = true)
  List<SeasonStats> findPlayerSeasonStats(@Param("playerId") int playerId);

  interface RosterPlayer {
    Integer getId();
    String getName();
    String getPosition();
    Integer getFG();
    Integer getFG3();
    Integer getFT();
    Double getAST();
    Double getSTL();
    Double getTRB();
    Double getBLK();
    Double getPTS();

  }


  @Query(value = "SELECT players.id AS Id, name AS Name, position AS Position, fg AS FG, "
      + "fg3 AS FG3, ft AS FT, ast AS AST, stl AS STL, trb AS TRB, blk AS BLK, pts AS PTS"
      + " FROM (SELECT * FROM player_game_stats WHERE game_id = :gameId) AS G INNER JOIN "
      + "player_seasons ON "
      + "    G.player_season_id = player_seasons.id INNER JOIN players ON player_seasons"
      + ".player_id = players.id"
      + " WHERE team_id = :teamId", nativeQuery = true)
  List<RosterPlayer> getTeamGameRoster(@Param("gameId") int gameId, @Param("teamId") int teamId);

//  @Query(value = "SELECT  id, team_id, season , salary FROM player_seasons "
//      + "WHERE  player_id = :playerId", nativeQuery = true)
//  List<SeasonStats> findPlayerSeasonStats(@Param("playerId") int playerId);

  interface CareerStats {
    Double getId();
    Double getFG();
    Double getFGP();
    Double getFG3();
    Double getFG3P();
    Double getFT();
    Double getFTP();
    Double getAST();
    Double getSTL();
    Double getBLK();
    Double getTRB();
    Double getPTS();
  }

  @Query(value = "SELECT player_id, SUM(fg) AS FG, AVG(fgp) AS FGP, SUM(fg3) AS FG3, AVG(fg3p) AS FG3P,"
      + " SUM(ft) AS FT, AVG(ftp) AS FTP, SUM(ast) AS AST, SUM(stl) AS STL, SUM(blk) AS BLK, "
      + " SUM(trb) AS TRB, SUM(pts) AS PTS "
      + "FROM player_seasons INNER JOIN player_game_stats "
      + " ON player_game_stats.player_season_id = player_seasons.id"
      + " WHERE player_id = :playerId"
      + " GROUP BY player_id;", nativeQuery = true)
  CareerStats findCareerStatsForPlayer(@Param("playerId") int playerId);

}
