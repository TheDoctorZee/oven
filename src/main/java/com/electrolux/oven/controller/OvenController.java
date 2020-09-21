package com.electrolux.oven.controller;

import com.electrolux.oven.entity.Hotplate;
import com.electrolux.oven.entity.Oven;
import com.electrolux.oven.entity.PowerLevel;
import com.electrolux.oven.service.OvenService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OvenController {

  private final OvenService ovenService;

  @Autowired
  public OvenController(OvenService ovenService) {
    this.ovenService = ovenService;
  }

  @GetMapping("/ovens")
  @ResponseStatus(HttpStatus.OK)
  public List<Oven> getAllOvensInfo() {
    return ovenService.getAllOvensInfo();
  }

  @GetMapping("/ovens/{ovenId}")
  @ResponseStatus(HttpStatus.OK)
  public Oven getOvenInfo(@PathVariable("ovenId") Long ovenId) {
    return ovenService.getOvenInfo(ovenId);
  }

  @GetMapping("/ovens/{ovenId}/{hotplateId}")
  @ResponseStatus(HttpStatus.OK)
  public Hotplate getHotplateInfo(@PathVariable("ovenId") Long ovenId,
      @PathVariable("hotplateId") Integer hotplateId) {
    return ovenService.getHotplateInfo(ovenId, hotplateId);
  }

  @PostMapping("/ovens")
  @ResponseStatus(HttpStatus.CREATED)
  public Long createOven() {
    return ovenService.createOven();
  }

  @DeleteMapping("/ovens/{ovenId}")
  @ResponseStatus(HttpStatus.OK)
  public String deleteOven(@PathVariable("ovenId") Long ovenId) {
    return ovenService.deleteOven(ovenId);
  }

  @PutMapping("/ovens/{ovenId}/{hotplateId}/{powerLevel}")
  @ResponseStatus(HttpStatus.OK)
  public Hotplate setHotplatePowerLevel(@PathVariable("ovenId") Long ovenId,
      @PathVariable("hotplateId") Integer hotplateId, @PathVariable("powerLevel") Integer powerLevel) {
    return ovenService.setHotplatePowerLevel(ovenId, hotplateId, powerLevel);
  }

  @PutMapping("/ovens/{ovenId}")
  @ResponseStatus(HttpStatus.OK)
  public Oven turnOffOven(@PathVariable("ovenId") Long ovenId) {
    return ovenService.turnOffOven(ovenId);
  }

}
