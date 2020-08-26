package Biz.NBAPedia.entity;


import java.util.Date;
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
public class PlayerComments {

  @Id
  @GeneratedValue
  private int Id;
  @ManyToOne
  private Users user;
  private Date created;
  private String content;

  @ManyToOne
  private Players player;

  public PlayerComments(Players player, Date date, Users user, String content) {
    this.player = player;
    this.created = date;
    this.user = user;
    this.content = content;
  }
}
