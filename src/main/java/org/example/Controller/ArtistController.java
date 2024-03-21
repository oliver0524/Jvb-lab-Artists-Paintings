package org.example.Controller;
import org.example.Model.Artist;
import org.example.Service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The @RestController annotation allows this method to be used as a Spring MVC controller.
 */
@RestController
public class ArtistController {
    ArtistService artistService;
    /**
     * @param artistService an ArtistService bean that will be autowired in from the Spring IOC container.
     */
    @Autowired
    public ArtistController(ArtistService artistService){
        this.artistService = artistService;
    }
    /**
     * GET mapping on the endpoint GET localhost:8080/artist should respond with a list of all artists
     * EG, GET localhost:9000/artist could return the results
     * [{
     *     "id":1,
     *     "name":"picasso",
     *     "nationality":"spanish",
     *     "paintings":[{"title":"guernica", "year":1937, "genre":"cubism"}]
     * },{
     *     "id":2,
     *     "name":"kandinsky",
     *     "nationality":"russian",
     *     "paintings":[{"title":"capricious", "year":1930, "genre":"abstract"},
     *                  {"title":"blue", "year":1927, "genre":"abstract"}]
     * },{
     *     "id":3
     *     "name":"mittens the cat",
     *     "nationality":"kitten"
     *     "paintings":[{"title":"blue", "year":2023, "genre":"paw prints"}]
     * }]
     * @return a JSON response body of all Artists
     */
    @GetMapping("artist")
    public List<Artist> getAllArtist(){
        return artistService.getAllArtists();
    }
    /**
     * POST mapping on the endpoint POST localhost:9000/artist should post a new artist and respond with the new artist
     * EG, POST localhost:9000/artist with the body
     * {
     *     "name":"picasso",
     *     "nationality":"spanish",
     * }
     * should respond with
     * {
     *     "id":1,
     *     "name":"picasso",
     *     "nationality":"spanish",
     *     "paintings":[{"title":"guernica", "year":1937, "genre":"cubism"}]
     * }
     * @param artist an Artist object parsed from the request body JSON
     * @return a JSON response body of the newly added artist
     */
    @PostMapping("artist")
    public Artist postArtist(@RequestBody Artist artist){
        return artistService.saveArtist(artist);
    }
    /**
     * GET mapping on the endpoint GET localhost:9000/artist?nationality={nationality} should respond with all artists
     * with some nationality, EG GET localhost:9000/artist?nationality=spanish
     * could respond with
     * {
     *     "id":1,
     *     "name":"picasso",
     *     "nationality":"spanish",
     *     "paintings":[{"title":"guernica", "year":1937, "genre":"cubism"}]
     * }
     */
    @GetMapping(value = "artist", params = {"nationality"})
    public List<Artist> getArtistByNationality(@RequestParam("nationality") String nationality){
        return artistService.getArtistsByName(nationality);
    }
    /**
     * GET mapping on the endpoint GET localhost:9000/artist?name={name} should respond with all artists with some name,
     * EG GET localhost:9000/artist?name=kandinsky could respond with
     * [{
     *     "id":2,
     *     "name":"kandinsky",
     *     "nationality":"russian",
     *     "paintings":[{"title":"capricious", "year":1930, "genre":"abstract"},
     *                  {"title":"blue", "year":1927, "genre":"abstract"}]
     * }]
     */
    @GetMapping(value = "artist", params = {"name"})
    public List<Artist> getArtistByName(@RequestParam("name") String name){
        return artistService.getArtistsByName(name);
    }

}
