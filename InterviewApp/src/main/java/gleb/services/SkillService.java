package gleb.services;

import gleb.entities.Skill;
import gleb.repositories.SkillRepository;
import gleb.requestmodels.DeleteSkillByIdInModel;
import gleb.requestmodels.SkillListOutModel;
import gleb.requestmodels.SkillsCountOutModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

  private SkillRepository skillRepository;

  @Autowired
  public void setSkillRepository(SkillRepository skillRepository) {
    this.skillRepository = skillRepository;
  }

  public ResponseEntity getSkills() {
    SkillListOutModel outModel = new SkillListOutModel();
    try {
      List<Skill> skillList = (List<Skill>) skillRepository.findAll();
      outModel.setSkills(skillList);
      return ResponseEntity.ok(outModel);
    } catch (Exception e) {
      return getResponseEntityOnServerError(e);
    }
  }

  public ResponseEntity getCountOfSkills() {
    SkillsCountOutModel outModel = new SkillsCountOutModel();
    try {
      outModel.setCount(skillRepository.count());
      return ResponseEntity.ok(outModel);
    } catch (Exception e) {
      return getResponseEntityOnServerError(e);
    }
  }

  public void deleteSkillById(DeleteSkillByIdInModel inModel) {
    skillRepository.deleteById(inModel.getId());
  }

  private ResponseEntity getResponseEntityOnServerError(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON_UTF8).body(e.getMessage());
  }
}
