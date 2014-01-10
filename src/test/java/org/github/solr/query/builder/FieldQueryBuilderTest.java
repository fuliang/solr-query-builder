/**
 * 
 */
package org.github.solr.query.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author fuliang 2014-1-10 下午5:53:21
 *
 */
public class FieldQueryBuilderTest {
	@Test
	public void testFieldQueryBuilder() {
		String field = "title";
		String fieldValue = "Hello world";
		FieldQueryBuilder builder = new FieldQueryBuilder(field, fieldValue);
		assertEquals("title:Hello world", builder.build());
	}
	
	@Test
	public void testFieldQueryBuilderWithBoost() {
		String field = "title";
		String fieldValue = "Hello world";
		float boost = 100.0f;
		FieldQueryBuilder builder = new FieldQueryBuilder(field, fieldValue).boost(boost);
		assertEquals("title:Hello world^100.0", builder.build());
	}
}
