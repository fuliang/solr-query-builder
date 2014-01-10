/**
 * 
 */
package org.github.solr.query.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author fuliang 2014-1-10 下午6:08:11
 *
 */
public class RangeQueryBuilderTest {
	@Test
	public void testRangeQueryBuilder() {
		RangeQueryBuilder<Integer> builder = new RangeQueryBuilder<Integer>();
		assertEquals("price:[0 TO 100]", builder.field("price").from(0).to(100).build());
	}
	
	@Test
	public void testRangeQueryBuilderWithBoost() {
		RangeQueryBuilder<Integer> builder = new RangeQueryBuilder<Integer>();
		assertEquals("price:[0 TO 100]^100.0", builder.field("price").from(0).to(100).boost(100).build());
	}
}
