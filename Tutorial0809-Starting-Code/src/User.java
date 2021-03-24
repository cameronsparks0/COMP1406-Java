import java.util.ArrayList;

public class User {
  private String     userName;
  private boolean    online;
  private ArrayList<Song> songList;
  
  public User()  { this(""); }
  
  public User(String u)  {
    userName = u;
    online = false;
    songList = new ArrayList<Song>();
  }
  
  public String getUserName() { return userName; }
  public ArrayList<Song> getSongList() {return songList;}
  public boolean isOnline() { return online; }

  public void addSong(Song s){
    songList.add(s);
    s.setOwner(this);
  }

  public int totalSongTime(){
    int songDuration=0;
    for(Song s: songList){
      songDuration = songDuration + s.getDuration();
    }
    return songDuration;
  }

  public void register(MusicExchangeCenter m){m.registerUser(this);}
  public void logon(){this.online = true;}
  public void logoff(){this.online = false;}

  public ArrayList<String> requestCompleteSonglist(MusicExchangeCenter m){
    ArrayList<String> songList = new ArrayList<String>();
    int songCount=1;
    songList.add(String.format("%-30s%-16s%-5s%s","TITLE","ARTIST","TIME","OWNER")); //No exact dimensions on tutorial, these are estimated based on example given.
    for(User u: m.onlineUsers()){
      for(Song s: u.getSongList()){
        songList.add(String.format("%-30s%-15s%2d%s%02d%s",songCount+". "+s.getTitle(),s.getArtist(),s.getDuration()/60,":",s.getDuration()%60," "+s.getOwner().getUserName()));
        songCount = songCount + 1;
      }
    }
    return songList;
  }

  public ArrayList<String> requestSonglistByArtist(MusicExchangeCenter m, String artist){
    ArrayList<String> songList = new ArrayList<String>();
    int songCount=1;
    songList.add(String.format("%-30s%-16s%-5s%s","TITLE","ARTIST","TIME","OWNER"));
    for(User u: m.onlineUsers()){
      for(Song s: u.getSongList()){
        if(s.getArtist().equals(artist)){
          songList.add(String.format("%-30s%-15s%2d%s%02d%s",songCount+". "+s.getTitle(),s.getArtist(),s.getDuration()/60,":",s.getDuration()%60," "+s.getOwner().getUserName()));
          songCount = songCount + 1;
        }
      }
    }
    return songList;
  }

  public void downloadSong(MusicExchangeCenter m, String title, String ownerName){
    Song newSongTemp = m.getSong(title,ownerName); // If getSong is called multiple times it ends up creating duplicates for downloadedSongs, so it must be temporarily stored.
    if(newSongTemp!=null){
      Song newSong = new Song(newSongTemp.getTitle(),newSongTemp.getArtist(),newSongTemp.getMinutes(),newSongTemp.getSeconds()); //Needing to create a new copy of the song as simply setting a song object = to another just points it to that other object.
      newSong.setOwner(this);
      songList.add(newSong);
    }
  }
  
  public String toString()  {
    String s = "" + userName + ": "+songList.size()+" songs (";
    if (!online) s += "not ";
    return s + "online)";
  }
}
