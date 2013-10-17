package com.github.zxh0.swk;

import com.github.zxh0.swk.replace.SimpleReplaceStrategy;
import org.junit.Assert;
import org.junit.Test;

public class SensitiveWordReplaceStrategyTest {
    
    @Test
    public void simpleReplaceStrategy() {
        SimpleReplaceStrategy strategy = new SimpleReplaceStrategy("*");
        Assert.assertEquals("**", strategy.replace("妈蛋"));
        Assert.assertEquals("*****", strategy.replace("妈的巴子的"));
    }
    
}
