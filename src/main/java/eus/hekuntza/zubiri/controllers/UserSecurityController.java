package eus.hekuntza.zubiri.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import eus.hekuntza.zubiri.dto.UserDto;
import eus.hekuntza.zubiri.model.entities.User;
import eus.hekuntza.zubiri.model.repositories.UserRepository;

@Controller
@RequestMapping("/")
public class UserSecurityController {

  @Autowired
  UserRepository userRepository;
  
  @Autowired
  PasswordEncoder passwordEncoder;
  
  @GetMapping("/login")
  public String showLoginForm(Model model){
      // create model object to store form data
      UserDto user = new UserDto();
      model.addAttribute("user", user);
      return "loginview";
  }

  @GetMapping("/register")
  public String showRegistrationForm(Model model){
      // create model object to store form data
      UserDto user = new UserDto();
      model.addAttribute("user", user);
      return "registerview";
  }
  
  // handler method to handle user registration form submit request
  @PostMapping("/register/save")
  public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                             BindingResult result,
                             Model model){
    
      Optional<User> existingUser = userRepository.findUserByUsername(userDto.getUsername());

      if(existingUser.isPresent() && existingUser.get().getUsername() != null){
          result.rejectValue("username", null,
                  "There is already an account registered with the same username");
      }

      if(result.hasErrors()){
          model.addAttribute("user", userDto);
          return "/registerview";
      }
      
      String pass = passwordEncoder.encode(userDto.getPassword());
      User u = new User(userDto.getUsername(), pass);
      userRepository.save(u);     

      return "redirect:/register?success";
  }
  
}
