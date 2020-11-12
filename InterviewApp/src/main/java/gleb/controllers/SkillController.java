package gleb.controllers;

import gleb.dto.DeleteSkillByIdRequestDto;
import gleb.dto.InsertSkillRequestDto;
import gleb.dto.UpdateSkillRequestDto;
import gleb.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SkillController {

  private SkillService skillService;

  @Autowired
  public void setSkillService(SkillService skillService) {
    this.skillService = skillService;
  }

  @GetMapping("/getSkills")
  public ResponseEntity getSkillList(HttpServletRequest request) {
    return skillService.getSkills();
  }

  @GetMapping("/getCountOfSkills")
  public ResponseEntity getCountOfSkills() {
    return skillService.getCountOfSkills();
  }

  @PostMapping("/deleteSkillById")
  public void deleteSkillById(@RequestBody DeleteSkillByIdRequestDto inModel) {
    skillService.deleteSkillById(inModel);
  }

  @DeleteMapping("/deleteAllSkills")
  public void deleteAllSkills() {
    skillService.deleteAllSkills();
  }

  @PostMapping("/insertSkill")
  public ResponseEntity insertSkill(@RequestBody InsertSkillRequestDto inModel) {
    return skillService.insertSkill(inModel);
  }

  @PutMapping("/updateSkill")
  public void updateSkill(@RequestBody UpdateSkillRequestDto inModel) {
    skillService.updateSkill(inModel);
  }
}
