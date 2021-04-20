package union_find;

public class QU {
    private int []id;
    private int count;
    private int[] size;
    public QU(int N) {
        count =N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] =i;
            size[i] =1;
        }
    }
    public int count(){
        return count;
    }
    public boolean connected(int p,int q){
        return find(p) == find(q);
    }

    private int find(int q) {
        while (q != id[q])
            q =id[q];
        return q;
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if(pID == qID)
           return;
        id[pID] =qID;
        count --;
    }
}
