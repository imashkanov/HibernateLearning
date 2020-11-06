package gleb.services.impl;

import gleb.entities.Skill;
import gleb.repositories.SkillRepository;
import gleb.requestmodels.DeleteSkillByIdInModel;
import gleb.requestmodels.SkillListOutModel;
import gleb.requestmodels.SkillsCountOutModel;
import gleb.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

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

  @Override
  public ResponseEntity getCountOfSkills() {
    SkillsCountOutModel outModel = new SkillsCountOutModel();
    try {
      outModel.setCount(skillRepository.count());
      return ResponseEntity.ok(outModel);
    } catch (Exception e) {
      return getResponseEntityOnServerError(e);
    }
  }

  @Override
  public void deleteSkillById(DeleteSkillByIdInModel inModel) {
    skillRepository.deleteById(inModel.getSkillId());
  }
}
