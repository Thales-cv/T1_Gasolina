package com.pucrs.centro_distribuicao;

import java.util.function.Function;

public class CentroDistribuicao {
    public enum SITUACAO {NORMAL, SOBRAVISO, EMERGENCIA}

    public enum TIPOPOSTO {COMUM, ESTRATEGICO}

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;

    private int tAditivito;
    private int tGasolina;
    private int tAlcool1;
    private int tAlcool2;
    private SITUACAO currSituacao;

    public CentroDistribuicao(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        if (tAditivo < 0 || tAditivo > MAX_ADITIVO) {
            throw new IllegalArgumentException("Quantidade de Aditivo invalida.");
        }
        if (tGasolina < 0 || tGasolina > MAX_GASOLINA) {
            throw new IllegalArgumentException("Quantidade de Gasolina invalida.");
        }
        if (tAlcool1 < 0 || tAlcool1 > MAX_ALCOOL / 2) {
            throw new IllegalArgumentException("Quantidade de Alcool no Tanque 1 invalida.");
        }
        if (tAlcool2 < 0 || tAlcool2 > MAX_ALCOOL / 2) {
            throw new IllegalArgumentException("Quantidade de Alcool no Tanque 2 invalida.");
        }
        if (tAlcool1 != tAlcool2) {
            throw new IllegalArgumentException("Quantidades de Alcool no Tanque 1 e 2 devem ser iguais.");
        }
        this.tAditivito = tAditivo;
        this.tGasolina = tGasolina;
        this.tAlcool1 = tAlcool1;
        this.tAlcool2 = tAlcool2;
        defineSituacao();
    }

    public void defineSituacao() {
        SITUACAO situacao = SITUACAO.NORMAL;
        if (MAX_ADITIVO > 0) {
            double pctAditivo = (double) tAditivito / MAX_ADITIVO * 100;
            if (pctAditivo < 25) {
                situacao = SITUACAO.EMERGENCIA;
            } else if (pctAditivo < 50) {
                situacao = SITUACAO.SOBRAVISO;
            }
        }
        if (MAX_GASOLINA > 0) {
            double pctGasolina = (double) tGasolina / MAX_GASOLINA * 100;
            if (pctGasolina < 25) {
                situacao = SITUACAO.EMERGENCIA;
            } else if (pctGasolina < 50) {
                situacao = situacao == SITUACAO.EMERGENCIA ? SITUACAO.EMERGENCIA : SITUACAO.SOBRAVISO;
            }
        }
        if (MAX_ALCOOL > 0) {
            double pctAlcool = (double) (tAlcool1 + tAlcool2) / MAX_ALCOOL * 100;
            if (pctAlcool < 25) {
                situacao = SITUACAO.EMERGENCIA;
            } else if (pctAlcool < 50) {
                situacao = situacao == SITUACAO.EMERGENCIA ? SITUACAO.EMERGENCIA : SITUACAO.SOBRAVISO;
            }
        }
        currSituacao = situacao;
    }

    public SITUACAO getSituacao() {
        return this.currSituacao;
    }

    public int gettGasolina() {
        return this.tGasolina;
    }

    public int gettAditivo() {
        return this.tAditivito;
    }

    public int gettAlcool1() {
        return this.tAlcool1;
    }

    public int gettAlcool2() {
        return this.tAlcool2;
    }

    public int recebeAditivo(int qtdade) {
        if (qtdade < 0) {
            return -1;
        }
        int disponivel = MAX_ADITIVO - tAditivito;
        int abastecido = Math.min(qtdade, disponivel);
        tAditivito += abastecido;
        defineSituacao();
        return abastecido;
    }

    public int recebeGasolina(int qtdade) {
        if (qtdade < 0) {
            return -1;
        }
        int disponivel = MAX_GASOLINA - tGasolina;
        int abastecido = Math.min(qtdade, disponivel);
        tGasolina += abastecido;
        defineSituacao();
        return abastecido;
    }

    public int recebeAlcool(int qtdade) {
        if (qtdade < 0) {
            return -1;
        }
        int disponivel = MAX_ALCOOL - (tAlcool1 + tAlcool2);
        int abastecido = Math.min(qtdade, disponivel);
        if (abastecido % 2 != 0) {
            abastecido -= 1;
        }
        tAlcool1 += abastecido / 2;
        tAlcool2 += abastecido / 2;
        defineSituacao();
        return abastecido;
    }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        if (getSituacao() == SITUACAO.EMERGENCIA && tipoPosto == TIPOPOSTO.COMUM) {
            return new int[]{-14};
        }
        boolean reduzir = getSituacao() == SITUACAO.SOBRAVISO && tipoPosto == TIPOPOSTO.COMUM || getSituacao() == SITUACAO.EMERGENCIA;
        Function<Double, Integer> calcQtd = (n) -> (int) (n * qtdade * (reduzir ? 0.5 : 1.0));
        int reqAditivo = calcQtd.apply(0.05);
        if (reqAditivo > tAditivito) {
            return new int[]{-21};
        }
        int reqGasolina = calcQtd.apply(0.7);
        if (reqGasolina > tGasolina) {
            return new int[]{-21};
        }
        int reqAlcool = calcQtd.apply(0.25);
        if (reqAlcool % 2 != 0) {
            reqAlcool -= 1;
        }
        if (reqAlcool > tAlcool1 + tAlcool2) {
            return new int[]{-21};
        }
        tGasolina -= reqGasolina;
        tAditivito -= reqAditivo;
        tAlcool1 -= reqAlcool/2;
        tAlcool2 -= reqAlcool/2;

        defineSituacao();
        return new int[]{tAditivito, tGasolina, tAlcool1, tAlcool2};
    }
}
