package mk.ukim.finki.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artistList = null;
    public static List<Song> songList = null;

    @PostConstruct
    public void init() {
        artistList = new ArrayList<>();
        artistList.add(new Artist(1L, "Bobi", "Majstorot", "Mnogu spremni majstorski pesni"));
        artistList.add(new Artist(2L, "Mekica", "Mekisonski", "Mekicite od strezhevo"));
        artistList.add(new Artist(3L, "Dedoto", "Dedovski", "Star covek dobri pesni"));
        artistList.add(new Artist(4L, "Macka", "Pejakovski", "Brz i besen"));
        artistList.add(new Artist(5L, "Pete", "Petkinson", "Ednostavno pete"));

        songList = new ArrayList<>();
        songList.add(new Song("PacX", "Pacman", "Death Metal", 1998));
        songList.add(new Song("ZZX", "What is this", "Turbofolk", 1964));
        songList.add(new Song("TestPesna", "TestTitle", "Metal", 2005));
        songList.add(new Song("EDM", "Electro House", "Techno", 2021));
        songList.add(new Song("Test2Pesna", "Test2Title", "Neshto Neshto", 2015));
    }
}
