import java.util.*;

public class MusicExchangeCenter {
    private ArrayList<User> users;
    private HashMap<String,Float> royalties;
    private ArrayList<Song> downloadedSongs;
    private HashMap<String, Integer> mostDownloaded; // Added this attribute to keep track of downloads per song (The key being the song name and the value being the amount of downloads)

    public MusicExchangeCenter(){
        users = new ArrayList<User>();
        royalties = new HashMap<String,Float>();
        downloadedSongs = new ArrayList<Song>();
        mostDownloaded = new HashMap<String, Integer>();
    }

    public ArrayList<Song> getDownloadedSongs(){return downloadedSongs;}

    public ArrayList<User> onlineUsers(){
        ArrayList<User> onlineUsers = new ArrayList<User>();
        for (User u : users) {
            if (u.isOnline()) {
                onlineUsers.add(u);
            }
        }
        return onlineUsers;
    }

    public ArrayList<Song> allAvailableSongs(){
        ArrayList<Song> availableSongs = new ArrayList<Song>();
        for(User u: users){
            for(Song s: u.getSongList()){
                if(!availableSongs.contains(s) && u.isOnline()){ // Checking to see if the user is online and if their is already a version of this song before adding it to the list of available songs.
                    availableSongs.add(s);
                }
            }
        }
        return availableSongs;
    }

    public String toString(){
        return "Music Exchange Center ("+onlineUsers().size()+" users online, "+allAvailableSongs().size()+" songs available)";
    }

    public User userWithName(String s){
        for (User u : users) {
            if (u.getUserName().equals(s)) {
                return u;
            }
        }
        return null;
    }

    public void registerUser(User x){
        if(userWithName(x.getUserName()) == null){
            users.add(x);
        }
    }

    public ArrayList<Song> availableSongsByArtist(String artist){
        ArrayList<Song> songsByArtist = new ArrayList<Song>();
        for(Song s: allAvailableSongs()){
            if(s.getArtist().equals(artist)){ // Within all the available songs it checks for ones by the specified artist.
                songsByArtist.add(s);
            }
        }
        return songsByArtist;
    }

    public Song getSong(String title, String ownerName){
        for(User u: users){
            for(Song s: u.getSongList()){
                if(s.getTitle().equals(title) && s.getOwner().getUserName().equals(ownerName) && s.getOwner().isOnline()){ //Checking all the conditions to see if a song is able to be downloaded(Title and Owner matches as well as if the owner is online)
                    downloadedSongs.add(s);
                    if(royalties.get(s.getArtist())!=null){ //If the artist already has an established royalty (Has a value for a specific key), 25 cents is added onto it.
                        royalties.put(s.getArtist(),royalties.get(s.getArtist())+0.25f);
                    }
                    else{ //Adding the artist to the HashMap of royalties with a starting value of 25 cents.
                        royalties.put(s.getArtist(),0.25f);
                    }
                    if(mostDownloaded.get(s.getTitle())!=null){ // If the artist is already part of the mostDownloaded HashMap add 1 to its value (Simulates one more download to the total downloads of that particular song)
                        mostDownloaded.put(s.getTitle(),mostDownloaded.get(s.getTitle())+1);
                    }
                    else{ // If the artist is not part of the mostDownloaded HashMap add the artist as a key and set the starting value to one.
                        mostDownloaded.put(s.getTitle(),1);
                    }
                    return s;
                }
            }
        }
        return null;
    }

    public void displayRoyalties(){
        System.out.println("Amount Artist");
        System.out.println("---------------");
        ArrayList<String> artistsTaken = new ArrayList<String>();
        for(Song s: uniqueDownloads()){
            if(royalties.containsKey(s.getArtist()) && !artistsTaken.contains(s.getArtist())){ // Making sure the artist is not displayed more than once.
                System.out.println("$"+royalties.get(s.getArtist())+" "+s.getArtist());
                artistsTaken.add(s.getArtist());
            }
        }
    }

    public TreeSet<Song> uniqueDownloads(){ return new TreeSet<Song>(downloadedSongs); }

    public ArrayList<Pair<Integer,Song>> songsByPopularity(){
        ArrayList<Pair<Integer,Song>> popularSongs = new ArrayList<Pair<Integer,Song>>();
        ArrayList<String> songsTaken = new ArrayList<String>(); // To keep track of which songs are already on the popularSongs ArrayList.
        for(User u: users){
            for(Song s: u.getSongList()){
                if(mostDownloaded.get(s.getTitle())!=null && !songsTaken.contains(s.getTitle())){ // Making sure no duplicate songs enter the popularSongs list and also if the song is actually downloaded.
                        popularSongs.add(new Pair<Integer, Song>(mostDownloaded.get(s.getTitle()), s));
                        songsTaken.add(s.getTitle());
                }
            }
        }
        Collections.sort(popularSongs, new Comparator<Pair<Integer, Song>>() {
            public int compare(Pair<Integer, Song> p1, Pair<Integer, Song> p2) {
                if(p1.getKey()>p2.getKey()){ // Comparing the key values of the most popular songs, whichever has the most downloads will be placed before the others.
                    return -1;
                }
                else if(p1.getKey().equals(p2.getKey())){
                    return 0;
                }
                else{
                    return 1;
                }
            }
        });
        return popularSongs;

    }
}
