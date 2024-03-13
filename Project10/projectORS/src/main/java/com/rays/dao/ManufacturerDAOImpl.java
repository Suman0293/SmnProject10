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
import com.rays.dto.ManufacturerDTO;

@Repository
public class ManufacturerDAOImpl extends BaseDAOImpl<ManufacturerDTO> implements ManufacturerDAOInt {

	@PersistenceContext
	public EntityManager entityManager;

	List<Predicate> whereCondition = new ArrayList<Predicate>();

	@Override
	protected List<Predicate> getWhereClause(ManufacturerDTO dto, CriteriaBuilder builder,
			Root<ManufacturerDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getManufacturerName())) {

			System.out.println("================>>>>>>>>>>>>>> " + dto.getManufacturerName());

			whereCondition.add(builder.like(qRoot.get("passengerName"), dto.getManufacturerName() + "%"));
		}

		return whereCondition;
	}

	@Override
	public Class<ManufacturerDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return ManufacturerDTO.class;
	}

	@Override
	public List search(ManufacturerDTO dto, int pageNo, int pageSize) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<ManufacturerDTO> cq = builder.createQuery(ManufacturerDTO.class);

		Root<ManufacturerDTO> qRoot = cq.from(ManufacturerDTO.class);

		List<Predicate> whereCondition = getWhereClause(dto, builder, qRoot);

		cq.where(whereCondition.toArray(new Predicate[whereCondition.size()]));

		TypedQuery<ManufacturerDTO> tq = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}

		List list = tq.getResultList();

		return list;
	}

	@Override
	public void update(ManufacturerDTO dto) {
		entityManager.merge(dto);
	}

	@Override
	public void delete(ManufacturerDTO id) {
		entityManager.remove(id);

	}

	public ManufacturerDTO findByPk(long pk) {
		ManufacturerDTO dto = new ManufacturerDTO();
		dto = entityManager.find(ManufacturerDTO.class, pk);
		return dto;
	}

	@Override
	public long add(ManufacturerDTO dto) {
		entityManager.persist(dto);
		return dto.getId();
	}

}
