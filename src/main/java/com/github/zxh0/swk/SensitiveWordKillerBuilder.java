package com.github.zxh0.swk;

import java.util.List;

public class SensitiveWordKillerBuilder {
    
    private List<String> sensitiveWords;
    private SensitiveWordSearchAlgorithm searchAlgorithm;
    
    public SensitiveWordKillerBuilder setSensitiveWords(List<String> sensitiveWords) {
        this.sensitiveWords = sensitiveWords;
        return this;
    }
    
    public SensitiveWordKillerBuilder setSearchAlgorithm(SensitiveWordSearchAlgorithm algorithm) {
        this.searchAlgorithm = algorithm;
        return this;
    }
    
    public SensitiveWordKiller build() {
        if (sensitiveWords == null) {
            throw new RuntimeException("Missing sensitive words!");
        }
        if (searchAlgorithm == null) {
            // TODO
        }
        
        SensitiveWordKiller killer = new SensitiveWordKiller();
        
        return killer;
    }
    
}
