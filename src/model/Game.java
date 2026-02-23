package model;

public class Game {
    private Long id;
    private String title;
    private String studio;
    private String genre;
    private int releaseYear;
    private boolean multi;

    public Game(Long id, String title, String studio, String genre, int releaseYear, boolean multi) {
        this.id = id;
        this.title = title;
        this.studio = studio;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.multi = multi;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getStudio() {
        return studio;
    }
    public void setStudio(String studio) {
        this.studio = studio;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public int getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    public boolean isMulti() {
        return multi;
    }
    public void setMulti(boolean multi) {
        this.multi = multi;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", studio='" + studio + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseYear=" + releaseYear +
                ", multiplayer=" + multi +
                '}';
    }
}

