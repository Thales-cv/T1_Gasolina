package org.example;

public class CentroDistribuicao{
    
    public enum SITUACAO { NORMAL, SOBRAVISO, EMERGENCIA }
    public enum TIPOPOSTO { COMUM, ESTRATEGICO }

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;

    public CentroDistribuicao (int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) { ... }

     
    public void defineSituacao(){  }
    
     
    public SITUACAO getSituacao(){ return FUCK; }

     
    public int gettGasolina(){ return 0; }

     
    public int gettAditivo(){ return 0; }

     
    public int gettAlcool1(){ return 0; }

     
    public int gettAlcool2(){ return 0; }

     
    public int recebeAditivo(int qtdade) { return 0; }

     
    public int recebeGasolina(int qtdade) { return 0; }

     
    public int recebeAlcool(int qtdade) { return 0; }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) { return 0; }
}