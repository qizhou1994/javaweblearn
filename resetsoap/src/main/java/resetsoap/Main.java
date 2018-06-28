package resetsoap;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Author zq
 * Created by CTSIG on 2018/6/28.
 * Email : qizhou1994@126.com
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Long> list = new ArrayList<Long>();
        while (true) {
            list.clear();
            int in = scanner.nextInt();
            int sum = 0;
            while (in-- != 0) {
                list.add(scanner.nextLong());
            }
            long min = 0;
            int index = 0;
            long sumNum = 0 ;
            if(list.size() > 1) {
                min = list.get(0) + list.get(1);
                for (int i = 0; i<=list.size(); i++) {
                    if (list.size() == 1) {
                        break;
                    }
//                    System.out.println("listsize = " + list.size());
                    if (i  < list.size()) {
                        if (min >= (list.get(i%list.size()) + list.get((i + 1)%list.size()))) {
                            //选出暂时最小的
                            min = (list.get(i%list.size()) + list.get((i + 1)%list.size()));
                            index = i;
//                            System.out.println("index = " + index);
                        }
                    } else if (i == list.size() ) {
                        //遍历到最后一个数字
                        sumNum += min;

                        list.set(index%list.size(),min);
                        list.remove((index + 1)%list.size());

                        i = 0;
                        if(list.size()>1){
                            index = 0;
                            min = list.get(0) + list.get(1);
                        }else {
                            break;
                        }

//                        System.out.println("min = " + list.get(index%list.size()));
                    }else {

                    }
                }
            }else {
                sumNum+= list.get(0);
            }
            System.out.print(sumNum);
        }
    }
}
