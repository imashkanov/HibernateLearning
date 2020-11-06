package gleb.repositories.impl;

import gleb.entities.Skill;
import gleb.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Optional;

@Repository
public class SkillRepositoryImpl implements SkillRepository {

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

  @Transactional
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

  @Transactional
  @Override
  public void deleteById(Long id) {
   Query deleteSkillByIdQuery =  entityManager.createQuery("delete from Skill skill where skill.id = :id")
           .setParameter("id", id);
   deleteSkillByIdQuery.getSingleResult();
  }

  @Transactional
  @Override
  public void delete(Skill skill) {

  }

  @Transactional
  @Override
  public void deleteAll(Iterable<? extends Skill> iterable) {

  }

  @Transactional
  @Override
  public void deleteAll() {

  }
}
