/**
 * 
 */
package com.github.solr.query.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * @author fuliang 2013-12-17 下午7:04:32
 *
 */
public class BoolQueryBuilder implements QueryBuilder, BoostableQueryBuilder<BoolQueryBuilder>{
	private List<QueryBuilder> mustQueryBuilders = new ArrayList<QueryBuilder>();
	private List<QueryBuilder> shouldQueryBuilders = new ArrayList<QueryBuilder>();
	private List<QueryBuilder> mustNotQueryBuilders = new ArrayList<QueryBuilder>();
	private float boost = -1;
	
	/* (non-Javadoc)
	 * @see com.qunar.vacation.search.solr.query.BoostableQueryBuilder#boost(float)
	 */
	
	public BoolQueryBuilder must(QueryBuilder queryBuilder) {
		this.mustQueryBuilders.add(queryBuilder);
		return this;
	}
	
	public BoolQueryBuilder should(QueryBuilder queryBuilder) {
		this.shouldQueryBuilders.add(queryBuilder);
		return this;
	}
	
	public BoolQueryBuilder mustNot(QueryBuilder queryBuilder) {
		this.mustNotQueryBuilders.add(queryBuilder);
		return this;
	}
	
	
	@Override
	public BoolQueryBuilder boost(float boost) {
		this.boost = boost;
		return this;
	}
	
	public boolean hasCause() {
		return !this.mustQueryBuilders.isEmpty() || !this.shouldQueryBuilders.isEmpty() || !this.mustNotQueryBuilders.isEmpty();
	}
	
	/* (non-Javadoc)
	 * @see com.qunar.vacation.search.solr.query.QueryBuilder#build()
	 */
	@Override
	public String build() {
		if (!hasCause()) {
			return StringUtils.EMPTY;
		}
		
		List<String> queries = new ArrayList<String>();
		
		String mustQuery = doBuilder(BoolOperator.AND.getOperator(), mustQueryBuilders);
		if (StringUtils.isNotEmpty(mustQuery)) {
			queries.add(mustQuery);
		}
		
		String shouldQuery = doBuilder(BoolOperator.OR.getOperator(), this.shouldQueryBuilders);
		if (StringUtils.isNotEmpty(shouldQuery)) {
			queries.add(shouldQuery);
		}
		
		String mustNotQuery =  doBuilder(BoolOperator.NOT.getOperator(), this.mustNotQueryBuilders);
		if (StringUtils.isNotEmpty(mustNotQuery)) {
			queries.add(mustNotQuery);
		}
		

		String joinQuery = StringUtils.join(queries, " ");
		
		StringBuilder queryBuilder = new StringBuilder();
		if (boost != -1) {
			queryBuilder.append("(").append(joinQuery).append(")").append("^").append(boost);
		} else {
			queryBuilder.append(joinQuery);
		}
		
		return queryBuilder.toString();
	}
	
	private String doBuilder(String operator, List<QueryBuilder> queryBuilders) {
		if (queryBuilders.isEmpty()) 
			return "";
				
		StringBuilder sb = new StringBuilder();
		
		for (QueryBuilder queryBuilder : queryBuilders) {
			sb.append(operator).append(queryBuilder.build()).append(" ");
		}
		
		return sb.substring(0, sb.length()-1);
	}
	
	public static void main(String[] args) {
		BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
		String query =  queryBuilder.must(new FieldQueryBuilder("city", "北京").boost(10))
							   		.must(new FieldQueryBuilder("star", 5))
							   		.must(new RangeQueryBuilder<Float>().field("price").from(10.2f).to(20.4f))
							   		.should(new FieldQueryBuilder("departure", "天津"))
							   		.should(new RangeQueryBuilder<Integer>().field("distance").from(0).to(3000))
							   		.mustNot(new FieldQueryBuilder("wrapper", "ctrip"))
							   		.build();
						
		System.out.println("query=" + query);
	}
}
