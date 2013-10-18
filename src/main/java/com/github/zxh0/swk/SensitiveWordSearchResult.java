package com.github.zxh0.swk;

/**
 * 敏感词搜索结果。
 */
public class SensitiveWordSearchResult {
    
    private final String word;
    private final int offset;

    public SensitiveWordSearchResult(String word, int offset) {
        this.word = word;
        this.offset = offset;
    }
    
    /**
     * @return 搜索到的敏感词
     */
    public String getWord() {
        return word;
    }

    /**
     * @return 敏感词在文本中的位置
     */
    public int getOffset() {
        return offset;
    }
    
}
