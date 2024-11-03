package mk.ukim.finki.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.model.Song;
import mk.ukim.finki.mk.lab.service.impl.ArtistServiceImpl;
import mk.ukim.finki.mk.lab.service.impl.SongServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "SongDetailsServlet", urlPatterns = {"/songs/song-details"})
public class SongDetailsServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final SongServiceImpl songService;
    private final ArtistServiceImpl artistService;

    public SongDetailsServlet(SpringTemplateEngine springTemplateEngine, SongServiceImpl songService, ArtistServiceImpl artistService) {
        this.springTemplateEngine = springTemplateEngine;
        this.songService = songService;
        this.artistService = artistService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Song song = songService.listSongs().stream().findFirst().orElse(null);

        IWebExchange WebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(WebExchange);
        context.setVariable("entity", song);
        springTemplateEngine.process("songDetails.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId = req.getParameter("trackId");
        String artistId = req.getParameter("artistId");
        //Song song = songService.listSongs().stream().findFirst().orElse(null);
        Song song = null;

        /*if (trackId != null && artistId != null) {
            song = songService.findByTrackId(trackId);
            Artist artist = artistService.findById(Long.valueOf(artistId));
            song.addPerformer(artist);
        }*/

        if (trackId != null) {
            song = songService.findByTrackId(trackId);
        }

        if (song == null) {
            // Handle the case where the song was not found
            System.err.println("Song not found for trackId: " + trackId);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Song not found");
            return; // Exit early
        }

        if (artistId != null) {
            Artist artist = artistService.findById(Long.valueOf(artistId));
            if (artist != null) {
                song.addPerformer(artist); // Only call this if artist is found
            } else {
                // Handle the case where the artist was not found
                System.err.println("Artist not found for artistId: " + artistId);
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Artist not found");
                return; // Exit early
            }
        }

        IWebExchange WebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(WebExchange);
        context.setVariable("entity", song);
        springTemplateEngine.process("songDetails.html", context, resp.getWriter());
    }
}
