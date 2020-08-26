package Biz.NBAPedia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Teams")
@Table(name="Teams", schema="NBAPedia")
public class Teams {

  @Id
  @GeneratedValue
  private int id;
  private String name;
  private String shortName;
  private int years;
  private int totalGames;
  private int totalWins;
  private int totalLosses;
  private int champions;
  @Column(name = "Active", columnDefinition = "TINYINT")
  @Type(type = "org.hibernate.type.NumericBooleanType")
  private boolean active;
  private String city;
  private String state;
  @Column(name = "About", columnDefinition = "TEXT")
  private String about;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public int getYears() {
    return years;
  }

  public void setYears(int years) {
    this.years = years;
  }

  public int getTotalGames() {
    return totalGames;
  }

  public void setTotalGames(int totalGames) {
    this.totalGames = totalGames;
  }

  public int getTotalWins() {
    return totalWins;
  }

  public void setTotalWins(int totalWins) {
    this.totalWins = totalWins;
  }

  public int getTotalLosses() {
    return totalLosses;
  }

  public void setTotalLosses(int totalLosses) {
    this.totalLosses = totalLosses;
  }

  public int getChampions() {
    return champions;
  }

  public void setChampions(int champions) {
    this.champions = champions;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }
}
