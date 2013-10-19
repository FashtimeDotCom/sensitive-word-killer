package com.github.zxh0.swk;

import com.github.zxh0.swk.replace.SimpleReplaceStrategy;
import com.github.zxh0.swk.search.BinarySearchAlgorithm;
import java.util.List;

public class SensitiveWordKillerBuilder {
    
    private List<String> sensitiveWords;
    private SensitiveWordSearchAlgorithm searchAlgorithm;
    private SensitiveWordReplaceStrategy replaceStrategy;
    
    public SensitiveWordKillerBuilder setSensitiveWords(List<String> sensitiveWords) {
        this.sensitiveWords = sensitiveWords;
        return this;
    }
    
    public SensitiveWordKillerBuilder setSearchAlgorithm(SensitiveWordSearchAlgorithm algorithm) {
        this.searchAlgorithm = algorithm;
        return this;
    }

    public SensitiveWordKillerBuilder setReplaceStrategy(SensitiveWordReplaceStrategy replaceStrategy) {
        this.replaceStrategy = replaceStrategy;
        return this;
    }
    
    public SensitiveWordKiller build() {
        if (sensitiveWords == null) {
            throw new RuntimeException("Missing sensitive words!");
        }
        
        if (searchAlgorithm == null) {
            searchAlgorithm = new BinarySearchAlgorithm();
        }
        
        if (replaceStrategy == null) {
            replaceStrategy = new SimpleReplaceStrategy('*');
        }
        
        searchAlgorithm.init(sensitiveWords);
        
        return new SensitiveWordKiller(searchAlgorithm, replaceStrategy);
    }
    
}
