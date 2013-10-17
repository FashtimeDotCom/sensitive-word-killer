package com.github.zxh0.swk.replace;

import com.github.zxh0.swk.SensitiveWordReplaceStrategy;

public class SimpleReplaceStrategy implements SensitiveWordReplaceStrategy {
    
    private String[] replacements;

    public SimpleReplaceStrategy(String replacement) {
        replacements = new String[10];
        replacements[0] = replacement;
        for (int i = 1; i < 10; i++) {
            replacements[i] = replacements[i - 1] + replacement;
        }
    }

    @Override
    public String replace(String sensitiveWord) {
        if (sensitiveWord.isEmpty()) {
            return sensitiveWord;
        }
        
        if (sensitiveWord.length() <= 10) {
            return replacements[sensitiveWord.length() - 1];
        }
        
        return replacements[10];
    }
    
}
