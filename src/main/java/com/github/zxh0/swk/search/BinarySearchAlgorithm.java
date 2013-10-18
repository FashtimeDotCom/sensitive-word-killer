package com.github.zxh0.swk.search;

import com.github.zxh0.swk.SensitiveWordSearchAlgorithm;
import com.github.zxh0.swk.SensitiveWordSearchResult;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BinarySearchAlgorithm implements SensitiveWordSearchAlgorithm {

    private int sensitiveWordCount; // 敏感词数量
    private String[] sensitiveWords; // 敏感词数组
    private char[] firstChars; // 由敏感词第一个汉字组成的数组
    private int[] firstWordIndexes;
    
    @Override
    public void init(List<String> sensitiveWords) {
        this.sensitiveWords = sensitiveWords.toArray(new String[0]);
        sensitiveWordCount = sensitiveWords.size();
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
    
    // 提取敏感词的第一个字
    private void initFirstChars() {
        firstChars = new char[sensitiveWordCount];
        firstWordIndexes = new int[sensitiveWordCount];
        
        for (int i = 0; i < sensitiveWordCount; i++) {
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
    public SensitiveWordSearchResult search(String text, int offset) {
        for (; offset < text.length(); offset++) {
            // 文本中的某个字
            char ch = text.charAt(offset);
            
            // 看这个字是否是某个敏感词的第一个字
            int idxOfCh = Arrays.binarySearch(firstChars, ch);
            if (idxOfCh < 0) {
                continue;
            }
            
            // 这个字是某个敏感词的第一个字，找到以这个字开头的最长的那个敏感词
            int indexOfWord = firstWordIndexes[idxOfCh];
            for (; indexOfWord < sensitiveWordCount; indexOfWord++) {
                if (firstChars[indexOfWord] != ch) {
                    break;
                }
                
                String word = sensitiveWords[indexOfWord];
                if (text.startsWith(word, offset)) {
                    return new SensitiveWordSearchResult(word, offset);
                }
            }
        }
        
        return null;
    }
    
}
