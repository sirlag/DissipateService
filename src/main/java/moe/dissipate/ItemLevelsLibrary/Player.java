package moe.dissipate.ItemLevelsLibrary;

public class Player {
    private String URL, Name, image, server;

    public String getURL() {
        return URL;
    }

    public String getName() {
        return Name;
    }

    public String getImage() {
        return image;
    }

    public String getServer() {
        return server;
    }

    public Player(String URL, String name, String image, String server) {
        this.URL = URL.replaceAll("http://na.finalfantasyxiv.com/lodestone", "");
        this.URL = this.URL.substring(0, this.URL.length()-1);
        Name = name;
        this.image = image;
        this.server = server;
    }

    @Override
    public String toString() {
        return "Player{" +
                "URL='" + URL + '\'' +
                ", Name='" + Name + '\'' +
                ", image='" + image + '\'' +
                ", server='" + server + '\'' +
                '}';
    }
}
