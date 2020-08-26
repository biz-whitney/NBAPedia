package Biz.NBAPedia.controller;


import Biz.NBAPedia.dto.UsersDto;
import Biz.NBAPedia.entity.Teams;
import Biz.NBAPedia.entity.Users;
import Biz.NBAPedia.service.TeamsService;
import Biz.NBAPedia.service.UsersService;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

  @Autowired
  private UsersService usersService;
  @Autowired
  private TeamsService teamsService;


  @GetMapping("/signup")
  public String signup(Model model) {
    List<Teams> teams = teamsService.findAll();
    UsersDto usersDto = new UsersDto();
    model.addAttribute("teams", teams);
    model.addAttribute("user", usersDto);
    return "signup";
  }

  @PostMapping("/signup")
  public String signupUser(@ModelAttribute("user") UsersDto usersDto, Model model, HttpServletRequest request) {
    Users userExists = usersService.findByUsername(usersDto.getUsername());
    if (userExists == null) {
      usersService.save(usersDto);
      try {
        request.login(usersDto.getUsername(), usersDto.getPassword());
        return "index";
      } catch (ServletException e) {
        return "index";
      }
    }
    model.addAttribute("error", "Username exists");
    return signup(model);
  }

}
