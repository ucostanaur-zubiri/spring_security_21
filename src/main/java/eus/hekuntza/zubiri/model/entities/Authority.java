package eus.hekuntza.zubiri.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Authority {

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private Long id;
  
  private String name;
  
  public Authority(String name) {
    this.name = name;
  }

  public Authority() {
  }
 
  public String getName() {
    return name;
  }
  
  @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  private User user;

}
