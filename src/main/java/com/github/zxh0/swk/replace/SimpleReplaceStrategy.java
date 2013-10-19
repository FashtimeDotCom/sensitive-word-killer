package com.github.zxh0.swk.replace;

import com.github.zxh0.swk.SensitiveWordReplaceStrategy;

/**
 * 简单的敏感词替换策略，把敏感词里的每一个字都替换成另外一个字。
 */
public class SimpleReplaceStrategy implements SensitiveWordReplaceStrategy {
    
    // 假设大部分敏感词不超过10个字
    private static final int MAX_CHAR_COUNT = 10;
    
    // 预先计算好的字符串
    private String[] replacements;

    public SimpleReplaceStrategy(final char replacement) {
        replacements = new String[MAX_CHAR_COUNT + 1];
        replacements[0] = "";
        for (int i = 1; i < replacements.length; i++) {
            replacements[i] = replacements[i - 1] + replacement;
        }
    }

    @Override
    public String replace(String sensitiveWord) {
        if (sensitiveWord.isEmpty()) {
            return sensitiveWord;
        }
        
        // 敏感词字数不超过MAX_CHAR_COUNT
        if (sensitiveWord.length() < MAX_CHAR_COUNT) {
            return replacements[sensitiveWord.length()];
        }
        
        // 敏感词字数超过MAX_CHAR_COUNT
        return replacements[MAX_CHAR_COUNT];
    }
    
}
