import java.util.ArrayList;

/**
 * Class EthernetPlayer
 */

public class EthernetPlayer extends MusicPlayer {
    
    private int deviceID;
    private int connStatus;
    
    
    static final int CONNECTED = 1;
    static final int NOT_CONNECTED = 0;
    
    private ArrayList<String> downloadList = new ArrayList<String>();
    
    
    public EthernetPlayer(int id) {
        super();
        deviceID = id;
        connStatus = NOT_CONNECTED;
        
        downloadList.add("Dark Horse");
        downloadList.add("Royals");
        downloadList.add("Counting Stars");
        downloadList.add("Let Her Go");
        downloadList.add("The Fox");
        System.out.println("Creating EthernetPlayer: " + deviceID);
    }
    
    /**
     *  @override turnOn
     *
     */
    
    public void turnOn() {
        
        System.out.println("EthernetPlayer Powering ON");
        this.status = ON;
        this.connectToNetwork();
        
    }
    
    
    /**
     *  Connect to network
     *
     */
    
    public void connectToNetwork() {
        
        if (this.status == OFF) {
            
        }
        
        else if ( connStatus == CONNECTED) {
            System.out.println("Already Connected");
        }
        else {
            connStatus = CONNECTED;
            System.out.println("Connected to Network");
            System.out.println();
        }
        
    }
    
    /**
     *  @override play
     *
     */
    
    public void play(int trackNumber) {
        if (trackNumber < 0 || trackNumber >= this.PLAYLIST_SIZE || trackNumber >= this.playlist.size()) {
            System.out.println("Please enter valid trackNo. " + trackNumber + " Invalid!");
            this.status = OFF;
            return;
        }
        System.out.println("Now Playing: " + playlist.get(trackNumber));
        this.status = OFF;
        
    }
    
    
    /**
     *  addTrackToPlaylist: Adds mentioned track to the end of playlist array
     *
     */
    
    public void addToPlaylist(String trackName) throws ExceedsCapacityException {
        
        if (playlist.size() == MusicPlayer.PLAYLIST_SIZE) {
            throw new ExceedsCapacityException("Exceeds Capacity!");
            
        }
        playlist.add(trackName);
        
    }
    
    
    /**
     *  deleteFromPlaylist: deletes track of give name from the playlist
     *
     */
    
    public void deleteFromPlaylist(String trackName) throws TrackNotFoundException {
        //implement your code for deleting the given element from the playlist
        // Add your code here
        if(!playlist.contains(trackName)){
            throw new TrackNotFoundException("Invalid!");
        }
        playlist.remove(trackName);
    }
    
    
    /**
     *  download: Downloads mentioned song from the given Download list and adds to playlist
     *
     */
    
    public void download(String trackName) {
        //implement this section of code to lookup tracName in the downloadlist and
        //add it to the playlist using add method in Arraylist with following message
        //add your code here
        
        
        
        //System.out.println("Downloading..." + trackName);
        
        //System.out.println("Sorry, Playlist is full! ");
        //System.out.println("Sorry, we currently do not support the track you tried to download!");
       /* boolean result = false;
        for(int i = 0 ; i < downloadList.size() ; i++){
            if(downloadList.get(i).equals(trackName)){
                result = true;
            }
        }*/


        if(downloadList.contains(trackName)){
            try{
                addToPlaylist(trackName);
                System.out.println("Downloading..." + trackName);
            }catch (ExceedsCapacityException e){
                System.out.println("Downloading..." + trackName);
                System.out.println("Sorry, Playlist is full! ");
            }
        }else{
            System.out.println("Downloading..." + trackName);
            System.out.println("Sorry, we currently do not support the track you tried to download!");
        }
        
    }
    
}
