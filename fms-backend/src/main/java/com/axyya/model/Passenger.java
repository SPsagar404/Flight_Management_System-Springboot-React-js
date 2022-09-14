package com.axyya.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;

@Entity
@Data
public class Passenger {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long passengerId;

  private String firstName;
  private String lastName;
  private String middleName;
  private String email;
  private String phone;

  
  

}