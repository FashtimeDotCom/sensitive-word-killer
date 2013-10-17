package com.github.zxh0.swk;

import java.util.ArrayList;
import java.util.List;

public class SensitiveWordKiller {
    
    private final SensitiveWordSearchAlgorithm searchAlgorithm;
    private final SensitiveWordReplaceStrategy replaceStrategy;

    public SensitiveWordKiller(SensitiveWordSearchAlgorithm searchAlgorithm,
            SensitiveWordReplaceStrategy replaceStrategy) {
        
        this.searchAlgorithm = searchAlgorithm;
        this.replaceStrategy = replaceStrategy;
    }
    
    public boolean check(String text) {
        return searchAlgorithm.search(text, 0) != null;
    }
    
    public List<String> search(String text) {
        List<String> words = new ArrayList<>();
        
        String word;
        int index = 0;
        while ((word = searchAlgorithm.search(text, index)) != null) {
            index += word.length();
            words.add(word);
        }
        
        return words;
    }
    
    public String replace(String text) {
        return text;
    }
    
}
