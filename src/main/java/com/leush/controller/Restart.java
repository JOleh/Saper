package com.leush.controller;

public interface Restart {
    /**
     * Перезапускає гру з новим полем з бомбами , але зі старими властивостями поля
     */
    void restartGame();

    /**
     * Перезапускає гру з новими властивостями поля
     */
    void resize();
}
