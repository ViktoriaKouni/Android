package com.example.corona.api;

import androidx.test.espresso.idling.CountingIdlingResource;

public class ResourceIdlng {

    private  static ResourceIdlng instance;
    private final String res ="GLOBAL";
    public final CountingIdlingResource countingIdlingResource  = new CountingIdlingResource(res);
    private ResourceIdlng(){}

    public static ResourceIdlng getInstance(){
        if(instance==null)
            instance =new ResourceIdlng();
        return instance;
    }

    public void increment(){
        countingIdlingResource.increment();
    }
    public void decrement(){
        countingIdlingResource.decrement();
    }
}
