/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.kimprocess;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import processmodel.Plant;
import processmodel.data.StatItem;
import processmodel.kimmethod.KimMethod;
import processmodel.simplest.SimpleMethod;

/**
 *
 * @author Dude
 */
public class KimProcess {

    public static boolean printDetail = false;
    KimMethod kimMethod;
    Integer maxPoints;  //максимальная оценка итераций
    Integer iterationCount; //Количество итераций
    Map<String, Integer> statisticMap;  // результат итераций с максимальнйо оценкой

    public KimProcess() {
        statisticMap = new HashMap<String, Integer>();
        maxPoints = 0;
    }

    private void dayModele(int day) {
        List<SimpleMethod> simpleMethods = kimMethod.getDailySimpleMethods(day);

        SimpleMethod selectSimpMethod = selectMethod(simpleMethods);


        if (printDetail) {
            System.out.println("Day:" + day);
        }
        if (selectSimpMethod != null) {
            selectSimpMethod.execute();
        }
    }

    private void iteration() {

        int start = kimMethod.getStartDate();
        int end = kimMethod.getEndDate();

        for (int i = start; i <= end; ++i) {
            dayModele(i);
        }
    }

    public void modele(KimMethod kimMethod, int iterationCount) {
        this.kimMethod = kimMethod;
        this.iterationCount = iterationCount;
        for (int i = 0; i < iterationCount; ++i) {
            if (printDetail) {
                System.out.println("---------");
                System.out.println("Iteration: " + i);
            }
            init();
            iteration();
            saveStatistic();
        }
    }

    void init() {
        Plant.getPlant().init();
        kimMethod.init();
    }

    void saveStatistic() {

        int currPoints = kimMethod.getResultPoint();
        if (maxPoints <= currPoints) {

            if (maxPoints < currPoints) {
                statisticMap.clear();
                maxPoints = currPoints;
            }
            String currStat = Plant.getStatistic();

            Integer count = statisticMap.get(currStat);
            statisticMap.put(currStat, count != null ? count + 1 : 1);
        }
    }
    private static long starter = 0;//System.currentTimeMillis();
    private static Random random = new Random(starter);
    // рандомизированный розыгрыш срели все допустимых решений

    public static SimpleMethod selectMethod(List<SimpleMethod> simpleMethods) {
        TreeMap<Integer, SimpleMethod> weightMap = new TreeMap<Integer, SimpleMethod>();
        int weightSum = 0;
        for (SimpleMethod sMethod : simpleMethods) {
            if (sMethod.isAllow()) {
                weightSum += sMethod.getWeight();
                weightMap.put(weightSum, sMethod);
            }
        }
        if (weightSum > 0) {
            int rand = random.nextInt(weightSum);
            return weightMap.higherEntry(rand).getValue();
        } else {
            return null;
        }
    }

    public void printKimStatistic() {


        int count = 10;
        
        List<StatItem> statList = new ArrayList<StatItem>();
        for (String key : statisticMap.keySet()) {
            StatItem si = new StatItem();
            si.setDescription(key);
            si.setPoint(statisticMap.get(key));
            statList.add(si);
        }

        Collections.sort(statList, new Comparator<StatItem>() {

            @Override
            public int compare(StatItem o1, StatItem o2) {
                return o1.getPoint().compareTo(o2.getPoint());
            }
        });

        DecimalFormat df = new DecimalFormat("###.##");
        
        for (int i = statList.size()-1; count > 0 && i>=0; i--) {
            count--;
            Integer repeats = statList.get(i).getPoint();
            System.out.println(statList.get(i).getDescription());
            System.out.println(df.format(repeats * 100d / iterationCount) + "% (" + repeats + "/" + iterationCount + ")");
            System.out.println("--------------");
        }
    }
}