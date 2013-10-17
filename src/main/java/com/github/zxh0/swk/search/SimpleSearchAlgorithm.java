package com.github.zxh0.swk.search;

import com.github.zxh0.swk.SensitiveWordSearchAlgorithm;
import com.github.zxh0.swk.SensitiveWordSearchResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SimpleSearchAlgorithm implements SensitiveWordSearchAlgorithm {

    private List<String> sensitiveWords;
    
    @Override
    public void init(List<String> sensitiveWords) {
        this.sensitiveWords = new ArrayList<>(sensitiveWords);
        sortSensitiveWordsByLength();
    }
    
    private void sortSensitiveWordsByLength() {
        Collections.sort(sensitiveWords, new Comparator<String>() {

            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s2.length(), s1.length());
            }
            
        });
    }

    @Override
    public SensitiveWordSearchResult search(String text, int startIndex) {
        for (String word : sensitiveWords) {
            int idx = text.indexOf(word, startIndex);
            if (idx >= 0) {
                return new SensitiveWordSearchResult(word, idx);
            }
        }
        
        return null;
    }
    
}
