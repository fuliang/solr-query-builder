/**
 * 
 */
package com.github.solr.query.builder;

/**
 * @author fuliang 2013-12-17 下午8:00:08
 *
 */
public enum BoolOperator {
	AND("+"), OR(""), NOT("-");
	
	private BoolOperator(String operator) {
		this.operator = operator;
	}
	
	public String getOperator()	 {
		return this.operator;
	}
	
	private String operator;
}
