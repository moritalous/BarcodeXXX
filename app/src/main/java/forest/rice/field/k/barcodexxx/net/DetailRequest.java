package forest.rice.field.k.barcodexxx.net;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import forest.rice.field.k.barcodexxx.entity.PokemonNew;

public class DetailRequest {

    private static final String baseUrl = "http://www.pokemon.jp/zukan/detail/%03d.html";

    public PokemonNew requestDetail(int no) {

        Document document;
        try {
            document = Jsoup.connect(createUrl(no)).get();
            return createPokemon(document);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    private String createUrl(int no) {
        return String.format(baseUrl, no);
    }

    private PokemonNew createPokemon(Document document) {
        PokemonNew pokemon = new PokemonNew();

        String no = document.getElementsByClass("pokemon-page").get(0).getElementsByClass("num").get(0).text();

        pokemon.setNo(Integer.parseInt(no.replace("No.", "")));

        pokemon.setName(document.getElementsByClass("pokemon-page").get(0).getElementsByClass("name").get(0).text());

        String profilePhto =document.getElementsByClass("profile-phto").get(0).getElementsByTag("img").get(0).attributes().get("src");
        pokemon.setImageUrl(profilePhto.replace("/zukan/images/l/", ""));

        return pokemon;
    }
}
