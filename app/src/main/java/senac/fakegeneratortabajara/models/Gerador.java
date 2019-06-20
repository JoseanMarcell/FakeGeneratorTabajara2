package senac.fakegeneratortabajara.models;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Gerador {

    private String tipo;
    private int quantidade;
    private List<Falso> fakes;

    public Gerador(String tipo, int quantidade) {
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public List<Falso> gerarFakes() {
        Faker faker = new Faker(new Locale("pt-BR"));
        fakes = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            switch (tipo){
                case "Nomes":
                    fakes.add(new Falso(faker.name().fullName(), "Nome"));
                    break;
                case "Pokemons" :
                    fakes.add(new Falso(faker.pokemon().name(), "Pokemon"));
                    break;
                case "Endereços" :
                    fakes.add(new Falso(faker.address().fullAddress(), "Endereço"));
                    break;
                case "Artistas" :
                    fakes.add(new Falso(faker.artist().name(), "Artista"));
                    break;
                case "Cervejas" :
                    fakes.add(new Falso(faker.beer().name(), "Cerveja"));
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

    public List<Falso> getFakes() {
        return fakes;
    }
}
