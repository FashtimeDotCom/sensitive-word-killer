package com.github.zxh0.swk;

import com.github.zxh0.swk.replace.SimpleReplaceStrategy;
import com.github.zxh0.swk.search.BinarySearchAlgorithm;
import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

public class SensitiveWordKillerTest {
    
    private List<String> sensitiveWords = Arrays.asList(
            "草", "草泥", "草泥马",
            "干", "干妈", "老干妈",
            "妈", "妈蛋", "妈蛋黄派");
    
    private SensitiveWordKiller killer = new SensitiveWordKillerBuilder()
            .setSensitiveWords(sensitiveWords)
            .setSearchAlgorithm(new BinarySearchAlgorithm())
            .setReplaceStrategy(new SimpleReplaceStrategy("*"))
            .build();
    
    
    @Test
    public void check() {
        Assert.assertTrue(killer.check("草"));
        Assert.assertTrue(killer.check("青草"));
        Assert.assertTrue(killer.check("草地"));
        Assert.assertTrue(killer.check("青草地"));
        Assert.assertTrue(killer.check("那边的草地上有草泥马"));
        
        Assert.assertFalse(killer.check("我爱吃蛋黄派"));
    }
    
    @Test
    public void scan() {
        Assert.assertEquals(Arrays.asList("草"), killer.scan("草"));
        Assert.assertEquals(Arrays.asList("草"), killer.scan("青草"));
        Assert.assertEquals(Arrays.asList("草"), killer.scan("草地"));
        Assert.assertEquals(Arrays.asList("草"), killer.scan("青草地"));
        
        Assert.assertEquals(Arrays.asList("草", "草泥马"),
                killer.scan("那边的草地上有草泥马"));
        
        Assert.assertEquals(Arrays.asList(), killer.scan("我爱吃蛋黄派"));
    }
    
    @Test
    public void filter() {
        Assert.assertEquals("*", killer.filter("草"));
        Assert.assertEquals("青*", killer.filter("青草"));
        Assert.assertEquals("*地", killer.filter("草地"));
        Assert.assertEquals("青*地", killer.filter("青草地"));
        Assert.assertEquals("那边的*地上有***", killer.filter("那边的草地上有草泥马"));
    }
    
}
