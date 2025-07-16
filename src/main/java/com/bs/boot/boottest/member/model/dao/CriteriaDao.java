package com.bs.boot.boottest.member.model.dao;

import com.bs.boot.boottest.member.model.entity.MemberEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CriteriaDao {
    @PersistenceContext
    private  EntityManager em;

    public List<MemberEntity> findAllCriteria(String name){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MemberEntity> cqurey = cb.createQuery(MemberEntity.class);
        Root<MemberEntity> member = cqurey.from(MemberEntity.class);
        cqurey.select(member);
        List<Predicate>  predicates = new ArrayList<Predicate>();
        if(name!=null){
            predicates.add(cb.like(member.get("name"), "%"+name+"%"));
        }
        if(predicates.size()>0){
            cqurey.where(predicates.toArray(new Predicate[predicates.size()]));
        }
        TypedQuery<MemberEntity> query = em.createQuery(cqurey);
        return query.getResultList();
    }
}
