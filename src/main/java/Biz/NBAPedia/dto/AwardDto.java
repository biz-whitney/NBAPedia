package Biz.NBAPedia.dto;

public class AwardDto {
  String award;
  String team;
  String season;

  public AwardDto(String award, String team, String season) {
    this.award = award;
    this.team = team;
    this.season = season;
  }
}
