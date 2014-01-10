/**
 * 
 */
package com.github.solr.query.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.solr.query.builder.PrefixQueryBuilder;

/**
 * @author fuliang 2014-1-10 下午6:01:11
 *
 */
public class PrefixQueryBuilderTest {
	@Test
	public void testPrefixQueryBuilder() {
		String field = "name";
		String prefixValue = "Hen";
		PrefixQueryBuilder builder = new PrefixQueryBuilder(field, prefixValue);
		assertEquals("name:Hen*", builder.build());
	}
	
	@Test
	public void testPrefixQueryBuilderWithBoost() {
		String field = "name";
		String prefixValue = "Hen";
		float boost = 100.2f;
		PrefixQueryBuilder builder = new PrefixQueryBuilder(field, prefixValue).boost(boost);
		assertEquals(String.format("name:Hen*^100.2", field, prefixValue, boost), builder.build());
	}
}
