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
        
        SensitiveWordSearchResult result;
        int index = 0;
        while ((result = searchAlgorithm.search(text, index)) != null) {
            String word = result.getWord();
            index = result.getIndex() + word.length();
            words.add(word);
        }
        
        return words;
    }
    
    public String replace(String text) {
        SensitiveWordSearchResult result = searchAlgorithm.search(text, 0);
        if (result == null) {
           return text; 
        }
        
        int index = result.getIndex();
        String word = result.getWord();
        
        StringBuilder buf = new StringBuilder();
        buf.append(text.substring(0, index));
        buf.append(replaceStrategy.replace(word));
        index += word.length();
        
        while ((result = searchAlgorithm.search(text, index)) != null) {
            //index = result.getIndex();
            buf.append(text.substring(index, result.getIndex()));
            word = result.getWord();
            buf.append(word);
            
            index = result.getIndex() + word.length();
        }
        
        buf.append(text.substring(index));
        return buf.toString();
    }
    
}
