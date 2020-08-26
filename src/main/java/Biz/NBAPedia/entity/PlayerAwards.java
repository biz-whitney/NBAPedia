package Biz.NBAPedia.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class PlayerAwards {

  @Id
  @GeneratedValue
  private int id;
  private String award;
  private String season;
  @ManyToOne
  private Players player;
  @ManyToOne
  private PlayerSeasons playerSeason;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAward() {
    return award;
  }

  public void setAward(String award) {
    this.award = award;
  }

  public String getSeason() {
    return season;
  }

  public void setSeason(String season) {
    this.season = season;
  }

  public Players getPlayer() {
    return player;
  }

  public void setPlayer(Players player) {
    this.player = player;
  }

  public PlayerSeasons getPlayerSeason() {
    return playerSeason;
  }

  public void setPlayerSeason(PlayerSeasons playerSeason) {
    this.playerSeason = playerSeason;
  }
}
