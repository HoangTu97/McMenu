package com.mcmenu.app.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.mcmenu.app.domain.Ingredients;
import com.mcmenu.app.repository.IngredientsRepository;
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
 * Spring Data Elasticsearch repository for the {@link Ingredients} entity.
 */
public interface IngredientsSearchRepository extends ElasticsearchRepository<Ingredients, Long>, IngredientsSearchRepositoryInternal {}

interface IngredientsSearchRepositoryInternal {
    Stream<Ingredients> search(String query);

    Stream<Ingredients> search(Query query);

    void index(Ingredients entity);
}

class IngredientsSearchRepositoryInternalImpl implements IngredientsSearchRepositoryInternal {

    private final ElasticsearchRestTemplate elasticsearchTemplate;
    private final IngredientsRepository repository;

    IngredientsSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate, IngredientsRepository repository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<Ingredients> search(String query) {
        //        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must(QueryBuilders.matchQuery("name", query).operator(Operator.AND));
        return search(new NativeSearchQuery(queryBuilder));
    }

    @Override
    public Stream<Ingredients> search(Query query) {
        return elasticsearchTemplate.search(query, Ingredients.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(Ingredients entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }
}
