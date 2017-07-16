package com.sternjin.imbd.imbdgetter;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import com.sternjin.imbd.imbdgetter.domain.Movie;

/**
 * Created by zinc on 02/07/2017.
 */
@Component
public class ImdbGetterImpl
    implements ImdbGetter
{
    public static final String VAR_ID = "${id}";

    @Override public Movie getDataByName(String name)
        throws IOException
    {
        return null;
    }

    @Override public Movie getDataById(String id)
        throws IOException
    {
        return null;
    }

    @Override public Movie getDataByObject(Movie movie)
        throws IOException
    {
        return getData(movie);
    }

    private Movie getData(Movie movie)
        throws IOException
    {

        String baseUrl = String.format("http://www.imdb.com/title/tt%s/", VAR_ID);
        String url = baseUrl.replace(VAR_ID, movie.getImdbId());

        Document doc = Jsoup.connect(url).get();
        Element overview = doc.getElementById("title-overview-widget");
        Element poster = overview.getElementsByClass("poster").first();
        Element img = poster.getElementsByTag("img").first();

        String imgUrl = img.attr("src");
        movie.setImgUrl(imgUrl);

        return movie;
    }

}
