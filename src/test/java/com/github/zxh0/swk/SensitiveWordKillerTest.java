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
        Assert.assertFalse(killer.check("我爱吃蛋黄派"));
    }
    
    @Test
    public void search() {
        Assert.assertEquals(Arrays.asList("草"), killer.search("草"));
        Assert.assertEquals(Arrays.asList("草"), killer.search("青草"));
        Assert.assertEquals(Arrays.asList("草"), killer.search("草地"));
        Assert.assertEquals(Arrays.asList("草"), killer.search("青草地"));
        
        Assert.assertEquals(Arrays.asList(), killer.search("我爱吃蛋黄派"));
        
        Assert.assertEquals(Arrays.asList("草", "草泥马"),
                killer.search("那边的草地上有草泥马"));
    }
    
    @Test
    public void test() {
        Assert.assertEquals("*", killer.replace("草"));
        Assert.assertEquals("青*", killer.replace("青草"));
        Assert.assertEquals("*地", killer.replace("草地"));
        Assert.assertEquals("青*地", killer.replace("青草地"));
        //Assert.assertEquals("***和***", killer.replace("草泥马和草泥马"));
    }
    
}
