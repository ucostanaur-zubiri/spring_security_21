package eus.hekuntza.zubiri.model.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
  
  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String username;
  private String password;
  
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  private List<Authority> authorities;
  
  public User(String username, String password) {
    super();
    this.username = username;
    this.password = password;
    this.authorities = new ArrayList<Authority>();
  }
  
  public User() {
    
  }
  
  
}
