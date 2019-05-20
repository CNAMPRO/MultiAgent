package com.example.multiagentclient;

import android.graphics.Color;

import java.util.*;

public class CEnvironement extends Observable {
    private static CEnvironement sInstance = null;
    protected Random mRandomGen;
    protected double mWidth;
    protected double mHeight;
    public ArrayList<CBase> mBaseList;
    public ArrayList<CNourriture> mNourritureList;

    //protected int mIterCounts = 0;

    private CEnvironement() {
        mBaseList = new ArrayList<CBase>();
        mNourritureList = new ArrayList<CNourriture>();

        // Création du générateur aléatoire.
        mRandomGen = new Random();
    }

    public static CEnvironement getInstance() {
        if(sInstance == null) {
            sInstance = new CEnvironement();
        }
        return sInstance;
    }

    public void putDownNourriture(CNourriture pNourriture) {
       // pNourriture.increaseSize();
    }

    public CNourriture catchNourriture(CNourriture pNourrite) {
        /*if (pNourrite.mSize == 1) {
            pNourrite.decreaseSize();
            pNourrite.squareSize = 0;
            // TODO CNAM : 2 lignes de code Ã  ajouter ici.
            return pNourrite;
        }
        else {
            pNourrite.decreaseSize();

            // TODO CNAM : 3 lignes de code Ã  ajouter ici.
            return ;
        }*/
        return pNourrite;
    }

    public void init(int _nbBase, int _nbAgents, int x, int y, int _nbNourriture)
    {
        mWidth = x;
        mHeight = y;
        mBaseList.clear();
        for(int i = 0; i < _nbBase; i++)
        {
            mBaseList.add(new CBase(x/10.0, y/10.0, _nbAgents, Color.RED, 10));
        }

        mNourritureList.clear();
        for(int i = 0; i < _nbNourriture; i++)
        {
            mNourritureList.add(new CNourriture(x/5.0, y/5.0, 2.0));
        }
    }

}