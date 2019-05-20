package com.example.multiagentclient;

public class CObject {
    public double posX;
    public double posY;

    public CObject() {};
    public CObject(double _x, double _y) {
        posX = _x;
        posY = _y;
    }

    public double distance(CObject o) {
        return Math.sqrt((o.posX - posX) * (o.posX - posX) + (o.posY - posY) * (o.posY - posY));
    }

    public double DistanceCarre(CObject o) {
        return (o.posX - posX) * (o.posX - posX) + (o.posY - posY) * (o.posY - posY);
    }

    // TODO CNAM : une dizaine de lignes de code à ajouter ici.
}