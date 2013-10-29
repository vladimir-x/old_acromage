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
import processmodel.OutWorld;
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
    Integer iterationCount; //Количество итераций
    Integer maxTacticPoints;  //максимальная тактическая оценка итераций
    Integer maxStrategicPoints;  //максимальная стратегическая оценка итераций
    Map<Integer, String> statisticTacticMap;  // результат итераций с максимальной тактической оценкой
    Map<Integer, String> statisticStrategicMap;  // результат итераций с максимальной стратегической оценкой
    Map<Integer, Integer> statisticCountTacticMap;
    Map<Integer, Integer> statisticCountStrategicMap;

    public KimProcess() {
        statisticTacticMap = new HashMap<Integer, String>();
        statisticStrategicMap = new HashMap<Integer, String>();
        statisticCountTacticMap = new HashMap<Integer, Integer>();
        statisticCountStrategicMap = new HashMap<Integer, Integer>();
        maxTacticPoints = 0;
        maxStrategicPoints = 0;
        OutWorld.loadByFile();
    }

    private void dayModele(int day) {
        Plant.getPlant().setDay(day);

        List<SimpleMethod> simpleMethods = kimMethod.getDailySimpleMethods();

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

        int currPoints;
        String currStat = Plant.getStatistic();
        Integer hash = currStat.hashCode();

        currPoints = kimMethod.getResultTacticPoint();
        if (maxTacticPoints <= currPoints) {

            if (maxTacticPoints < currPoints) {
                statisticTacticMap.clear();
                maxTacticPoints = currPoints;
            }

            if (!statisticTacticMap.containsKey(hash)) {
                statisticTacticMap.put(hash, currStat);
            }

            Integer count = statisticCountTacticMap.get(hash);
            statisticCountTacticMap.put(hash, count != null ? count + 1 : 1);
        }

        currPoints = kimMethod.getResultStrategicPoint();
        if (maxStrategicPoints <= currPoints) {

            if (maxStrategicPoints < currPoints) {
                statisticStrategicMap.clear();
                maxStrategicPoints = currPoints;
            }

            if (!statisticStrategicMap.containsKey(hash)) {
                statisticStrategicMap.put(hash, currStat);
            }

            Integer count = statisticCountStrategicMap.get(hash);
            statisticCountStrategicMap.put(hash, count != null ? count + 1 : 1);
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

        int count;
        DecimalFormat df = new DecimalFormat("###.##");

        List<StatItem> statTacticList = new ArrayList<StatItem>();
        for (Integer hash : statisticTacticMap.keySet()) {
            StatItem si = new StatItem();
            si.setDescription(statisticTacticMap.get(hash));
            si.setCount(statisticCountTacticMap.get(hash));
            si.setPoint(maxTacticPoints);
            statTacticList.add(si);
        }

        Collections.sort(statTacticList, new Comparator<StatItem>() {
            @Override
            public int compare(StatItem o1, StatItem o2) {
                return o1.getCount().compareTo(o2.getCount());
            }
        });

        count = 3;

        System.out.println("------------------------------------------");
        System.out.println("Tactick plans: points=" + maxTacticPoints + " count=" + statTacticList.size() + ";\n");

        for (int i = statTacticList.size() - 1; count > 0 && i >= 0; i--) {
            count--;
            Integer repeats = statTacticList.get(i).getCount();
            System.out.println("--------------");
            System.out.println(statTacticList.get(i).getDescription());
            System.out.println(df.format(repeats * 100d / iterationCount) + "% (" + repeats + "/" + iterationCount + ")");
        }

        List<StatItem> statStrategicList = new ArrayList<StatItem>();
        for (Integer hash : statisticStrategicMap.keySet()) {
            StatItem si = new StatItem();
            si.setDescription(statisticStrategicMap.get(hash));
            si.setCount(statisticCountStrategicMap.get(hash));
            si.setPoint(maxStrategicPoints);
            statStrategicList.add(si);
        }

        Collections.sort(statStrategicList, new Comparator<StatItem>() {
            @Override
            public int compare(StatItem o1, StatItem o2) {
                return o1.getCount().compareTo(o2.getCount());
            }
        });

        count = 3;
        System.out.println("------------------------------------------");
        System.out.println("Strategic plans: points=" + maxStrategicPoints + " count=" + statStrategicList.size() + ";\n");
        for (int i = statStrategicList.size() - 1; count > 0 && i >= 0; i--) {
            count--;
            Integer repeats = statStrategicList.get(i).getCount();
            System.out.println("--------------");
            System.out.println(statStrategicList.get(i).getDescription());
            System.out.println(df.format(repeats * 100d / iterationCount) + "% (" + repeats + "/" + iterationCount + ")");
        }

        System.out.println("------------------------------------------");
        System.out.println("END.");
    }
}
