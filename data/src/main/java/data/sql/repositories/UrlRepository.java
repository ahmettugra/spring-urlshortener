package data.sql.repositories;

import data.sql.entities.Url;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

/**
 * Created by ahmettugra on 3.08.2017.
 */
public interface UrlRepository extends CrudRepository<Url, BigInteger> {
    Url findByShortCode(String shortCode);
    Url findByLongUrl(String longUrl);
}
