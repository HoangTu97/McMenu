package com.mcmenu.app.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.mcmenu.app.domain.Meal;
import com.mcmenu.app.repository.MealRepository;
import java.util.stream.Stream;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data Elasticsearch repository for the {@link Meal} entity.
 */
public interface MealSearchRepository extends ElasticsearchRepository<Meal, Long>, MealSearchRepositoryInternal {}

interface MealSearchRepositoryInternal {
    Stream<Meal> search(String query);

    Stream<Meal> search(Query query);

    void index(Meal entity);
}

class MealSearchRepositoryInternalImpl implements MealSearchRepositoryInternal {

    private final ElasticsearchRestTemplate elasticsearchTemplate;
    private final MealRepository repository;

    MealSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate, MealRepository repository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<Meal> search(String query) {
        //        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must(QueryBuilders.matchQuery("name", "*" + query + "*").operator(Operator.AND));
        return search(new NativeSearchQuery(queryBuilder));
    }

    @Override
    public Stream<Meal> search(Query query) {
        return elasticsearchTemplate.search(query, Meal.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(Meal entity) {
        repository.findOneWithEagerRelationships(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }
}
