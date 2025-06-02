package com.cao.caoaiagent.rag;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyTokenTextSplitter {

    public List<Document> splitDocuments(List<Document> documents) {
        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
        return tokenTextSplitter.split(documents);
    }

    public List<Document> splitCustomized(List<Document> documents) {
        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter(200, 100, 10, 5000, true);
        return tokenTextSplitter.split(documents);
    }

}
