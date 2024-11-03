package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Artist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArtistService {
    List<Artist> listArtists();
    Artist findById(Long id);
}
