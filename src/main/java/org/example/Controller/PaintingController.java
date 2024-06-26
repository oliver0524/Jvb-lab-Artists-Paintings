package org.example.Controller;

import org.example.Model.Painting;
import org.example.Service.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The @Controller annotation is a stereotype annotation introduced by Spring MVC (model/view/controller), which
 * allows for the creation of a Component which follows a stereotype Controller pattern. This means that the class can
 * be used to create web endpoints. The @RestController annotation exhibits all the behavior of @Controller, but it
 * also includes the behavior of @ResponseBody, which automatically converts the Objects that are returned by the
 * endpoint methods into JSON response bodies.
 */
@RestController
public class PaintingController {
    /**
     * This class relies on a PaintingService dependency. Spring can inject this Object as a Bean (Object managed by
     * Spring) into this class, rather than having the developer instantiate the object. This is referred to as
     * dependency injection, which is a part of Spring Core.
     */
    PaintingService paintingService;
    /**
     * The @Autowired annotation will automatically find the most appropriate Bean to inject into this class.
     * This class is dependent on a PaintingService, so @Autowired will inject a PaintingService bean from the IOC
     * container (inversion-of-control.) This is referred to as Dependency Injection, which is a part of Spring Core.
     *
     * There are 3 ways to conduct Dependency Injection in Spring. The most common way, constructor injection, is
     * leveraged here by placing @Autowired above the constructor. The developer may also perform Setter injection
     * by placing @Autowired above a Setter method, or Field injection by placing the @Autowired annotation above
     * a class's field.
     *
     * @param paintingService a PaintingService bean that will be autowired in from the Spring IOC container.
     */
    @Autowired
    public PaintingController(PaintingService paintingService){
        this.paintingService = paintingService;
    }
    /**
     * This endpoint is provided to ensure you are able to test your API. Endpoint on POST
     * localhost:9000/artist/{artistID}/painting to persist a painting contained in the request
     * body. For instance, a request to POST localhost:9000/artist/3/painting, containing a valid new Painting JSON in
     * the request body such as
     * {"title":"untitled", "year":2023, "genre":"paw print"}
     * should persist a new Painting entity into the Painting table and respond with
     * {"id":1, "title":"untitled", "year":2023, "genre":"paw print"}
     */
    @PostMapping("artist/{id}/painting")
    public Painting postPainting(@PathVariable long id, @RequestBody Painting painting){
        return paintingService.savePainting(id, painting);
    }

    /**
     * TODO Problem 1: write an endpoint on GET localhost:9000/painting to get all paintings.
     * Leverage the paintingService.
     * For instance, a request to GET localhost:9000/painting should retrieve all paintings, such as
     * [{"title":"guernica", "year":1937, "genre":"cubism"},
     *  {"title":"capricious", "year":1930, "genre":"abstract"},
     *  {"title":"blue", "year":1927, "genre":"abstract"},
     *  {"title":"blue", "year":2023, "genre":"paw prints"}]
     */
    @GetMapping("painting")
    public List<Painting>getAllPainting(){
        return paintingService.getAllPaintings();
    }

    /**
     * TODO Problem 3: write an endpoint on GET localhost:9000/painting?title={title} to retrieve all paintings with
     * some title. Leverage the paintingService. For instance, a request to GET localhost:9000/painting?title=blue
     * could respond with
     * [{"title":"blue", "year":1927, "genre":"abstract"},
     *  {"title":"blue", "year":2023, "genre":"paw prints"}]
     */
    @GetMapping(value="painting",params={"title"})
    public List<Painting>getPaintingByTitle(@RequestParam("title") String title){
        return paintingService.getAllPaintingsByTitle(title);
    }

    /**
     * TODO Problem 4: write an endpoint on GET localhost:9000/painting?genre={genre} to retrieve all paintings with
     * some genre. Leverage the paintingService. For instance, a request to GET
     * localhost:9000/painting?genre=surrealist could respond with
     * [{"title":"capricious", "year":1930, "genre":"abstract"},
     *  {"title":"blue", "year":1927, "genre":"abstract"}]
     */
    @GetMapping(value="painting",params={"genre"})
    public List<Painting>getPaintingByGenre(@RequestParam("genre") String genre){
        return paintingService.getAllPaintingsByGenre(genre);
    }

    /**
     * TODO Problem 5: write an endpoint on GET localhost:9000/painting?genre={genre}&title={title} to retrieve all
     * paintings with some genre AND title. Leverage the paintingService. For instance, a request to GET
     * localhost:9000/painting?title=blue&genre=surrealist could respond with,
     * [{"title":"blue", "year":1927, "genre":"abstract"}]
     */
    @GetMapping(value="painting",params={"title","genre"})
    public List<Painting>getPaintingByTitleAndGenre(@RequestParam String title, String genre){
        return paintingService.getAllPaintingsByTitleAndGenre(title, genre);
    }
}