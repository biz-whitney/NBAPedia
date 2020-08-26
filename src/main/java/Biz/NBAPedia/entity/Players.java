package Biz.NBAPedia.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Players")
public class Players {

  @Id
  @GeneratedValue
  private int id;
  private String name;
  private String fullName;
  private String position;
  private String position2;
  private String height;
  private int weight;
  private Date dob;
  @Column(name = "Retired", columnDefinition = "TINYINT")
  @Type(type = "org.hibernate.type.NumericBooleanType")
  private boolean retired;
  @Column(name = "About", columnDefinition = "TEXT")
  private  String about;
  private String imgUrl;

}
