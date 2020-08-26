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
public class UserViewHistory {

  @Id
  @GeneratedValue
  private int id;
  private int viewCount;
  @ManyToOne
  private Players player;
  @ManyToOne
  private Users user;

  public UserViewHistory(Users user, Players player, int i) {
    this.user = user;
    this.player = player;
    this.viewCount = i;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public Players getPlayer() {
    return player;
  }

  public void setPlayer(Players player) {
    this.player = player;
  }

  public Users getUser() {
    return user;
  }

  public void setUser(Users user) {
    this.user = user;
  }
}
