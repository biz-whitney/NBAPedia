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
public class PlayerSeasons {

  @Id
  @GeneratedValue
  private int id;
  @ManyToOne
  private Players player;
  @ManyToOne
  private Teams team;
  private String season;
  private double salary;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Players getPlayer() {
    return player;
  }

  public void setPlayer(Players player) {
    this.player = player;
  }

  public Teams getTeam() {
    return team;
  }

  public void setTeam(Teams team) {
    this.team = team;
  }

  public String getSeason() {
    return season;
  }

  public void setSeason(String season) {
    this.season = season;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }
}
