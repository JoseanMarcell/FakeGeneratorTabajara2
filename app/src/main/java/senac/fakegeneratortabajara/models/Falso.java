package senac.fakegeneratortabajara.models;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import senac.fakegeneratortabajara.R;

public class Falso {

    private String nome;
    private String tipo;
    private int foto;

    public Falso(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getFoto() {
        switch (tipo){
            case "Pokemon":
                foto = R.drawable.pikachu;
                break;
            case "Cerveja":
                foto = R.drawable.cerveja;
                break;
        }
        return foto;
    }
}
