import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Algorithm{
    ArrayList<Cache> caches;
    ArrayList<Requests> requests;
    ArrayList<Endpoint> endpoints;

    public Algorithm(ArrayList<Endpoint> endpoints, ArrayList<Cache> caches, ArrayList<Requests> requests){
        this.caches = caches;
        this.requests = requests;
        this.endpoints = endpoints;
    }

    public void youtube(){
        for(Requests currReq : requests) {
            boolean flag = true;
            Video currVideo = currReq.getVideo();
            int endpointID = currReq.getEndpointID();
            Endpoint currEndpoint = endpoints.get(endpointID);
            currEndpoint.sort();
            for (int k = 0; k < currEndpoint.connectedCaches(); k++) {
                int cacheID = currEndpoint.getAdjCaches().get(k).getCacheID();
                Cache currCache = caches.get(cacheID);
                for(Video video : currCache.getStoredVideos()){
                    if(video.getVideoID() == currVideo.getVideoID()){
                        // to prevent adding the same video twice or more on the same cache...
                        flag = false;
                        break;
                    }
                }
                if(currCache.availableSpace() >= currVideo.getSize() && flag){
                    currCache.storeVideo(currVideo);
                    break;
                }
            }

        }
        print();
    }

    public void print(){
        try {
            FileWriter fw = new FileWriter("output.txt");
            PrintWriter pw = new PrintWriter(fw);
            for (Cache currCache : caches) {
                String aboutCache = "Cache ID: " + currCache.getCacheID();
                String stored = "Stored Videos:";
                pw.println(aboutCache);
                pw.println(stored);
                for (int k = 0; k < currCache.getStoredVideos().size(); k++) {
                    String currVid = "Video ID: " + currCache.getStoredVideos().get(k).getVideoID() + " Size " + currCache.getStoredVideos().get(k).getSize() + "MB";
                    pw.println(currVid);
                }
                String avail = "Available space left: " + currCache.availableSpace() + "MB";
                pw.println(avail);
                pw.println("");
            }
            pw.close();
        }catch (Exception e){
            System.out.println("Error");
        }
    }

}

