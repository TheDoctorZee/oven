package com.electrolux.oven.service;

import com.electrolux.oven.dao.HotplateRepository;
import com.electrolux.oven.dao.OvenRepository;
import com.electrolux.oven.entity.Hotplate;
import com.electrolux.oven.entity.Oven;
import com.electrolux.oven.entity.PowerLevel;
import com.electrolux.oven.exeption.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OvenServiceImpl implements OvenService {

  private static final Logger LOGGER = LoggerFactory.getLogger(OvenServiceImpl.class);
  private static final String CANT_FIND_OVEN_WITH_ID = "Can't find oven with id: ";
  private static final String CANT_FIND_HOTPLATE_WITH_ID = "Can't find hotplate with number: %s and oven id: %s ";
  public static final String WRONG_POWER_LEVEL = "Can't set level: %s. Levels are in range from 0 to 3";

  private final OvenRepository ovenRepository;
  private final HotplateRepository hotplateRepository;

  @Autowired
  public OvenServiceImpl(OvenRepository ovenRepository,
      HotplateRepository hotplateRepository) {
    this.ovenRepository = ovenRepository;
    this.hotplateRepository = hotplateRepository;
  }

  @Override
  public List<Oven> getAllOvensInfo() {
    return ovenRepository.findAll();
  }

  @Override
  public Oven getOvenInfo(Long ovenId) {
    return ovenRepository.findById(ovenId)
        .orElseThrow(() -> new EntityNotFoundException(CANT_FIND_OVEN_WITH_ID + ovenId));
  }

  @Override
  public Hotplate getHotplateInfo(Long ovenId, Integer hotplateNumber) {
    return hotplateRepository.findByOvenIdAndHotplateNumber(ovenId, hotplateNumber).orElseThrow(
        () -> new EntityNotFoundException(
            String.format(CANT_FIND_HOTPLATE_WITH_ID, hotplateNumber, ovenId)));
  }

  @Override
  public Long createOven() {
    final Oven newOven = new Oven();
    final Oven savedOven = ovenRepository.save(newOven);
    return savedOven.getOvenId();
  }

  @Override
  public String deleteOven(Long ovenId) {
    ovenRepository.deleteById(ovenId);
    return "Oven was deleted";
  }

  @Override
  public Hotplate setHotplatePowerLevel(Long ovenId, Integer hotplateNumber, Integer powerLevel) {
    final Hotplate hotplate = getHotplateInfo(ovenId, hotplateNumber);
    final List<PowerLevel> powerLevels = Arrays.asList(PowerLevel.values());
    final Optional<PowerLevel> optionalLevel = powerLevels.stream()
        .filter(pl -> pl.getLevel() == powerLevel).findFirst();
    final PowerLevel level = optionalLevel.orElseThrow(() -> new EntityNotFoundException(
        String.format(WRONG_POWER_LEVEL, powerLevel)));
    hotplate.setPowerLevel(level);
    return hotplateRepository.save(hotplate);
  }

  @Override
  public Oven turnOffOven(Long ovenId) {
    final Oven oven = getOvenInfo(ovenId);
    oven.getHotplates().forEach(plate -> plate.setPowerLevel(PowerLevel.LEVEL_0));
    return ovenRepository.save(oven);
  }

}
