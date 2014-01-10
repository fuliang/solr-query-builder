/**
 * 
 */
package org.github.solr.query.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author fuliang 2014-1-10 下午6:13:07
 *
 */
public class BoolQueryBuilderTest {
	@Test
	public void testMustBoolQueryBuilder() {
		BoolQueryBuilder builder = new BoolQueryBuilder();
		builder.must(new FieldQueryBuilder("title", "hello"))
			   .must(new FieldQueryBuilder("name", "Henny"));
		assertEquals("+title:hello +name:Henny", builder.build());
	}
	
	@Test
	public void testShouldBoolQueryBuilder() {
		BoolQueryBuilder builder = new BoolQueryBuilder();
		builder.should(new FieldQueryBuilder("title", "hello"))
			   .should(new FieldQueryBuilder("name", "Henny"));
		assertEquals("title:hello name:Henny", builder.build());
	}
	
	@Test
	public void testMustNotBoolQueryBuilder() {
		BoolQueryBuilder builder = new BoolQueryBuilder();
		builder.mustNot(new FieldQueryBuilder("title", "hello"))
			   .mustNot(new FieldQueryBuilder("name", "Henny"));
		assertEquals("-title:hello -name:Henny", builder.build());
	}
	
	@Test
	public void testBoolQueryBuilder() {
		BoolQueryBuilder builder = new BoolQueryBuilder();
		builder.must(new FieldQueryBuilder("title", "hello"))
		   	    .must(new FieldQueryBuilder("name", "Henny"))
		   	    .should(new FieldQueryBuilder("price", 10))
		   	    .should(new FieldQueryBuilder("age", 20))
		   	    .mustNot(new FieldQueryBuilder("city","beijing"));
		assertEquals("+title:hello +name:Henny price:10 age:20 -city:beijing", builder.build());
	}
	
	@Test
	public void testBoolQueryBuilderWithBoost() {
		BoolQueryBuilder builder = new BoolQueryBuilder();
		builder.must(new FieldQueryBuilder("title", "hello"))
		   	    .must(new FieldQueryBuilder("name", "Henny"))
		   	    .should(new FieldQueryBuilder("price", 10))
		   	    .should(new FieldQueryBuilder("age", 20))
		   	    .mustNot(new FieldQueryBuilder("city","beijing"))
		   	    .boost(10.2f);
		assertEquals("(+title:hello +name:Henny price:10 age:20 -city:beijing)^10.2", builder.build());
	}
}
