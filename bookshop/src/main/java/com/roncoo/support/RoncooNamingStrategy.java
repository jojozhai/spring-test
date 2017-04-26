/**
 * 
 */
package com.roncoo.support;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.spi.MetadataBuildingContext;

/**
 * @author zhailiang
 *
 */
public class RoncooNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8807070034833441991L;
	
	@Override
	protected Identifier toIdentifier(String stringForm, MetadataBuildingContext buildingContext) {
		return super.toIdentifier("rc_"+stringForm, buildingContext);
	}

}
