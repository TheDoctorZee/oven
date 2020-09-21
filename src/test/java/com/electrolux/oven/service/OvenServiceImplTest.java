package com.electrolux.oven.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.electrolux.oven.dao.HotplateRepository;
import com.electrolux.oven.dao.OvenRepository;
import com.electrolux.oven.entity.Hotplate;
import com.electrolux.oven.entity.Oven;
import com.electrolux.oven.entity.PowerLevel;
import com.electrolux.oven.exeption.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OvenServiceImplTest {

  @Mock
  private OvenRepository ovenRepository;
  @Mock
  private HotplateRepository hotplateRepository;
  @InjectMocks
  private OvenServiceImpl ovenService;

  private static Long ovenId = 1L;
  private static Integer hotplateId = 1;
  private static Oven oven;

  @BeforeAll
  public static void setUp() {
    oven = new Oven();
  }

  @Test
  public void getOvenInfo() {
    doReturn(Optional.of(oven)).when(ovenRepository).findById(ovenId);

    final Oven actual = ovenService.getOvenInfo(ovenId);

    assertEquals(oven, actual);
  }

  @Test
  public void getOvenInfo_notFound() {
    doReturn(Optional.ofNullable(null)).when(ovenRepository).findById(ovenId);

    assertThrows(EntityNotFoundException.class, () -> ovenService.getOvenInfo(ovenId));
  }

  @Test
  public void getHotplateInfo() {
    final List<Hotplate> hotplates = oven.getHotplates();
    final Hotplate hotplate = hotplates.get(0);
    doReturn(Optional.of(hotplate)).when(hotplateRepository).findByOvenIdAndHotplateNumber(ovenId, hotplateId);

    final Hotplate actual = ovenService.getHotplateInfo(ovenId, hotplateId);

    assertEquals(hotplate, actual);
  }

  @Test
  public void getHotplateInfo_notFound() {
    final List<Hotplate> hotplates = oven.getHotplates();
    final Hotplate hotplate = hotplates.get(0);
    doReturn(Optional.ofNullable(null)).when(hotplateRepository).findByOvenIdAndHotplateNumber(ovenId, hotplateId);

    assertThrows(EntityNotFoundException.class, () -> ovenService.getHotplateInfo(ovenId, hotplateId));
  }

  @Test
  public void createOven() {
    doReturn(oven).when(ovenRepository).save(any(Oven.class));

    final Long oven = ovenService.createOven();

    verify(ovenRepository).save(any(Oven.class));
  }

  @Test
  public void setHotplatePowerLevel() {
    final List<Hotplate> hotplates = oven.getHotplates();
    final Hotplate hotplate = hotplates.get(0);
    doReturn(Optional.of(hotplate)).when(hotplateRepository).findByOvenIdAndHotplateNumber(ovenId, hotplateId);
    doReturn(hotplate).when(hotplateRepository).save(any(Hotplate.class));

    final Hotplate actual = ovenService.setHotplatePowerLevel(ovenId, hotplateId, 3);

    assertEquals(PowerLevel.LEVEL_3, actual.getPowerLevel());
  }

  @Test
  public void setHotplatePowerLevel_wrongLevel() {
    final List<Hotplate> hotplates = oven.getHotplates();
    final Hotplate hotplate = hotplates.get(0);
    doReturn(Optional.of(hotplate)).when(hotplateRepository).findByOvenIdAndHotplateNumber(ovenId, hotplateId);

    assertThrows(EntityNotFoundException.class, () -> ovenService.setHotplatePowerLevel(ovenId, hotplateId, 4));
  }

  @Test
  public void turnOffOven() {
    final Oven oven = new Oven();
    final List<Hotplate> hotplates = oven.getHotplates();
    hotplates.forEach(plate -> plate.setPowerLevel(PowerLevel.LEVEL_3));

    doReturn(oven).when(ovenRepository).save(any(Oven.class));
    doReturn(Optional.of(oven)).when(ovenRepository).findById(ovenId);

    final Oven actual = ovenService.turnOffOven(ovenId);

    verify(ovenRepository).save(any(Oven.class));
    assertEquals(oven, actual);
  }

}