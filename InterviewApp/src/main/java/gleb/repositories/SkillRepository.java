package gleb.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Repository
public class SkillRepository  {

  private EntityManager entityManager;

  @Autowired
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public List<String> getSkillsList(HttpServletRequest request) {
    return (List<String>)entityManager.createNativeQuery("select * from Skill").getResultList();
  }

}
