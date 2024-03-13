package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.PassengerDTO;
import com.rays.dto.PaymentDTO;

@Repository
public class PassengerDAOImpl extends BaseDAOImpl<PassengerDTO> implements PassengerDAOInt {


	@PersistenceContext
	public EntityManager entityManager;

	List<Predicate> whereCondition = new ArrayList<Predicate>();

	@Override
	protected List<Predicate> getWhereClause(PassengerDTO dto, CriteriaBuilder builder, Root<PassengerDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getPassengerName())) {

			System.out.println("================>>>>>>>>>>>>>> " + dto.getPassengerName());

			whereCondition.add(builder.like(qRoot.get("passengerName"), dto.getPassengerName() + "%"));
		}

		return whereCondition;
	}

	@Override
	public Class<PassengerDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return PassengerDTO.class;
	}

	@Override
	public List search(PassengerDTO dto) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<PassengerDTO> cq = builder.createQuery(PassengerDTO.class);

		Root<PassengerDTO> qRoot = cq.from(PassengerDTO.class);

		List<Predicate> whereCondition = getWhereClause(dto, builder, qRoot);

		cq.where(whereCondition.toArray(new Predicate[whereCondition.size()]));

		TypedQuery<PassengerDTO> tq = entityManager.createQuery(cq);

		List list = tq.getResultList();

		return list;
	}
	
	@Override
	public void update(PassengerDTO dto) {
		entityManager.merge(dto);
	}

	@Override
	public void delete(PassengerDTO id) {
		entityManager.remove(id);

	}

	public PassengerDTO findByPk(long pk) {
		PassengerDTO dto = new PassengerDTO();
		dto = entityManager.find(PassengerDTO.class, pk);
		return dto;
	}


}
