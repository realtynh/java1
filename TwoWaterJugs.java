/**
 * 2WaterJugs
 */
import java.util.*;
public class TwoWaterJugs {

        public class Pair{
            int first;
            int second;
            public Pair(int first, int second) {
                this.first = first;
                this.second = second;
            }
            public int getFirst() {
                return this.first;
            }
            public int getSecond() {
                return this.second;
            }
        }

        // kiểm tra tính đúng sai trạng thái
        public boolean isSolve(Pair u) {
            int jug1 = 4;
            int jug2 = 3;
            return !(u.getFirst() < 0 || u.getSecond() < 0 || u.getFirst() > jug1 || u.getSecond() > jug2);
        }

        // là kết quả trạng thái
        public boolean isTarget(Pair u) {
            int target = 2;
            return u.getFirst() == target || u.getSecond() == target;
        }

        // tạo trạng thái
        public void create_Status(Pair dinh_xet,Map<Pair,Integer> visit, Queue<Pair> openT, Map<Pair,Integer> saveOPENT) {
            int jug1 = 4;
            int jug2 = 3;
            int target = 2;

            if (dinh_xet.getFirst() == 0 && dinh_xet.getSecond()==0)
            {
                // fill jug1.
                if (!visit.containsKey(new Pair(jug1,dinh_xet.getSecond()))) {
                    openT.add(new Pair (jug1,dinh_xet.getSecond()));
                    saveOPENT.put(new Pair (jug1, dinh_xet.getSecond()),1);
                }
                //fill jug2.
                if (!visit.containsKey(new Pair(dinh_xet.getFirst(),jug2)))
                {
                    openT.add(new Pair(dinh_xet.getFirst(),jug2));
                    saveOPENT.put(new Pair(dinh_xet.getFirst(),jug2),1);
                }
            }
            else
            {
                // fill 1.
                if (!visit.containsKey(new Pair(jug1,dinh_xet.getSecond())) && isSolve(new Pair(jug1,dinh_xet.getSecond()))) {
                    openT.add(new Pair (jug1,dinh_xet.getSecond()));
                    saveOPENT.put(new Pair (jug1, dinh_xet.getSecond()),1);
                }
                // fill 2.
                if (!visit.containsKey(new Pair(dinh_xet.getFirst(),jug2)) && isSolve(new Pair(dinh_xet.getFirst(),jug2)))
                {
                    openT.add(new Pair(dinh_xet.getFirst(),jug2));
                    saveOPENT.put(new Pair(dinh_xet.getFirst(),jug2),1);
                }
                // 1 - 2

                if (!visit.containsKey(openT.peek()))
                {
                    int d = jug2 - dinh_xet.getSecond();
                    if (dinh_xet.getFirst() >= d)
                    {
                        int c = dinh_xet.getFirst() - d;
                        if (!visit.containsKey(new Pair(c,dinh_xet.getSecond())) && isSolve(new Pair(c,dinh_xet.getSecond())))
                        {
                            if (!saveOPENT.containsKey(new Pair(c,dinh_xet.getSecond())))
                            {
                                openT.add(new Pair(c,dinh_xet.getSecond()));
                            }
                            saveOPENT.put(new Pair(c,dinh_xet.getSecond()), 1);
                        }
                    }
                    else
                    {
                        int c = dinh_xet.getFirst() + dinh_xet.getSecond();
                        if (!visit.containsKey(new Pair(0,c)) && isSolve(new Pair(0,c)))
                        {
                            if (!saveOPENT.containsKey(new Pair(0,c)))
                            {
                                openT.add(new Pair(0,c));
                            }
                            saveOPENT.put(new Pair(0, c), 1);
                        }
                    }
                }

                // 2 - 1
                if (!visit.containsKey(openT.peek()))
                {
                    int d = jug1 - dinh_xet.getFirst();

                    if (dinh_xet.getSecond() >= d){
                        int c = dinh_xet.getSecond() - d;
                        if (!visit.containsKey(new Pair(jug1,c)) && isSolve(new Pair(jug1,c)))
                        {
                            if (!saveOPENT.containsKey(new Pair(jug1,c)))
                            {
                                openT.add(new Pair(jug1,c));
                            }
                            saveOPENT.put(new Pair(jug1,c), 1);
                        }
                    }
                    else
                    {
                        int c = dinh_xet.getFirst() + dinh_xet.getSecond();
                        if (!visit.containsKey(new Pair(c,0)) && isSolve(new Pair(c,0)))
                        {
                            if (!saveOPENT.containsKey(new Pair(c,0)))
                            {
                                openT.add(new Pair(c,0));
                            }
                            saveOPENT.put(new Pair(c,0), 1);
                        }
                    }
                }

                // empty 1
                if (!visit.containsKey(new Pair(0,dinh_xet.getSecond())) && isSolve(new Pair(0,dinh_xet.getSecond())))
                {
                    if (!saveOPENT.containsKey(new Pair(0,dinh_xet.getSecond())))
                    {
                        openT.add(new Pair(0,dinh_xet.getSecond()));
                    }
                    saveOPENT.put(new Pair(0, dinh_xet.getSecond()), 1);
                }

                // empty 2
                if (!visit.containsKey(new Pair(dinh_xet.getFirst(),0)) && isSolve(new Pair(dinh_xet.getFirst(),0)))
                {
                    if (!saveOPENT.containsKey(new Pair(dinh_xet.getFirst(),0)))
                    {
                        openT.add(new Pair(dinh_xet.getFirst(),0));
                    }
                    saveOPENT.put(new Pair(dinh_xet.getFirst(),0), 1);
                }
            }

        }

        
        public void print_BFS(Pair dinh_xet,Map<Pair,Integer> visit, Queue<Pair> openT)
        {
            System.out.println("Đỉnh Xét : " + "(" + dinh_xet.getFirst() +","+dinh_xet.getSecond()+")");

            System.out.print("OPEN-T\t: ");
            Queue <Pair> tmp = new LinkedList<>();
            while (!tmp.isEmpty()) {
                    System.out.print("("+tmp.peek().getFirst()+","+tmp.peek().getSecond()+")");
                    tmp.remove();
            }
            System.out.println();
            System.out.print("CLOSE-T\t: ");
            for (Map.Entry<Pair, Integer> entry : visit.entrySet()) {
                Pair key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println("Key: (" + key.getFirst() + ", " + key.getSecond() + "), Value: " + value);
            }
            System.out.println("\n");

        }

        public void BFS(int jug1,int jug2,int target)
        {
             Queue <Pair>  openT = new LinkedList<>();
             HashMap<Pair, Integer> visit = new HashMap<>();
             HashMap<Pair, Integer> saveOPENT = new HashMap<>();

            openT.add(new Pair(0,0));

            while (!openT.isEmpty()) {
                Pair dinh_xet = openT.peek();

                visit.put(new Pair(dinh_xet.getFirst(),dinh_xet.getSecond()),1);
                create_Status(dinh_xet, visit, openT, saveOPENT);
                openT.remove();
                visit.put(new Pair(dinh_xet.getFirst(),dinh_xet.getSecond()), 1);
                print_BFS(dinh_xet, visit, openT);

                if (isTarget(new Pair(dinh_xet.getFirst(),dinh_xet.getSecond())))
                {
                    break;
                }

            }
        }
        public static void main(String[] args) {
            TwoWaterJugs twoWaterJugs = new TwoWaterJugs();
            twoWaterJugs.BFS(4, 3, 2);
        }
}