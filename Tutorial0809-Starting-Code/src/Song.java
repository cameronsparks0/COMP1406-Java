public class Song implements Comparable<Song> {
  private String title;
  private String artist;
  private int duration;
  private User owner;
  
  public Song()  {
    this("", "", 0, 0);
  }
  
  public Song(String t, String a, int m, int s)  {
    title = t;
    artist = a;
    duration = m * 60 + s;
    owner = null;
  }

  public void setOwner(User u){owner = u;}
  public User getOwner(){ return owner;}
  
  public String getTitle() { 
    return title; 
  } 
  
  public String getArtist() { 
    return artist; 
  }
  
  public int getDuration() { 
    return duration; 
  }
  
  public int getMinutes() {
    return duration / 60;
  }
  
  public int getSeconds() {
    return duration % 60;
  }

  public int compareTo(Song s){
    if(this.getTitle().compareTo(s.getTitle())<0){ // If the title of the first song is alphabetically before the compared song, -1 is returned meaning this first song goes before the second.
      return -1;
    }
    else if(this.getTitle().compareTo(s.getTitle())==0){ // If the title of both songs are the same, 0 is returned meaning the order between them doesn't matter
      return 0;
    }
    else{
      return 1; // If the title of the compared song is alphabetically before the first song, 1 is returned meaning the compared song goes before the first.
    }
  }
  
  public String toString()  {
    return("\"" + title + "\" by " + artist + " " + (duration / 60) + ":" + (duration%60));
  }
}
