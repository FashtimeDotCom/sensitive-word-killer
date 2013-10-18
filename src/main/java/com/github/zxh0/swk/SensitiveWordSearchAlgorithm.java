package com.github.zxh0.swk;

import java.util.List;

/**
 * 敏感词搜索算法。
 */
public interface SensitiveWordSearchAlgorithm {
    
    /**
     * 初始化搜索算法。
     */
    public void init(List<String> sensitiveWords);
    
    /**
     * 从某个位置开始，在文本中搜索敏感词。
     * @param text 文本
     * @param offset 从哪里开始搜索
     * @return 搜索结果；或者null，如果找不到敏感词的话
     */
    public SensitiveWordSearchResult search(String text, int offset);
    
}
