package com.feeder.feedersurvey.repository.impl;

import com.feeder.feedersurvey.dto.FeederDetailDto;
import com.feeder.feedersurvey.entity.FeederDetail;
import com.feeder.feedersurvey.repository.FeederDetailsCustomRepository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class FeederDetailsCustomRepositoryImpl implements FeederDetailsCustomRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<FeederDetail> findFeederDetails(FeederDetailDto feederDetailDto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FeederDetail> query = cb.createQuery(FeederDetail.class);
        Root<FeederDetail> root = query.from(FeederDetail.class);
        query.select(root);

        final List<Predicate> predicateList = new ArrayList<>();
        if(feederDetailDto.getFeederId() != null) {
            predicateList.add(cb.equal(root.get("feederId"), feederDetailDto.getFeederId()));
        }
        if(feederDetailDto.getSubstationId() != null) {
            predicateList.add(cb.equal(root.get("substationId"), feederDetailDto.getSubstationId()));
        }
        if(StringUtils.hasText(feederDetailDto.getTransformerLocationName())) {
            predicateList.add(cb.like(root.get("transformerLocationName"), feederDetailDto.getTransformerLocationName()));
        }
        if(StringUtils.hasText(feederDetailDto.getExistingTcCapacity())) {
            predicateList.add(cb.equal(root.get("existingTcCapacity"), feederDetailDto.getExistingTcCapacity()));
        }
        if(StringUtils.hasText(feederDetailDto.getProposeTcCapacity())) {
            predicateList.add(cb.equal(root.get("proposeTcCapacity"), feederDetailDto.getProposeTcCapacity()));
        }
        if(StringUtils.hasText(feederDetailDto.getProposeRMUType())) {
            predicateList.add(cb.equal(root.get("proposeRMUType"), feederDetailDto.getProposeRMUType()));
        }
        query.where(predicateList.toArray(new Predicate[] {}));
        TypedQuery<FeederDetail> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }
}
