package dao;

import java.io.Serializable;

public class AnalystDAO implements Serializable {
  private Integer analystId;
  private String name;

  public Integer getAnalystId() {
    return analystId;
  }

  public void setAnalystId(Integer analystId) {
    this.analystId = analystId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}