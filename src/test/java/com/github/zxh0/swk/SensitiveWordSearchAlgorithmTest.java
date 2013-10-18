package com.github.zxh0.swk;

import com.github.zxh0.swk.search.BinarySearchAlgorithm;
import com.github.zxh0.swk.search.SimpleSearchAlgorithm;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class SensitiveWordSearchAlgorithmTest {
    
    private List<String> sensitiveWords = Arrays.asList(
            "草", "草泥", "草泥马",
            "干", "干妈", "老干妈",
            "妈", "妈蛋", "妈蛋黄派");
    
    @Test
    public void simpleSearchAlgorithm() {
        SimpleSearchAlgorithm algorithm = new SimpleSearchAlgorithm();
        algorithm.init(sensitiveWords);
        
        test(algorithm);
    }
    
    @Test
    public void binarySearchAlgorithm() {
        BinarySearchAlgorithm algorithm = new BinarySearchAlgorithm();
        algorithm.init(sensitiveWords);
        
        test(algorithm);
    }
    
    private void test(SensitiveWordSearchAlgorithm algorithm) {
        oneWord(algorithm);
        twoWords(algorithm);
        longestWord(algorithm);
    }
    
    private void oneWord(SensitiveWordSearchAlgorithm algorithm) {
        Assert.assertEquals("草", algorithm.search("草", 0).getWord());
        Assert.assertEquals("草", algorithm.search("青草", 0).getWord());
        Assert.assertEquals("草", algorithm.search("草地", 0).getWord());
        Assert.assertEquals("草", algorithm.search("青草地", 0).getWord());
    }
    
    private void twoWords(SensitiveWordSearchAlgorithm algorithm) {
        Assert.assertEquals("草", algorithm.search("那边的草地上有草泥马", 0).getWord());
        Assert.assertEquals("草泥马", algorithm.search("那边的草地上有草泥马", 4).getWord());
    }
    
    private void longestWord(SensitiveWordSearchAlgorithm algorithm) {
        Assert.assertEquals("草泥", algorithm.search("草泥", 0).getWord());
        Assert.assertEquals("草泥马", algorithm.search("草泥马", 0).getWord());
        Assert.assertEquals("草泥马", algorithm.search("草泥马是一种动物", 0).getWord());
    }
    
}
