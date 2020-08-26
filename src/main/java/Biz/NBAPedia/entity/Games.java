package Biz.NBAPedia.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class Games {

  @Id
  @GeneratedValue
  private int id;
  @ManyToOne
  private Teams homeTeam;
  @ManyToOne
  private Teams awayTeam;
  private int homeScore;
  private int awayScore;
  private int ots;
  @Column(name = "Playoff", columnDefinition = "TINYINT")
  @Type(type = "org.hibernate.type.NumericBooleanType")
  private boolean playoff;
  private Date date;
  private String season;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Teams getHomeTeam() {
    return homeTeam;
  }

  public void setHomeTeam(Teams homeTeam) {
    this.homeTeam = homeTeam;
  }

  public Teams getAwayTeam() {
    return awayTeam;
  }

  public void setAwayTeam(Teams awayTeam) {
    this.awayTeam = awayTeam;
  }

  public int getHomeScore() {
    return homeScore;
  }

  public void setHomeScore(int homeScore) {
    this.homeScore = homeScore;
  }

  public int getAwayScore() {
    return awayScore;
  }

  public void setAwayScore(int awayScore) {
    this.awayScore = awayScore;
  }

  public int getOts() {
    return ots;
  }

  public void setOts(int ots) {
    this.ots = ots;
  }

  public boolean isPlayoff() {
    return playoff;
  }

  public void setPlayoff(boolean playoff) {
    this.playoff = playoff;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getSeason() {
    return season;
  }

  public void setSeason(String season) {
    this.season = season;
  }
}
