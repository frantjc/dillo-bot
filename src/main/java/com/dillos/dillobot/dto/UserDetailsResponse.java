package com.dillos.dillobot.dto;

import java.time.LocalDate;

import com.dillos.dillobot.entities.UserDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class UserDetailsResponse {
    
  LocalDate birthday;

  public UserDetailsResponse(UserDetails userDetails) {
    this.birthday = userDetails.getBirthday();
  }
}
