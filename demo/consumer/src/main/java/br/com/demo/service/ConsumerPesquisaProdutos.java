package br.com.demo.service;


import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import br.com.demo.model.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.demo.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsumerPesquisaProdutos {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    ConsultaMercadoLivre consulta;

    public void run(String jsonConsultaFila) {
        String descricaoConsulta = JsonUtil.readJson(jsonConsultaFila, String.class);

        LOGGER.info("Consultando seguinte produto -> "+descricaoConsulta);

        try {


            List<Produto> listaRetorno = new ArrayList<>();

            listaRetorno =  consulta.consultaProdutos(descricaoConsulta);

            for (Produto produto : listaRetorno) {

                LOGGER.info("---------------------------------------");
                LOGGER.info("Descrição : " + produto.getDescricao());
                LOGGER.info("URL Imagem : " + produto.getUrlImagem());
                LOGGER.info("Valor : " + produto.getValor());
                LOGGER.info("---------------------------------------");

            }

        }  catch (FailingHttpStatusCodeException ex) {
            if (ex.getStatusCode() == 404) {
                LOGGER.warn("Sem nenhum resultados para a busca");
            } else {
                LOGGER.error("Houve algum erro na requisição" +  ex);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
