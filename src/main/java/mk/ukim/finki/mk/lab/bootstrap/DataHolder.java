package mk.ukim.finki.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artistList = new ArrayList<>();
    public static List<Song> songList = new ArrayList<>();

    @PostConstruct
    public void init() {
        artistList.add(new Artist(1L, "Eric", "Clapton", "Eric Clapton Biography"));
        artistList.add(new Artist(2L, "Bob", "Marley", "Bob Marley Biography"));
        artistList.add(new Artist(3L, "David", "Bowie", "David Bowie Biography"));
        artistList.add(new Artist(4L, "Elton", "John", "Elton John Biography"));
        artistList.add(new Artist(5L, "Billy", "Joel", "Billy Joel Biography"));

        songList.add(new Song("Layla", "Layla", "Rock", 1970));
        songList.add(new Song("CYBL", "Could You Be Loved", "Reggae", 1980));
        songList.add(new Song("Starman", "Starman", "Pop", 1972));
        songList.add(new Song("RM", "Rocket Man", "Pop", 1972));
        songList.add(new Song("PM", "Piano Man", "Folk", 1973));
    }
}
