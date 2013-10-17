package com.github.zxh0.swk;

import java.util.List;

public interface SensitiveWordSearchAlgorithm {
    
    public void init(List<String>  sensitiveWords);
    
    public String search(String text, int startIndex);
    
}
