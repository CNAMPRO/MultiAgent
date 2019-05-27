package CClient;

import java.io.Serializable;

public class CBase implements Serializable {
	
	public String nom;
    public int rayon = 10;
    public int nbAgents = 0;
    //ArrayList<CAgent> fourmiz;
    //Color color;

    public CBase(String message) 
    {
//        this.posX = _x;
//        this.posY = _y;
        //this.nbAgents = _nbAgents;
        //this.fourmiz = new ArrayList<CAgent>();
        //this.color = _color;
        this.nom = message;

        //for(int i = 0; i < this.nbAgents;i++)
        //{
            //this.fourmiz.add(new CAgent(this.posX, this.posY));
        //}
    }

    public void bougerAgents()
    {
        //for(int cpt = 0; cpt < this.nbAgents; cpt++)
        //{
            //this.fourmiz.get(cpt).updatePosition();
        //}
    }

    /*public void afficherBase(Graphics pG)
    {
        pG.setColor(color);
        pG.fillOval((int)this.posX, (int)this.posY, rayon, rayon);
    }*/

    /*public void afficherAgents(Graphics pG)
    {
        pG.setColor(color);
        for(int cpt = 0; cpt < this.nbAgents; cpt++)
        {
            pG.fillOval((int)this.fourmiz.get(cpt).posX,
                    (int)this.fourmiz.get(cpt).posY,
                    (int)this.fourmiz.get(cpt).SIZE,
                    (int)this.fourmiz.get(cpt).SIZE);
        }
    }*/
    
}