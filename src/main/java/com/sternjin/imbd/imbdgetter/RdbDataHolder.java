package com.sternjin.imbd.imbdgetter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sternjin.imbd.imbdgetter.domain.Movie;

/**
 * Created by zinc on 16/07/2017.
 */
public class RdbDataHolder
    implements DataHolder
{

    Logger logger = LoggerFactory.getLogger(getClass());

    private final JdbcTemplate jdbcTemplate;

    public RdbDataHolder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override public void put(Movie movie)
        throws IOException
    {
        String sql = "update data"
            + " SET img_url = ?"
            + " where id = ?";
        jdbcTemplate.update(sql, movie.getImgUrl(), movie.getId());

    }

    @Override public void put(List<Movie> list)
        throws IOException
    {
        String sql = "insert into data(id, imdb_id, name, genres) values(?,?,?,?)";

        AtomicInteger cnt = new AtomicInteger(0);
        try (Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);)
        {
            for (Movie m : list) {
                try {
                    ps.setInt(1, Integer.parseInt(m.getId()));
                    ps.setString(2, m.getImdbId());
                    ps.setString(3, m.getName());
                    ps.setString(4, m.getGenres());
                    ps.addBatch();
                    if (cnt.incrementAndGet() % 40 == 0) {
                        ps.executeBatch();
                        if (cnt.get() % 1000 == 0) {
                            logger.info("RdbDataHolder ... {} / {}", cnt.get(), list.size());
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            ps.executeBatch();
        } catch (SQLException e) {
            //logger.error(e.getMessage());
        }
    }

    @Override public List<Movie> getAll()
        throws IOException
    {
        return jdbcTemplate.query("select * from data", new MovieRowMapper());

    }

    @Override public List<Movie> getImgNullMovies()
        throws IOException
    {
        return jdbcTemplate.query("select * from data where img_url is null", new MovieRowMapper());
    }

    @Override public int size()
        throws IOException
    {
        return jdbcTemplate.queryForObject("select count(*) from data", Integer.class);
    }

    class MovieRowMapper
        implements RowMapper<Movie>
    {
        @Override public Movie mapRow(ResultSet rs, int i)
            throws SQLException
        {
            Movie movie = new Movie();
            movie.setId(rs.getString("id"));
            movie.setImdbId(rs.getString("imdb_id"));
            movie.setName(rs.getString("name"));
            movie.setGenres(rs.getString("genres"));
            movie.setImgUrl(rs.getString("img_url"));
            return movie;
        }
    }

}
