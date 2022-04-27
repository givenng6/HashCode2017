import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Main {

    public static void main(String[] args){
        int numCaches = 0, numEndpoints = 0;
        int numRequests = 0, MAX_MB = 0;

        ArrayList<Endpoint> endpoints = new ArrayList<>();
        ArrayList<Video> videos = new ArrayList<>();
        ArrayList<Cache> caches = new ArrayList<>();
        ArrayList<Requests> requests = new ArrayList<>();

        File file = new File("example.txt");
        try {
            Scanner data = new Scanner(file);
                String line = data.nextLine();
                String[] info = line.split(" ");
                numEndpoints = Integer.parseInt(info[1]);
                numRequests = Integer.parseInt(info[2]);
                numCaches = Integer.parseInt(info[3]);
                MAX_MB = Integer.parseInt(info[4]);

                String[] clips = data.nextLine().split(" ");
                for(int i = 0; i < clips.length; i++) {
                    int size = Integer.parseInt(clips[i]);
                    videos.add(new Video(size, i));
                }for(int i = 0; i < numCaches; i++){
                    caches.add(new Cache(i, MAX_MB));
                }

                for(int i = 0; i < numEndpoints; i++){
                    String[] currEndpoint = data.nextLine().split(" ");
                    int latency = Integer.parseInt(currEndpoint[0]);
                    int connectedCaches = Integer.parseInt(currEndpoint[1]);
                    endpoints.add(new Endpoint(i, latency));
                    for(int j = 0; j < connectedCaches; j++){
                        String[] currCache = data.nextLine().split(" ");
                        int cacheID = Integer.parseInt(currCache[0]);
                        int cacheLatency = Integer.parseInt(currCache[1]);
                        endpoints.get(i).addCache(cacheID, cacheLatency);
                    }
                }

                for(int i = 0; i < numRequests; i++){
                    String[] currRequest = data.nextLine().split(" ");
                    int reqSize = Integer.parseInt(currRequest[2]);
                    int reqVideo = Integer.parseInt(currRequest[0]);
                    int reqEndpoint = Integer.parseInt(currRequest[1]);
                    if(videos.get(reqVideo).getSize() <= MAX_MB) {
                        requests.add(new Requests(reqEndpoint, reqSize, videos.get(reqVideo)));
                    }
                }

        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }

        mergeSort(requests);
        Algorithm algorithm = new Algorithm(endpoints, caches, requests);
        algorithm.youtube();
    }

    public static void mergeSort(ArrayList<Requests> requests){
        int listSize = requests.size();
        if(listSize <= 1){
            return;
        }
        int leftLength = listSize / 2;
        int rightLength = listSize - leftLength;

        ArrayList<Requests> left = new ArrayList<>();
        ArrayList<Requests> right = new ArrayList<>();
        for(int i = 0; i < leftLength; i++){
            left.add(requests.get(i));
        }for(int i = rightLength; i < listSize; i++){
            right.add(requests.get(i));
        }

        mergeSort(left);
        mergeSort(right);
        merge(requests, left, right);
    }

    public static void merge(ArrayList<Requests> requests, ArrayList<Requests> left, ArrayList<Requests> right){
        int leftSize = left.size();
        int rightSize = right.size();
        int i = 0, j = 0, k = 0;

        while(i < leftSize && j < rightSize){
            if(left.get(i).getRequestSize() <= right.get(j).getRequestSize()){
                requests.remove(k);
                requests.add(k, right.get(j));
                j++;
            }else{
                requests.remove(k);
                requests.add(k, left.get(i));
                i++;
            }
            k++;
        }

        while(i < leftSize){
            requests.remove(k);
            requests.add(k, left.get(i));
            i++;
            k++;
        }
        while(j < rightSize){
            requests.remove(k);
            requests.add(k, right.get(j));
            j++;
            k++;
        }
    }

}
