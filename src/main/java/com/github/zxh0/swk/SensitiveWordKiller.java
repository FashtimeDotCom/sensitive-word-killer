package com.github.zxh0.swk;

import java.util.List;

public class SensitiveWordKiller {
    
    private final SensitiveWordSearchAlgorithm searchAlgorithm;

    public SensitiveWordKiller(SensitiveWordSearchAlgorithm searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }
    
    public boolean check(String text) {
        return false;
    }
    
    public List<String> search(String text) {
        return null;
    }
    
    public String replace(String text) {
        return text;
    }
    
}
