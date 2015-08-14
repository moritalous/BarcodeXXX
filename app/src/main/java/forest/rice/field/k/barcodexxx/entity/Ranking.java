package forest.rice.field.k.barcodexxx.entity;

import java.util.ArrayList;

public class Ranking {

    private Captor captor = null;
    private ArrayList<Pokemon> pokemonList = new ArrayList<>();

    public Captor getCaptor() {
        return captor;
    }

    public void setCaptor(Captor captor) {
        this.captor = captor;
    }

    public ArrayList<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(ArrayList<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }
}
