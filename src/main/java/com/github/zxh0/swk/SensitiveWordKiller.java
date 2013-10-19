package com.github.zxh0.swk;

import java.util.ArrayList;
import java.util.List;

/**
 * 用来搜索和过滤敏感词。
 */
public class SensitiveWordKiller {
    
    private final SensitiveWordSearchAlgorithm searchAlgorithm;
    private final SensitiveWordReplaceStrategy replaceStrategy;

    public SensitiveWordKiller(SensitiveWordSearchAlgorithm searchAlgorithm,
            SensitiveWordReplaceStrategy replaceStrategy) {
        
        this.searchAlgorithm = searchAlgorithm;
        this.replaceStrategy = replaceStrategy;
    }
    
    /**
     * 检查文本里是否包含敏感词。
     * @param text 文本
     * @return 如果文字包含敏感词返回true，否则返回false
     */
    public boolean check(String text) {
        return searchAlgorithm.search(text, 0) != null;
    }
    
    /**
     * 扫描文本，找出所有敏感词。
     * @param text 文本
     * @return 所有找到的敏感词
     */
    public List<String> scan(String text) {
        int offset = 0;
        SensitiveWordSearchResult result;
        List<String> words = new ArrayList<>();
        
        while ((result = searchAlgorithm.search(text, offset)) != null) {
            String word = result.getWord();
            offset = result.getOffset() + word.length();
            words.add(word);
        }
        
        return words;
    }
    
    /**
     * 将文本中的敏感词按替换规则替换掉。
     * @param text 文本
     * @return 替换掉敏感词的文本
     */
    public String filter(String text) {
        // 找出第一个敏感词
        SensitiveWordSearchResult result = searchAlgorithm.search(text, 0);
        if (result == null) {
           // 文本不包含敏感词
           return text; 
        }
        
        int offset = result.getOffset();
        String word = result.getWord();
        
        StringBuilder buf = new StringBuilder();
        buf.append(text.substring(0, offset));
        buf.append(replaceStrategy.replace(word));
        offset += word.length();
        
        // 继续找敏感词
        while ((result = searchAlgorithm.search(text, offset)) != null) {
            //index = result.getIndex();
            buf.append(text.substring(offset, result.getOffset()));
            word = result.getWord();
            buf.append(replaceStrategy.replace(word));
            
            offset = result.getOffset() + word.length();
        }
        
        buf.append(text.substring(offset));
        return buf.toString();
    }
    
}
