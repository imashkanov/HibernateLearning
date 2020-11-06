package gleb.controllers;

import gleb.requestmodels.DeleteSkillByIdInModel;
import gleb.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
  public void deleteSkillById(@RequestBody DeleteSkillByIdInModel inModel) {
    skillService.deleteSkillById(inModel);
  }
}
