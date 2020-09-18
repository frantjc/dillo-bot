package com.dillos.dillobot.dto;

import java.time.LocalDate;

import com.dillos.dillobot.entities.UserDetails;

import lombok.Data;

@Data
public class UserDetailsRequest {

  LocalDate birthday;

  public UserDetailsRequest(UserDetails userDetails) {
    this.birthday = userDetails.getBirthday();
  }

  public UserDetailsRequest() {}

}
