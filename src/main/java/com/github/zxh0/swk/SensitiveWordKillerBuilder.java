package com.github.zxh0.swk;

public class SensitiveWordKillerBuilder {
    
    private SensitiveWordKiller killer = new SensitiveWordKiller();
    
    public SensitiveWordKillerBuilder setSensitiveWords() {
        return this;
    }
    
    public SensitiveWordKiller build() {
        return killer;
    }
    
}
