package com.app.test1;


public interface IntFunctional {
	public abstract int breed();
    // line n2
    public default Object callPet(){
        return this;
    }
    // line n3
    void speak();
}
