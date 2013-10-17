package com.github.zxh0.swk;

import com.github.zxh0.swk.search.BinarySearchAlgorithm;
import com.github.zxh0.swk.search.SimpleSearchAlgorithm;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class SensitiveWordSearchAlgorithmTest {
    
    private List<String> sensitiveWords = Arrays.asList("妈蛋", "他妈的", "妈的", "他妈的巴子的");
    
    @Test
    public void simpleSearchAlgorithm() {
        SensitiveWordSearchAlgorithm algorithm = new SimpleSearchAlgorithm();
        algorithm.init(sensitiveWords);
        
        Assert.assertEquals("他妈的巴子的", algorithm.search("他妈的巴子的", 0).getWord());
        Assert.assertEquals("他妈的巴子的", algorithm.search("去他妈的巴子的！", 0).getWord());
        Assert.assertEquals("他妈的", algorithm.search("他妈的", 0).getWord());
        Assert.assertEquals("他妈的", algorithm.search("去他妈的", 0).getWord());
        Assert.assertEquals("妈的", algorithm.search("妈的", 0).getWord());
        Assert.assertEquals(null, algorithm.search("大姨妈", 0));
    }
    
    @Test
    public void binarySearchAlgorithm() {
        SensitiveWordSearchAlgorithm algorithm = new BinarySearchAlgorithm();
        algorithm.init(sensitiveWords);
        
        Assert.assertEquals("他妈的巴子的", algorithm.search("他妈的巴子的", 0).getWord());
        Assert.assertEquals("他妈的巴子的", algorithm.search("去他妈的巴子的！", 0).getWord());
        Assert.assertEquals("他妈的", algorithm.search("他妈的", 0).getWord());
        Assert.assertEquals("他妈的", algorithm.search("去他妈的", 0).getWord());
        Assert.assertEquals("妈的", algorithm.search("妈的", 0).getWord());
        Assert.assertEquals(null, algorithm.search("大姨妈", 0));
    }
    
}
