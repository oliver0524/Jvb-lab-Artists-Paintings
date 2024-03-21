package org.example.Service;


import org.example.Application;
import org.example.Model.Artist;
import org.example.Repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The @Service annotation may also be used instead of the @Component annotation, the @Service annotation is a
 * Stereotype annotation, which would be a component with a preset configuration that would be appropriate for a
 * Service class (in this case, @Service and @Component behave identically.)
 *
 * For the purpose of completing any challenges within this project: do not change anything within this class!
 * It is already complete.
 */
@Service
public class ArtistService {
    /**
     * This class relies on an ArtistRepository dependency. Spring can inject this Object as a Bean (Object managed by
     * Spring) into this class, rather than having the developer instantiate the object. This is referred to as
     * Dependency Injection, which is a part of Spring Core.
     */
    ArtistRepository artistRepository;
    /**
     * The @Autowired annotation will automatically find the most appropriate Bean to inject into this class.
     * This class is dependent on an ArtistRepository, so @Autowired will inject an ArtistRepository bean from the IOC
     * container (inversion-of-control.) This is referred to as Dependency Injection, which is a part of Spring Core.
     *
     * There are 3 ways to conduct Dependency Injection in Spring. The most common way, constructor injection, is
     * leveraged here by placing @Autowired above the constructor. The developer may also perform Setter injection
     * by placing @Autowired above a Setter method, or Field injection by placing the @Autowired annotation above
     * a class's field.
     */
    @Autowired
    public ArtistService(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
        Application.log.info("logging method execution: ArtistService constructor");
    }
    /**
     * Method uses the JPARepository-provided method findAll().
     * @return a list of all Artist entities
     */
    public List<Artist> getAllArtists(){
        Application.log.info("logging method execution: ArtistService.getAllArtists");
        return artistRepository.findAll();
    }
    /**
     * Method persists an Artist. The artist Object in the parameter is transient (ie, no relationship with a database
     * record), and the Artist Object returned by the save() method is persistent (a relationship with a database
     * record exists)
     * @param artist a transient Artist entity
     * @return a persisted Artist entity
     */
    public Artist saveArtist(Artist artist){
        Application.log.info("logging method execution: ArtistService.saveArtist");
        return artistRepository.save(artist);
    }
    /**
     * Method retrieves Artists with a particular name by leveraging a JPQL query written in ArtistRepository.
     * @param name a Name for a particular painter (eg 'Picasso')
     * @return all Artist entities with the nationality
     */
    public List<Artist> getArtistsByName(String name){
        Application.log.info("logging method execution: ArtistService.getArtistsByName");
        return artistRepository.findArtistsByName(name);
    }
    /**
     * Method retrieves Artists of a particular Nationality by leveraging a query method written in ArtistRepository.
     * @param nationality a String for a nationality (eg 'Spanish')
     * @return all Artist entities with the nationality
     */
    public List<Artist> getArtistsByNationality(String nationality){
        Application.log.info("logging method execution: ArtistService.getArtistsByNationality");
        return artistRepository.findArtistsByNationality(nationality);
    }
}
