package com.github.zxh0.swk.search;

import com.github.zxh0.swk.SensitiveWordSearchAlgorithm;
import com.github.zxh0.swk.SensitiveWordSearchResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BinarySearchAlgorithm implements SensitiveWordSearchAlgorithm {

    private List<String> sensitiveWords;
    private char[] firstChars;
    private int[] firstWordIndexes;
    
    @Override
    public void init(List<String> sensitiveWords) {
        this.sensitiveWords = new ArrayList<>(sensitiveWords);
        initSensitiveWords();
        initFirstChars();
    }
    
    private void initSensitiveWords() {
        Collections.sort(sensitiveWords, new Comparator<String>() {
            
            @Override
            public int compare(String s1, String s2) {
                if (s1.length() == s2.length()) {
                    return Character.compare(s1.charAt(0), s2.charAt(0));
                } else {
                    return Integer.compare(s2.length(), s1.length());
                }
            }
            
        });
    }
    
    private void initFirstChars() {
        final int wordCount = sensitiveWords.size();
        firstChars = new char[wordCount];
        firstWordIndexes = new int[wordCount];
        
        for (int i = 0; i < wordCount; i++) {
            final char firstChar = sensitiveWords.get(i).charAt(0);
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
                        String word = sensitiveWords.get(j);
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
