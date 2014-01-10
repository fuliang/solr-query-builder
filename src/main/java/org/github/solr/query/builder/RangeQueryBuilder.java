/**
 * 
 */
package org.github.solr.query.builder;

/**
 * @author fuliang 2013-12-18 上午11:20:24
 *
 */
public class RangeQueryBuilder<T> implements QueryBuilder, BoostableQueryBuilder<RangeQueryBuilder<T>>{
	private String field;
	private T from;
	private T to;
	private float boost = -1;
	
	public RangeQueryBuilder<T> field(String field) {
		this.field = field;
		return this;
	}
	/* (non-Javadoc)
	 * @see com.qunar.vacation.search.solr.query.BoostableQueryBuilder#boost(float)
	 */
	@Override
	public RangeQueryBuilder<T> boost(float boost) {
		this.boost = boost;
		return this;
	}
	
	public RangeQueryBuilder<T> from(T from) {
		this.from = from;
		return this;
	}
	
	public RangeQueryBuilder<T> to(T to) {
		this.to = to;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.qunar.vacation.search.solr.query.QueryBuilder#build(com.qunar.vacation.search.solr.query.QueryBuilder.Params)
	 */
	@Override
	public String build() {
		StringBuilder sb = new StringBuilder();
		sb.append(field).append(":")
		  .append("[").append(from)
		  .append(" TO ").append(to)
		  .append("]");
		if (boost != -1) {
			sb.append("^").append(boost);
		}
		return sb.toString();
	}
	
}
