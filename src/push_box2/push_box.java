package push_box2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Higanbana on 2016/9/4.
 */

class node{
    private int rx,ry;
    private int bx,by;
    private int step;

    public int getRx() {
        return rx;
    }
    public int getRy() {
        return ry;
    }
    public int getBx() {
        return bx;
    }
    public int getBy() {
        return by;
    }
    public int getStep() {
        return step;
    }
    public void setBx(int bx) {
        this.bx = bx;
    }
    public void setBy(int by) {
        this.by = by;
    }
    public void setRx(int rx) {
        this.rx = rx;
    }
    public void setRy(int ry) {
        this.ry = ry;
    }
    public void setStep(int step) {
        this.step = step;
    }
}
public class push_box {
    static private int n,m;
    static private int [][] Map =new int[110][110];
    static private int [][] dir = {{0,1},{0,-1},{1,0},{-1,0}};

    public static boolean bfsr(int rx,int ry,int ex,int ey,int bx,int by){//瀵逛汉杩涜鎼滅储
        boolean [][] vis = new boolean[10][10];
        node s = new node();
        Queue<node> q = new LinkedList<node>();
        s.setRx(rx);
        s.setRy(ry);
        s.setStep(0);
        vis[s.getRx()][s.getRy()] = true;
        q.offer(s);
        while (!q.isEmpty())
        {
            s=q.peek();
            q.poll();

            if (s.getRx()==ex && s.getRy()==ey)
                return true;
            for (int i=0; i<4; i++)
            {
                int X=s.getRx()+dir[i][0];
                int Y=s.getRy()+dir[i][1];

                if (X>=0 && X<n && Y>=0 && Y<m && Map[X][Y]!=1 && !(X==bx && Y==by) && !vis[X][Y])
                {
                    node tempSs = new node();

                    tempSs.setRx(X);
                    tempSs.setRy(Y);
                    tempSs.setStep(s.getStep()+1);
                    vis[tempSs.getRx()][tempSs.getRy()] = true;
                    q.offer(tempSs);
                }
            }
        }
        return false;
    }

    public static void bfsx(int sx,int sy,int ex,int ey,int rx,int ry)//瀵圭瀛愯繘琛屾悳绱�
    {
        node s = new node();
        Queue<node> q = new LinkedList<node>();
        s.setBx(sx);
        s.setBy(sy);
        s.setStep(0);
        s.setRx(rx);
        s.setRy(ry);
        boolean[][][] vis= new boolean[10][10][10];
        q.offer(s);
        while (!q.isEmpty())
        {
            s=q.peek();
            q.poll();

            if (s.getBx()==ex && s.getBy()==ey)
            {
                System.out.println(s.getStep());
                return ;
            }
            for (int i=0; i<4; i++)
            {
                int X=s.getBx()+dir[i][0];//绠卞瓙灏嗚鍘荤殑鍦版柟
                int Y=s.getBy()+dir[i][1];
                int Xx=s.getBx()-dir[i][0];//浜哄簲璇ュ埌杈剧殑鍦版柟
                int Yy=s.getBy()-dir[i][1];

                boolean result_bfsr=bfsr(s.getRx(),s.getRy(),Xx,Yy,s.getBx(),s.getBy());
                if (X>=0 && X<n && Y>=0 && Y<m && Map[X][Y]!=1 && !vis[X][Y][i] && result_bfsr==true)//鍒ゆ柇鏄惁鍙互鍘�
                {

                    node tempSs = new node();
                    tempSs.setBx(X);
                    tempSs.setBy(Y);
                    tempSs.setRx(s.getBx());
                    tempSs.setRy(s.getBy());
                    tempSs.setStep(s.getStep()+1);
                    vis[tempSs.getBx()][tempSs.getBy()][i] = true;
                    q.offer(tempSs);
                }
            }
        }
        System.out.println(-1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();  //娴嬭瘯鏁版嵁鐨勬暟閲�

        while (t != 0) {
            t--;
            int rx = 0, ry = 0, bx = 0, by = 0, ex = 0, ey = 0;
            n = scanner.nextInt();  //鎴块棿鐨勫
            m = scanner.nextInt();  //鎴块棿鐨勯暱
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    Map[i][j] = scanner.nextInt();  //鍦板浘
                    if (Map[i][j] == 4) {
                        rx = i;
                        ry = j;
                        Map[i][j] = 0;
                    }
                    if (Map[i][j] == 2) {
                        bx = i;
                        by = j;
                        Map[i][j] = 0;
                    }
                    if (Map[i][j] == 3) {
                        ex = i;
                        ey = j;
                    }
                }
            }
            bfsx(bx, by, ex, ey, rx, ry);
        }
    }
}
