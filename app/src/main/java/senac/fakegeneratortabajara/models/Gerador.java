package senac.fakegeneratortabajara.models;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Gerador {

    private String tipo;
    private int quantidade;
    private List<String> fakes;

    public Gerador(String tipo, int quantidade) {
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public List<String> gerarFakes() {
        Faker faker = new Faker(new Locale("pt-BR"));
        fakes = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            switch (tipo){
                case "Nomes": fakes.add(faker.name().fullName());
                    break;
                case "Pokemons" : fakes.add(faker.pokemon().name());
                    break;
                case "Endereços" : fakes.add(faker.address().fullAddress());
                    break;
                case "Artistas" : fakes.add(faker.artist().name());
                    break;
                case "Cervejas" : fakes.add(faker.beer().name());
                    break;
            }
        }

        return fakes;
    }

    public String getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public List<String> getFakes() {
        return fakes;
    }
}
