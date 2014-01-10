/**
 * 
 */
package org.github.solr.query.builder;

/**
 * @author fuliang 2014-1-2 下午4:03:43
 *
 */
public class PrefixQueryBuilder implements QueryBuilder, BoostableQueryBuilder<PrefixQueryBuilder>{
	public String field;
	public String prefix;
	public float boost = -1;
	
	public PrefixQueryBuilder(String field, String prefix) {
		this.field = field;
		this.prefix = prefix;
	}

	/* (non-Javadoc)
	 * @see com.qunar.vacation.search.solr.query.BoostableQueryBuilder#boost(float)
	 */
	@Override
	public PrefixQueryBuilder boost(float boost) {
		this.boost = boost;
		return this;
	}

	@Override
	public String build() {
		StringBuilder sb = new StringBuilder();
		sb.append(field)
				  .append(":")
				  .append(prefix)
				  .append("*");
		if (boost != -1) {
			sb.append("^").append(boost);
		}
		return sb.toString();
	}
}
