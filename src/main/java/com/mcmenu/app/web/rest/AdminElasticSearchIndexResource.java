package com.mcmenu.app.web.rest;

import com.mcmenu.app.security.SecurityUtils;
import com.mcmenu.app.service.ElasticsearchIndexService;
import io.micrometer.core.annotation.Timed;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.web.util.HeaderUtil;

@RestController
@RequestMapping("/api/admin")
public class AdminElasticSearchIndexResource {

    private final Logger log = LoggerFactory.getLogger(AdminElasticSearchIndexResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ElasticsearchIndexService elasticsearchIndexService;

    public AdminElasticSearchIndexResource(ElasticsearchIndexService elasticsearchIndexService) {
        this.elasticsearchIndexService = elasticsearchIndexService;
    }

    /**
     * POST  /elasticsearch/index -> Reindex all Elasticsearch documents
     */
    @PostMapping("/elasticsearch/index")
    @Timed
    public ResponseEntity<Void> reindexAll() throws URISyntaxException {
        log.info("REST request to reindex Elasticsearch by user : {}", SecurityUtils.getCurrentUserLogin());
        elasticsearchIndexService.reindexAll();
        return ResponseEntity.accepted().headers(HeaderUtil.createAlert(applicationName, "elasticsearch.reindex.accepted", null)).build();
    }
}
