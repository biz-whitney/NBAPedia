package Biz.NBAPedia.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
  private String content;


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
