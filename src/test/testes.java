import org.junit.Test;
import static org.junit.Assert.*;

import org.example.CentroDistribuicao;
import org.example.CentroDistribuicao.TIPOPOSTO;

public class testes{
    // INICIALIZA UM CD
    public CentroDistribuicao inicializa(){
        CentroDistribuicao cd = new CentroDistribuicao(500, 10000, 1250, 1250);
        return cd;
    }

    // Testes de mistura (TESTE PARAMETRO)
    //1. Retirada com combustível suficiente
    @Test
    public void testRetiradaCombustivel(){
        testes teste = new testes();
        CentroDistribuicao cd = inicializa();
        int[] resposta = cd.encomendaCombustivel(100000, TIPOPOSTO.COMUM);
        int[] 
        assert.equals(,resposta)

    }
    //2. Retirada com combustível insuficiente
    //3. Retirada com combustível suficiente e valores finais do tanque atualizdos

    // Testes de Estado (TESTE VALOR LIMITE)
    //1. Combustível acima de 50% -> estado NORMAL
    //2. Combustível menor que 50% e maior que 25% -> estado SOBREAVISO
    //3. Combusítvel menor de 25% -> estado EMERGÊNCIA

    // Testes de Pedidos (TESTE BASEADOS EM MODELOS)
    //1. Quando tipo do posto for COMUM & ESTADOS
    //2. Quando tipo de posto for ESTRATÉGICO & ESTADOS

}

