package Biz.NBAPedia.entity;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class TeamComments {
  @Id
  @GeneratedValue
  private int Id;
  @ManyToOne
  private Users user;
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;
  private String content;
  @ManyToOne
  private Teams team;


  public TeamComments(Teams team, Date date, Users user, String content) {
    this.team = team;
    this.created = date;
    this.user = user;
    this.content = content;
  }
}
