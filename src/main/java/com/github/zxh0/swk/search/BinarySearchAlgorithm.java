package com.github.zxh0.swk.search;

import com.github.zxh0.swk.SensitiveWordSearchAlgorithm;
import com.github.zxh0.swk.SensitiveWordSearchResult;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BinarySearchAlgorithm implements SensitiveWordSearchAlgorithm {

    private String[] sensitiveWords; // 敏感词数组
    private char[] firstChars; // 由敏感词第一个汉字组成的数组
    private int[] firstWordIndexes;
    
    @Override
    public void init(List<String> sensitiveWords) {
        this.sensitiveWords = sensitiveWords.toArray(new String[0]);
        sortSensitiveWords();
        initFirstChars();
    }
    
    // 对敏感词数组排序
    private void sortSensitiveWords() {
        Arrays.sort(sensitiveWords, new Comparator<String>() {
            
            @Override
            public int compare(String s1, String s2) {
                if (s1.charAt(0) == s2.charAt(0)) {
                    return Integer.compare(s2.length(), s1.length());
                } else {
                    return Character.compare(s1.charAt(0), s2.charAt(0));
                }
            }
            
        });
    }
    
    private void initFirstChars() {
        final int wordCount = sensitiveWords.length;
        firstChars = new char[wordCount];
        firstWordIndexes = new int[wordCount];
        
        for (int i = 0; i < wordCount; i++) {
            final char firstChar = sensitiveWords[i].charAt(0);
            firstChars[i] = firstChar;
            if (i == 0 || firstChar != firstChars[i - 1]) {
                firstWordIndexes[i] = i;
            } else {
                firstWordIndexes[i] = firstWordIndexes[i - 1];
            }
        }
    }

    @Override
    public SensitiveWordSearchResult search(String text, int startIndex) {
        for (int i = startIndex; i < text.length(); i++) {
            char ch = text.charAt(i);
            int idxOfFirstChars = Arrays.binarySearch(firstChars, ch);
            if (idxOfFirstChars > 0) {
                for (int j = firstWordIndexes[idxOfFirstChars]; j < firstChars.length; j++) {
                    if (firstChars[j] == ch) {
                        String word = sensitiveWords[j];
                        int idx = text.indexOf(word, startIndex);
                        if (idx >= 0) {
                            return new SensitiveWordSearchResult(word, idx);
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        
        return null;
    }
    
}
