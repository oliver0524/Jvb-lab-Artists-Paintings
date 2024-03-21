package org.example.Service;

import org.example.Application;
import org.example.Model.Artist;
import org.example.Model.Painting;
import org.example.Repository.ArtistRepository;
import org.example.Repository.PaintingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * The @Service annotation may also be used instead of the @Component annotation, the @Service annotation is a
 * Stereotype annotation, which would be a component with a preset configuration that would be appropriate for a
 * Service class (in this case, @Service and @Component behave identically.)
 *
 * The @Transactional annotation wraps every method in this class inside a database transaction, which is a set of
 * database statements that happen in isolation of all other database transactions. This means that multiple methods
 * of the class can run simultaneously (Spring is multithreaded) without having an effect such as a dirty read,
 * reading data that has been modified in an in-progress transaction. This matters when a Transaction has multiple
 * steps. (for instance, when a single transaction should process 100 database statements for purchasing items from a
 * user's cart, we don't want Spring to make the mistake of reading from an incomplete transaction, as e.g. a
 * request for the total cost of a user's cart could return an erroneous amount, such as only 50 items, if Spring
 * happens to read an incomplete transaction.)
 */
@Service
@Transactional
public class PaintingService {
    PaintingRepository paintingRepository;
    ArtistRepository artistRepository;
    @Autowired
    public PaintingService(PaintingRepository paintingRepository, ArtistRepository artistRepository){
        Application.log.info("logging method execution: PaintingService constructor");
        this.paintingRepository = paintingRepository;
        this.artistRepository = artistRepository;
    }

    /**
     * Leverage the Spring Data JPARepository method save() to persist a new Painting to the Painting table. The
     * new painting is returned. This functionality is provided to allow you to test your API.
     * @param painting A transient Painting entity
     * @return the persisted Painting entity, if successful
     */
    public Painting savePainting(long artistID, Painting painting){
        Artist a = artistRepository.findById(artistID).get();
        a.getPaintings().add(painting);
        return paintingRepository.save(painting);
    }

    /**
     * TODO Problem 1: Leverage the paintingRepository to retrieve all Paintings from the Painting table.
     * @return a list of all Painting entities
     */
    public List<Painting> getAllPaintings(){
        return paintingRepository.findAll();
    }
    /**
     * TODO Problem 2: Leverage the paintingRepository to retrieve the Artist of a Painting with a particular ID.
     * @param paintingID
     * @return the artist of a particular painting.
     */
    public Artist getArtistOfPainting(long paintingID){
        Optional<Painting> paintingOptional = paintingRepository.findById(paintingID);
        if (paintingOptional.isPresent()){
            Painting painting = paintingOptional.get();
            return painting.getArtist();
        }
        return null;
    }
    /**
     * TODO Problem 3: Using the query method you'll write in the PaintingRepository, retrieve all Paintings by their
     * genre.
     * @param genre
     * @return a list of all Painting entities with a particular genre.
     */
    public List<Painting> getAllPaintingsByGenre(String genre){
        return paintingRepository.findPaintingByGenre(genre);
    }
    /**
     * TODO Problem 4: Using the query method you'll write in the PaintingRepository, retrieve all Paintings by
     * their title.
     * @param title
     * @return a list of all Painting entities with a particular title.
     */
    public List<Painting> getAllPaintingsByTitle(String title){
        return paintingRepository.findPaintingByTitle(title);
    }
    /**
     * TODO Problem 5: Using the query method you'll write in the PaintingRepository, retrieve all Paintings by
     * their title & genre.
     * @param title
     * @param genre
     * @return a list of all Painting entities with a particular title & genre.
     */
    public List<Painting> getAllPaintingsByTitleAndGenre(String title, String genre){
        return paintingRepository.findPaintingByTitleAndGenre(title, genre);
    }

}
