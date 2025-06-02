package com.cao.caoaiagent.rag;

import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 向量转换和存储(基于内存存储)
 */
@Configuration
public class LoveAppVectorStoreConfig {

    private final LoveAppDocumentLoader loveAppDocumentLoader;

    @Resource
    private MyTokenTextSplitter myTokenTextSplitter;

    @Resource
    private MyKeywordEnricher myKeywordEnricher;

    public LoveAppVectorStoreConfig(LoveAppDocumentLoader loveAppDocumentLoader) {
        this.loveAppDocumentLoader = loveAppDocumentLoader;
    }

    @Bean
    VectorStore loveAppVecStore(EmbeddingModel dashscopeEmbeddingModel){
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(dashscopeEmbeddingModel).build();
        // 加载文档
        List<Document> documents = loveAppDocumentLoader.loadDocuments();
        // 使用分词器对文档进行分词
        List<Document> splitDocuments = myTokenTextSplitter.splitCustomized(documents);
        // 自动补充关键词元数据
        List<Document> enrichedDocuments = myKeywordEnricher.enrichDocuments(splitDocuments);
        simpleVectorStore.add(enrichedDocuments);
        return simpleVectorStore;
    }
}
