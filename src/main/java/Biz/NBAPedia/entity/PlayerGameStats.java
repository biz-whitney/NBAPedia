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
public class PlayerGameStats {

  @Id
  @GeneratedValue
  private int id;
  private int FG;
  private int FGA;
  private double FGP;
  private int FG3;
  private int FG3A;
  private double FG3P;
  private int FT;
  private int FTA;
  private double FTP;
  private int ORB;
  private int DRB;
  private int TRB;
  private int AST;
  private int STL;
  private int TOV;
  private int PTS;
  private int PF;
  private int BLK;
  @ManyToOne
  private Games game;
  @ManyToOne
  private PlayerSeasons playerSeason;

}
