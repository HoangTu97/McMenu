package com.mcmenu.app.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.mcmenu.app.domain.NutritionSummary;
import com.mcmenu.app.repository.NutritionSummaryRepository;
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
 * Spring Data Elasticsearch repository for the {@link NutritionSummary} entity.
 */
public interface NutritionSummarySearchRepository
    extends ElasticsearchRepository<NutritionSummary, Long>, NutritionSummarySearchRepositoryInternal {}

interface NutritionSummarySearchRepositoryInternal {
    Stream<NutritionSummary> search(String query);

    Stream<NutritionSummary> search(Query query);

    void index(NutritionSummary entity);
}

class NutritionSummarySearchRepositoryInternalImpl implements NutritionSummarySearchRepositoryInternal {

    private final ElasticsearchRestTemplate elasticsearchTemplate;
    private final NutritionSummaryRepository repository;

    NutritionSummarySearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate, NutritionSummaryRepository repository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<NutritionSummary> search(String query) {
        //        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must(QueryBuilders.matchQuery("name", query).operator(Operator.AND));
        return search(new NativeSearchQuery(queryBuilder));
    }

    @Override
    public Stream<NutritionSummary> search(Query query) {
        return elasticsearchTemplate.search(query, NutritionSummary.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(NutritionSummary entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }
}
