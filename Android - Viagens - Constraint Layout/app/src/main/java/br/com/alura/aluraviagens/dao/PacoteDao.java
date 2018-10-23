package br.com.alura.aluraviagens.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.aluraviagens.model.Pacote;

public class PacoteDao {

    public List<Pacote> lista() {
        List<Pacote> pacotes = new ArrayList<>(Arrays.asList(
                new Pacote("São Paulo", "sao_paulo_sp", 2, 243.99),
                new Pacote("Belo Horizonte", "belo_horizonte_mg", 3, 421.50),
                new Pacote("Recife", "recife_pe", 4,754.20),
                new Pacote("Rio de Janeiro", "rio_de_janeiro_rj", 6,532.55),
                new Pacote("Salvador", "salvador_ba", 5, 899.99),
                new Pacote("Foz do Iguaçu", "foz_do_iguacu_pr", 1, 289.90)));
        return pacotes;
    }
}
