/**
 * 
 */
package com.lesson.repository.spec;

import com.lesson.domain.Book;
import com.lesson.dto.BookCondition;
import com.lesson.repository.spec.support.QueryWraper;
import com.lesson.repository.spec.support.ShopSimpleSpecification;

/**
 * @author zhailiang
 *
 */
public class BookSpec extends ShopSimpleSpecification<Book, BookCondition> {

	public BookSpec(BookCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Book> queryWraper) {
		addLikeCondition(queryWraper, "name");
	}

}
