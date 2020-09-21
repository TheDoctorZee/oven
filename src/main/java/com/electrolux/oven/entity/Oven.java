package com.electrolux.oven.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ovens")
public class Oven implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oven_seq")
  @SequenceGenerator(name = "oven_seq", sequenceName = "oven_id_seq", allocationSize = 1)
  @Column(name = "oven_id")
  private Long ovenId;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "oven_id", referencedColumnName = "oven_id")
  private List<Hotplate> hotplates = new ArrayList<>();

  public Oven() {
    hotplates.add(new Hotplate(ovenId, 0));
    hotplates.add(new Hotplate(ovenId, 1));
    hotplates.add(new Hotplate(ovenId, 2));
    hotplates.add(new Hotplate(ovenId, 3));
  }

  public Long getOvenId() {
    return ovenId;
  }

  public List<Hotplate> getHotplates() {
    return hotplates;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Oven oven = (Oven) o;
    return Objects.equals(ovenId, oven.ovenId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ovenId);
  }
}
