/**
 * 
 */
package com.lesson.support;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * @author zhailiang
 *
 */
public class BookShopRepositoryImpl<T> extends SimpleJpaRepository<T, Long> {

	public BookShopRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}
	
	@Override
	public <S extends T> S save(S entity) {
		System.out.println("保存了:"+entity.getClass().getSimpleName());
		return super.save(entity);
	}

}
