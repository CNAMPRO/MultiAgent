package com.example.multiagentclient;

public class CNourriture extends CObject {
    public double rayon;
    public double posX;
    public double posY;

    public CNourriture(double _x, double _y, double _rayon) {
        posX = _x;
        posY = _y;
        rayon = _rayon;
    }

}