solr-query-builder
==================

An Apache Solr query builder DSL for writing Solr query conviently.

An Example for build a complex query:
```java
BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
		String query =  queryBuilder.must(new FieldQueryBuilder("city", "北京").boost(10))
							   		.must(new FieldQueryBuilder("star", 5))
							   		.must(new RangeQueryBuilder<Float>().field("price").from(10.2f).to(20.4f))
							   		.should(new FieldQueryBuilder("departure", "天津"))
							   		.should(new RangeQueryBuilder<Integer>().field("distance").from(0).to(3000))
							   		.mustNot(new FieldQueryBuilder("wrapperid", "ticket"))
							   		.build();
						
		System.out.println("query=" + query);
```
