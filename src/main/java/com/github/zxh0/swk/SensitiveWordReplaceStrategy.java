package com.github.zxh0.swk;

/**
 * 敏感词替换策略。
 */
public interface SensitiveWordReplaceStrategy {
    
    /**
     * 将敏感词替换。
     * @param sensitiveWord 敏感词
     * @return 替换后的词
     */
    public String replace(String sensitiveWord);
    
}
