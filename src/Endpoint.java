import java.util.ArrayList;

public class Endpoint{
    private final int latency, endpointID;
    private ArrayList<Node> adjCaches;

    public Endpoint(int endpointID, int latency){
        this.latency = latency;
        this.endpointID = endpointID;
        adjCaches = new ArrayList<>();
    }

    public int getLatency(){
        return this.latency;
    }

    public int connectedCaches(){
        return this.adjCaches.size();
    }

    public void addCache(int cacheID, int latency){
        adjCaches.add(new Node(cacheID, latency));
    }

    public int getEndpointID() {
        return endpointID;
    }

    public void sort(){
        mergeSort(adjCaches);
    }

    private void mergeSort(ArrayList<Node> adjCaches){
        int listSize = adjCaches.size();
        if(listSize <= 1){
            return;
        }
        int leftLength = listSize / 2;
        int rightLength = listSize - leftLength;

        ArrayList<Node> left = new ArrayList<>();
        ArrayList<Node> right = new ArrayList<>();
        for(int i = 0; i < leftLength; i++){
            left.add(adjCaches.get(i));
        }for(int i = rightLength; i < listSize; i++){
            right.add(adjCaches.get(i));
        }

        mergeSort(left);
        mergeSort(right);
        merge(adjCaches, left, right);
    }

    private void merge(ArrayList<Node> adjCaches, ArrayList<Node> left, ArrayList<Node> right) {
        int leftSize = left.size();
        int rightSize = right.size();
        int i = 0, j = 0, k = 0;

        while (i < leftSize && j < rightSize) {
            if (left.get(i).getLatency() >= right.get(j).getLatency()) {
                adjCaches.remove(k);
                adjCaches.add(k, right.get(j));
                j++;
            } else {
                adjCaches.remove(k);
                adjCaches.add(k, left.get(i));
                i++;
            }
            k++;
        }

        while (i < leftSize) {
            adjCaches.remove(k);
            adjCaches.add(k, left.get(i));
            i++;
            k++;
        }
        while (j < rightSize) {
            adjCaches.remove(k);
            adjCaches.add(k, right.get(j));
            j++;
            k++;
        }

    }

    public ArrayList<Node> getAdjCaches(){
        return adjCaches;
    }
}
