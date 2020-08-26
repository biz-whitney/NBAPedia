package Biz.NBAPedia.service;

import Biz.NBAPedia.dto.UsersDto;
import Biz.NBAPedia.entity.Teams;
import Biz.NBAPedia.entity.Users;
import Biz.NBAPedia.repository.UsersRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {

  private static List<SimpleGrantedAuthority> role = new ArrayList<>(Arrays.asList(
      new SimpleGrantedAuthority("USER")));

  @Autowired
  private UsersRepository usersRepository;
  @Autowired
  private TeamsService teamsService;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;


  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    Users user = usersRepository.findByUsername(s);
    if (user == null) {
      throw new UsernameNotFoundException("Invalid username or pasword.");
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(), role);
  }


  public Users findByUsername(String username) {
    return usersRepository.findByUsername(username);
  }


  public Users findById(int id) {
    return usersRepository.findById(id).orElse(null);
  }

  public Users save(UsersDto userDto) {
    Users user = new Users();
    user.setUsername(userDto.getUsername());
    String password = passwordEncoder.encode(userDto.getPassword());
    user.setPassword(password);
    Teams team = teamsService.findTeamById(userDto.getFavoriteTeamId());
    user.setFavoriteTeam(team);
    usersRepository.save(user);
    return user;
  }

  public Users updateUser(UsersDto usersDto) {
    Users user = usersRepository.findByUsername(usersDto.getUsername());
    Teams team = teamsService.findTeamById(usersDto.getFavoriteTeamId());
    user.setFavoriteTeam(team);
//    user.setPassword(passwordEncoder.encode(usersDto.getPassword()));
    return usersRepository.save(user);
  }
}
