package com.electrolux.oven.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "hotplates")
public class Hotplate implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotplate_seq")
  @SequenceGenerator(name = "hotplate_seq", sequenceName = "hotplate_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "oven_id")
  private Long ovenId;
  private Integer hotplateNumber;
  @Enumerated(EnumType.STRING)
  private PowerLevel powerLevel;

  public Hotplate() {
  }

  public Hotplate(Long ovenId, Integer hotplateNumber) {
    this.ovenId = ovenId;
    this.hotplateNumber = hotplateNumber;
    this.powerLevel = PowerLevel.LEVEL_0;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getOvenId() {
    return ovenId;
  }

  public void setOvenId(Long ovenId) {
    this.ovenId = ovenId;
  }

  public Integer getHotplateNumber() {
    return hotplateNumber;
  }

  public void setHotplateNumber(Integer hotplateNumber) {
    this.hotplateNumber = hotplateNumber;
  }

  public PowerLevel getPowerLevel() {
    return powerLevel;
  }

  public void setPowerLevel(PowerLevel powerLevel) {
    this.powerLevel = powerLevel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Hotplate hotplate = (Hotplate) o;
    return Objects.equals(id, hotplate.id) &&
        Objects.equals(ovenId, hotplate.ovenId) &&
        Objects.equals(hotplateNumber, hotplate.hotplateNumber) &&
        powerLevel == hotplate.powerLevel;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ovenId, hotplateNumber, powerLevel);
  }
}
