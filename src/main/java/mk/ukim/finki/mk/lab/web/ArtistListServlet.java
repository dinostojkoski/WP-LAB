package mk.ukim.finki.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.service.impl.ArtistServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ArtistListServlet", urlPatterns = {"/artist/artist-list"})
public class ArtistListServlet extends HttpServlet {

    private final ArtistServiceImpl artistService;
    private final SpringTemplateEngine springTemplateEngine;

    public ArtistListServlet(ArtistServiceImpl artistService, SpringTemplateEngine springTemplateEngine) {
        this.artistService = artistService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Artist> artistList;
        artistList = artistService.listArtists();

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("artistList", artistList);

        springTemplateEngine.process("listArtists.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId;
        List<Artist> artistList;
        artistList = artistService.listArtists();

        if (req.getParameter("songRadio") != null) {
            trackId = req.getParameter("songRadio");
        } else {
            trackId = "-";
        }

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("trackId", trackId);
        context.setVariable("artistList", artistList);
        springTemplateEngine.process("listArtists.html", context, resp.getWriter());
    }
}
