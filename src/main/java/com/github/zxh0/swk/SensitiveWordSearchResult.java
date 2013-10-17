package com.github.zxh0.swk;

public class SensitiveWordSearchResult {
    
    private final String word;
    private final int index;

    public SensitiveWordSearchResult(String word, int index) {
        this.word = word;
        this.index = index;
    }
    
    public String getWord() {
        return word;
    }

    public int getIndex() {
        return index;
    }
    
}
