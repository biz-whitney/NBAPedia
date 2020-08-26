package Biz.NBAPedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersDto {

  private String username;
  private String password;
  private int favoriteTeamId;


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

  public int getFavoriteTeamId() {
    return favoriteTeamId;
  }

  public void setFavoriteTeamId(int favoriteTeamId) {
    this.favoriteTeamId = favoriteTeamId;
  }
}
