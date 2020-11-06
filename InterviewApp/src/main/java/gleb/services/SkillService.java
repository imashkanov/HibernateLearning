package gleb.services;

import gleb.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class SkillService extends AbstractService {

  private SkillRepository skillRepository;

  @Autowired
  public void setSkillRepository(SkillRepository skillRepository) {
    this.skillRepository = skillRepository;
  }

  public ResponseEntity getAllSkills() {
    try {
      List<String> skills = skillRepository.getSkillsList();
      return ResponseEntity.ok(skills);
    } catch (Exception e) {
      return getResponseEntityOnServerError(e);
    }
  }
}
