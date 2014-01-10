/**
 * 
 */
package org.github.solr.query.builder;

/**
 * @author fuliang 2013-12-17 下午8:09:24
 *
 */
public class FieldQueryBuilder implements QueryBuilder, BoostableQueryBuilder<FieldQueryBuilder>{
	private String field;
	private Object query;
	private float boost = -1;
	/* (non-Javadoc)
	 * @see com.qunar.vacation.search.solr.query.BoostableQueryBuilder#boost(float)
	 */
	
	public FieldQueryBuilder(String field, String query) {
		this(field, (Object)query);
	}
	
	public FieldQueryBuilder(String field, int query) {
		this(field, (Object)query);
	}
	
	public FieldQueryBuilder(String field, long query) {
		this(field, (Object) query);
	}
	
	public FieldQueryBuilder(String field, float query) {
		this(field, (Object)query);
	}
	
	public FieldQueryBuilder(String field, double query) {
		this(field, (Object)query);
	}
	
	public FieldQueryBuilder(String field, boolean query) {
		this(field, (Object)query);
	}
	
	public FieldQueryBuilder(String field, Object query) {
		this.field = field;
		this.query = query;
	}
	
	@Override
	public FieldQueryBuilder boost(float boost) {
		this.boost = boost;
		return this;
	}

	@Override
	public String build() {
		StringBuilder sb = new StringBuilder();
		sb.append(field).append(":").append(query.toString());
		if(boost != -1) {
			sb.append("^").append(boost);
		}
		return sb.toString();
	}

}
