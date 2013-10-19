package com.github.zxh0.swk.replace;

import com.github.zxh0.swk.SensitiveWordReplaceStrategy;

/**
 * 简单的敏感词替换策略，把敏感词里的每一个字都替换成另外一个字。
 */
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
        
        // 敏感词不超过10个字
        if (sensitiveWord.length() <= 10) {
            return replacements[sensitiveWord.length() - 1];
        }
        
        // 敏感词超过10个字
        return replacements[9];
    }
    
}
