package com.github.zxh0.swk.search;

import com.github.zxh0.swk.SensitiveWordSearchAlgorithm;
import com.github.zxh0.swk.SensitiveWordSearchResult;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 用遍历法搜索敏感词，优先搜索较长的词。
 */
public class SimpleSearchAlgorithm implements SensitiveWordSearchAlgorithm {

    private String[] sensitiveWords;
    
    @Override
    public void init(List<String> sensitiveWords) {
        this.sensitiveWords = sensitiveWords.toArray(new String[0]);
        sortSensitiveWordsByWordCount();
    }
    
    // 按字数从多到少对敏感词排序
    private void sortSensitiveWordsByWordCount() {
        Arrays.sort(sensitiveWords, new Comparator<String>() {

            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s2.length(), s1.length());
            }
            
        });
    }

    @Override
    public SensitiveWordSearchResult search(String text, int startIndex) {
        for (int i = startIndex; i < text.length(); i++) {
            for (String word : sensitiveWords) {
                if (text.startsWith(word, i)) {
                    return new SensitiveWordSearchResult(word, i);
                }
            }
        }
        
        // 找不到
        return null;
    }
    
}
