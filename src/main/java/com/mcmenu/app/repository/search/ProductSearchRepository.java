package com.mcmenu.app.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.mcmenu.app.domain.Product;
import com.mcmenu.app.repository.ProductRepository;
import java.util.stream.Stream;
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
 * Spring Data Elasticsearch repository for the {@link Product} entity.
 */
public interface ProductSearchRepository extends ElasticsearchRepository<Product, Long>, ProductSearchRepositoryInternal {}

interface ProductSearchRepositoryInternal {
    Stream<Product> search(String query);

    Stream<Product> search(Query query);

    void index(Product entity);
}

class ProductSearchRepositoryInternalImpl implements ProductSearchRepositoryInternal {

    private final ElasticsearchRestTemplate elasticsearchTemplate;
    private final ProductRepository repository;

    ProductSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate, ProductRepository repository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<Product> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Stream<Product> search(Query query) {
        return elasticsearchTemplate.search(query, Product.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(Product entity) {
        repository.findOneWithEagerRelationships(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }
}
