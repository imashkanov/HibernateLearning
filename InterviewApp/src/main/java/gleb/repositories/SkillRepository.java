package gleb.repositories;

import gleb.entities.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Repository
public class SkillRepository implements CrudRepository<Skill, Long> {

  private EntityManager entityManager;

  @Autowired
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public List<String> getSkillsList() {
    return (List<String>)entityManager.createNativeQuery("select * from Skill").getResultList();
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
