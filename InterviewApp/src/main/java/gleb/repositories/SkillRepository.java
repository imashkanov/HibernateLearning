package gleb.repositories;

import gleb.dto.Skill;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Repository
public class SkillRepository implements CrudRepository<Skill, Long> {

  Session hibernateSession;

  @Autowired
  public void setHibernateSession(Session hibernateSession) {
    this.hibernateSession = hibernateSession;
  }

  public List<String> getSkillsList(HttpServletRequest request) {
    String query = "from Skill";
    Query getSkillsListQuery = hibernateSession.createQuery(query);
    return getSkillsListQuery.list();
  }

  @Override
  public <S extends Skill> S save(S s) {
    return null;
  }

  @Override
  public <S extends Skill> Iterable<S> saveAll(Iterable<S> iterable) {
    return null;
  }

  @Override
  public Optional<Skill> findById(Long aLong) {
    return Optional.empty();
  }

  @Override
  public boolean existsById(Long aLong) {
    return false;
  }

  @Override
  public Iterable<Skill> findAll() {
    return null;
  }

  @Override
  public Iterable<Skill> findAllById(Iterable<Long> iterable) {
    return null;
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(Long aLong) {

  }

  @Override
  public void delete(Skill skill) {

  }

  @Override
  public void deleteAll(Iterable<? extends Skill> iterable) {

  }

  @Override
  public void deleteAll() {

  }
}
