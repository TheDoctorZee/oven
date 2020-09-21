package com.electrolux.oven.service;

import com.electrolux.oven.entity.Hotplate;
import com.electrolux.oven.entity.Oven;
import java.util.List;

public interface OvenService {

  List<Oven> getAllOvensInfo();

  Oven getOvenInfo(Long ovenId);

  Hotplate getHotplateInfo(Long ovenId, Integer hotplateNumber);

  Long createOven();

  String deleteOven(Long ovenId);

  Hotplate setHotplatePowerLevel(Long ovenId, Integer hotplateNumber, Integer powerLevel);

  Oven turnOffOven(Long ovenId);
}
