package com.vmos.components;

public interface IComponent {
    public boolean isPresent();

    public void waitForComponentToLoad(int timeoutInSeconds);

    public void waitForComponentToDisappear(int timeoutInSeconds);
}
