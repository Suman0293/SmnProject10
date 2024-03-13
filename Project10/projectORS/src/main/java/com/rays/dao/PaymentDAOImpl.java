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
import com.rays.dto.PaymentDTO;

@Repository
public class PaymentDAOImpl extends BaseDAOImpl<PaymentDTO> implements PaymentDAOInt {

	@PersistenceContext
	public EntityManager entityManager;

	List<Predicate> whereCondition = new ArrayList<Predicate>();

	@Override
	protected List<Predicate> getWhereClause(PaymentDTO dto, CriteriaBuilder builder, Root<PaymentDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getPaymentMode())) {

			System.out.println("================>>>>>>>>>>>>>> " + dto.getPaymentMode());

			whereCondition.add(builder.like(qRoot.get("paymentMode"), dto.getPaymentMode() + "%"));
		}

		return whereCondition;
	}

	@Override
	public Class<PaymentDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return PaymentDTO.class;
	}

	@Override
	public List search(PaymentDTO dto) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<PaymentDTO> cq = builder.createQuery(PaymentDTO.class);

		Root<PaymentDTO> qRoot = cq.from(PaymentDTO.class);

		List<Predicate> whereCondition = getWhereClause(dto, builder, qRoot);

		cq.where(whereCondition.toArray(new Predicate[whereCondition.size()]));

		TypedQuery<PaymentDTO> tq = entityManager.createQuery(cq);

		/*
		 * if (pageSize > 0) { tq.setFirstResult(pageNo * pageSize);
		 * tq.setMaxResults(pageSize); }
		 */

		List list = tq.getResultList();

		return list;
	}

	@Override
	public void update(PaymentDTO dto) {
		entityManager.merge(dto);
	}

	@Override
	public void delete(PaymentDTO id) {
		entityManager.remove(id);

	}

	public PaymentDTO findByPk(long pk) {
		PaymentDTO dto = new PaymentDTO();
		dto = entityManager.find(PaymentDTO.class, pk);
		return dto;
	}

}