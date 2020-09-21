package com.electrolux.oven.entity;

public enum PowerLevel {
  LEVEL_0 (0),
  LEVEL_1 (1),
  LEVEL_2 (2),
  LEVEL_3 (3);

  private final int level;

  PowerLevel(int level) {
    this.level = level;
  }

  public int getLevel() {
    return level;
  }
}
