package gleb.controllers;

import gleb.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SkillController {
  private SkillService skillService;

  @Autowired
  public void setSkillService(SkillService skillService) {
    this.skillService = skillService;
  }

  @GetMapping("/skills")
  public ResponseEntity getAllSkills(HttpServletRequest request) {
    return skillService.getAllSkills();
  }
}
