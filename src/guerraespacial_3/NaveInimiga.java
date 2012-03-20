package guerraespacial_3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.GameEngine;
import javaPlay.GameObject;
import javaPlay.Keyboard;
import javaPlay.Sprite;

public class NaveInimiga extends ObjetoComMovimento {

    Sprite sprite;
    int velocidade;
    int vida;

    public NaveInimiga(int x, int y, int velocidade) {

        this.vida = 100;
        this.velocidade = velocidade;
        this.x = x;
        this.y = y;
        try {            
            this.sprite = new Sprite("resources/nave_branca_mini.png", 1, 32, 32);
        } catch (Exception ex) {
            System.out.println("Imagem n�o encontrada: " + ex.getMessage());
        }
    }

    public void step(long timeElapsed){

    }

    public void draw(Graphics g) {
        if(this.estaMorto()){
            return; //N�o desenha nada;
        }
        g.setColor(Color.white);
        g.drawString(this.vida+"", this.x+5, this.y-15);
        
        this.sprite.draw(g, this.x, this.y);        
    }

    public void perdeVida(int numPontos){
        this.vida -= numPontos;
    }

    public boolean estaMorto(){
        return (this.vida <= 0);
    }

    public Rectangle getRetangulo(){
        return new Rectangle(this.x, this.y, 32, 32);
    }
    

    /**
     * Perseguir significa que o X e o Y desta nave deve sempre se aproximar
     * do x e do y do objeto perseguido.
     *
     * Para deixar o mais realista poss�vel, faremos a persegui��o tamb�m na diagonal.
     * Lembre que se o
     * x do perseguidor for Maior -> Perseguidor est� � direita
     * x do perseguidor for Menor -> Perseguidor est� � esquerda
     * y do perseguidor for Maior -> Perseguidor est� abaixo
     * y do perseguidor for Menor -> Perseguidor est� acima
     */
    public void persegue(GameObject objeto){
        int xPerseguido = objeto.getX();
        int yPerseguido = objeto.getY();

        if(this.x < xPerseguido && this.y < yPerseguido){
            //NaveMini Est� � esquerda e acima
            //Nave Perseguida est� abaixo e � direita.
            this.moveDireitaBaixo(this.velocidade);
            this.sprite.setCurrAnimFrame(4);

        } else if(this.x < xPerseguido && this.y > yPerseguido){
            //NaveMini Est� � esquerda e abaixo
            //Nave Perseguida est� acima e � direita.
            this.moveDireitaCima(this.velocidade);
            this.sprite.setCurrAnimFrame(2);

        } else if(this.x > xPerseguido && this.y < yPerseguido){
            //NaveMini Est� � direita e acima
            //Nave Perseguida est� abaixo e � esquerda.
            this.moveEsquerdaBaixo(this.velocidade);
            this.sprite.setCurrAnimFrame(6);

        } else if(this.x > xPerseguido && this.y > yPerseguido){
            //NaveMini Est� � direita e abaixo
            //Nave Perseguida est� acima e � esquerda.
            this.moveEsquerdaCima(this.velocidade);
            this.sprite.setCurrAnimFrame(8);

        } if(this.x <= xPerseguido && this.y == yPerseguido){
            //Nave Perseguida est� a direita.
            this.moveDireita(this.velocidade);
            this.sprite.setCurrAnimFrame(3);

        } else if(this.x >= xPerseguido && this.y == yPerseguido){
            //Nave Perseguida est� a esquerda.
            this.moveEsquerda(this.velocidade);
            this.sprite.setCurrAnimFrame(7);

        } else if(this.x == xPerseguido && this.y <= yPerseguido){
            //Nave Perseguida est� abaixo
            this.moveBaixo(this.velocidade);
            this.sprite.setCurrAnimFrame(5);

        } else if(this.x == xPerseguido && this.y >= yPerseguido){
            //Nave Perseguida est� acima
            this.moveCima(this.velocidade);
            this.sprite.setCurrAnimFrame(1);
        }

    }


}
