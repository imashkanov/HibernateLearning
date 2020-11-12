package gleb.services;

import gleb.entities.Skill;
import gleb.repositories.SkillRepository;
import gleb.requestmodels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService extends AbstractService {

  private SkillRepository skillRepository;

  @Autowired
  public void setSkillRepository(SkillRepository skillRepository) {
    this.skillRepository = skillRepository;
  }

  public ResponseEntity getSkills() {
    SkillListOutModel outModel = new SkillListOutModel();
    try {
      List<Skill> skillList = skillRepository.findAll();
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

  public void deleteAllSkills() {
    skillRepository.deleteAll();
  }

  public ResponseEntity insertSkill(InsertSkillInModel inModel) {
    InsertSkillOutModel outModel = new InsertSkillOutModel();
    Skill insertingSkill = new Skill();
    insertingSkill.setName(inModel.getSkillName());
    try {
      Skill insertedSkill = skillRepository.save(insertingSkill);
      outModel.setId(insertedSkill.getId());
      return ResponseEntity.ok(outModel);
    } catch (Exception e) {
      return getResponseEntityOnServerError(e);
    }
  }

  public void updateSkill(UpdateSkillInModel inModel) {
    Optional<Skill> updatingSkill = skillRepository.findById(inModel.getId());
    updatingSkill.ifPresent(skill -> {
      skill.setName(inModel.getName());
      skillRepository.save(skill);
    });
  }
}
