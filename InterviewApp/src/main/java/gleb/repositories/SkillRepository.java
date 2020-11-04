package gleb.repositories;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Repository
public class SkillRepository  {

  Session hibernateSession;

  @Autowired
  public void setHibernateSession(Session hibernateSession) {
    this.hibernateSession = hibernateSession;
  }

  public List<String> getSkillsList(HttpServletRequest request) {
    String query = "select * from Skill";
    Query getSkillsListQuery = hibernateSession.createNativeQuery(query);
    return getSkillsListQuery.list();
  }

}
