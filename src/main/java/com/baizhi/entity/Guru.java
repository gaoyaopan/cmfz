package com.baizhi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guru implements Serializable {

  private String id;
  private String name;
  private String photo;
  private String status;
  private String nickName;

}
