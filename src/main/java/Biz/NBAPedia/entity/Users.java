package Biz.NBAPedia.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Users {

//  @Id
//  @GeneratedValue
//  private int id;
  @Id
  @Column(unique=true)
  private String username;
  private String password;
  @ManyToOne
  private Teams favoriteTeam;

//  public int getId() {
//    return id;
//  }
//
//  public void setId(int id) {
//    this.id = id;
//  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Teams getFavoriteTeam() {
    return favoriteTeam;
  }

  public void setFavoriteTeam(Teams favoriteTeam) {
    this.favoriteTeam = favoriteTeam;
  }
}
