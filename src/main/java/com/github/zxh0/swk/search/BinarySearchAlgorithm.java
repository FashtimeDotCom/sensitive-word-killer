package com.github.zxh0.swk.search;

import com.github.zxh0.swk.SensitiveWordSearchAlgorithm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearchAlgorithm implements SensitiveWordSearchAlgorithm {

    private List<String> sensitiveWords;
    private char[] firstChars;
    private int[] wordIndexes;
    
    @Override
    public void init(List<String> sensitiveWords) {
        this.sensitiveWords = new ArrayList<>(sensitiveWords);
        initSensitiveWords();
        initFirstChars();
    }
    
    private void initSensitiveWords() {
        Collections.sort(sensitiveWords);
    }
    
    private void initFirstChars() {
        final int wordCount = sensitiveWords.size();
        firstChars = new char[wordCount];
        wordIndexes = new int[wordCount];
        
        for (int i = wordCount - 1; i >= 0; i--) {
            final char firstChar = sensitiveWords.get(i).charAt(0);
            firstChars[i] = firstChar;
            if (i == wordCount - 1) {
                wordIndexes[i] = i;
            } else {
                if (firstChar == firstChars[i + 1]) {
                    wordIndexes[i] = wordIndexes[i + 1];
                }
            }
        }
    }
    

    @Override
    public String search(String text, int startIndex) {
        for (int i = startIndex; i < text.length(); i++) {
            char ch = text.charAt(i);
            int idx = Arrays.binarySearch(firstChars, ch);
            if (idx > 0) {
                for (int j = wordIndexes[idx]; j >= 0; j--) {
                    if (firstChars[j] == ch) {
                        String word = sensitiveWords.get(j);
                        if (text.indexOf(word, startIndex) >= 0) {
                            return word;
                        }
                    }
                }
            }
        }
        
        return null;
    }
    
}
