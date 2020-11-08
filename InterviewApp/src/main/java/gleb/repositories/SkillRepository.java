package gleb.repositories;

import gleb.entities.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Optional;

@Repository
public class SkillRepository implements CrudRepository<Skill, Long> {

  private EntityManager entityManager;

  @Autowired
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Transactional
  @Override
  public <S extends Skill> S save(S s) {
    return null;
  }

  @Override
  public <S extends Skill> Iterable<S> saveAll(Iterable<S> iterable) {
    return null;
  }

  @Override
  public Optional<Skill> findById(Long id) {
    Skill skill = entityManager.find(Skill.class, id);
    return Optional.of(skill);
  }

  @Override
  public boolean existsById(Long id) {
    int countById = (int) entityManager.createQuery
      ("select count (*) from Skill skill where skill.id = :idParam")
      .setParameter("idParam", id).getSingleResult();
    return countById > 0;
  }

  @Override
  public Iterable<Skill> findAll() {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Skill> criteriaSkill = criteriaBuilder.createQuery(Skill.class);
    return entityManager.createQuery(criteriaSkill.select(criteriaSkill.from(Skill.class))).getResultList();
  }

  @Override
  public Iterable<Skill> findAllById(Iterable<Long> iterable) {
    return null;
  }

  @Override
  public long count() {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> criteriaLong = criteriaBuilder.createQuery(Long.class);
    criteriaLong.select(criteriaBuilder.count(criteriaLong.from(Skill.class)));
    return entityManager.createQuery(criteriaLong).getSingleResult();
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    Query deleteSkillByIdQuery = entityManager.createQuery("delete from Skill s where s.id = :id").setParameter("id", id);
    deleteSkillByIdQuery.executeUpdate();
  }

  @Transactional
  @Override
  public void delete(Skill skill) {
    entityManager.remove(skill);
  }

  @Override
  public void deleteAll(Iterable<? extends Skill> skillsCollectionForDelete) {
    skillsCollectionForDelete.forEach(this::delete);
  }


  @Override
  public void deleteAll() {
    Iterable<Skill> skillForDelete = findAll();
    skillForDelete.forEach(this::delete);
  }
}
