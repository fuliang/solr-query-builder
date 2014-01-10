/**
 * 
 */
package com.github.solr.query.builder;


/**
 * @author fuliang 2013-12-17 下午7:03:02
 *
 */
public interface BoostableQueryBuilder<B extends BoostableQueryBuilder<B>> {
	B boost(float boost);
}
