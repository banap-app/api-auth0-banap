package com.auth.application;

public class Result <T,U>{

    private T firstValue;
    private U secondValue;

    public Result(T firstValue, U secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public T getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(T firstValue) {
        this.firstValue = firstValue;
    }

    @SuppressWarnings("unchecked")
    public <V extends U> V getSecondValue() {
        return (V) secondValue;
    }
    public void setSecondValue(U secondValue) {
        this.secondValue = secondValue;
    }
}
