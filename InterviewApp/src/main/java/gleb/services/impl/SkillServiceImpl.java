package gleb.services.impl;

import gleb.dto.*;
import gleb.entities.EntityStatus;
import gleb.entities.Skill;
import gleb.repositories.SkillRepository;
import gleb.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {

  private SkillRepository skillRepository;

  @Autowired
  public void setSkillRepository(SkillRepository skillRepository) {
    this.skillRepository = skillRepository;
  }

  @Override
  public ResponseEntity getSkills() {
    SkillListResponseDto outModel = new SkillListResponseDto();
    try {
      List<Skill> skillList = skillRepository.findAll();
      outModel.setSkills(skillList);
      return ResponseEntity.ok(outModel);
    } catch (Exception e) {
      return getResponseEntityOnServerError(e);
    }
  }

  @Override
  public ResponseEntity getCountOfSkills() {
    SkillsCountResponseDto outModel = new SkillsCountResponseDto();
    try {
      outModel.setCount(skillRepository.count());
      return ResponseEntity.ok(outModel);
    } catch (Exception e) {
      return getResponseEntityOnServerError(e);
    }
  }

  @Override
  public void deleteSkillById(DeleteSkillByIdRequestDto deleteSkillByIdRequestDto) {
    skillRepository.deleteById(deleteSkillByIdRequestDto.getId());
  }

  @Override
  public void deleteAllSkills() {
    skillRepository.deleteAll();
  }

  @Override
  public ResponseEntity insertSkill(InsertSkillRequestDto insertSkillRequestDto) {
    InsertSkillReponseDto outModel = new InsertSkillReponseDto();
    Skill insertingSkill = new Skill();
    insertingSkill.setName(insertSkillRequestDto.getSkillName());
    insertingSkill.setStatus(EntityStatus.ACTIVE);
    try {
      Skill insertedSkill = skillRepository.save(insertingSkill);
      outModel.setId(insertedSkill.getId());
      return ResponseEntity.ok(outModel);
    } catch (Exception e) {
      return getResponseEntityOnServerError(e);
    }
  }

  @Override
  public void updateSkill(UpdateSkillRequestDto updateSkillRequestDto) {
    Optional<Skill> updatingSkill = skillRepository.findById(updateSkillRequestDto.getId());
    updatingSkill.ifPresent(skill -> {
      skill.setName(updateSkillRequestDto.getName());
      skillRepository.save(skill);
    });
  }
}
