package illuminati;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * @author kpankaj
 */
public class MovingAvgMax {

    private Queue<Double> doubleQueue = new LinkedList<>();
    private Queue<Double> priorityQueue = new PriorityQueue<>();
    private double totalInWindow = 0;
    private int windoSizeSoFar = 0;
    private final int maxWindow;

    public MovingAvgMax(int windowSize) {
        this.maxWindow = windowSize;
    }

    void movingAverageMaxPrices(double tradePrice) {
        System.out.print(tradePrice + "=");
        double avg = getWindowAverage(tradePrice);
        insertMaxElement(tradePrice);
        System.out.print(avg);
        for (Double mx : priorityQueue) {
            System.out.print(" " + mx);
        }
        System.out.println();
    }

    private void insertMaxElement(double tradePrice) {
        if (priorityQueue.size() == maxWindow) {
            if (priorityQueue.peek() < tradePrice) {
                priorityQueue.poll();
                priorityQueue.add(tradePrice);
            }
        } else {
            priorityQueue.add(tradePrice);
        }
    }

    private double getWindowAverage(double tradePrice) {
        totalInWindow += tradePrice;
        doubleQueue.add(tradePrice);
        if (windoSizeSoFar == maxWindow) {
            double last = doubleQueue.poll();
            totalInWindow -= last;
        } else {
            ++windoSizeSoFar;
        }
        return totalInWindow / windoSizeSoFar;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Random rand = new Random();
        int n = 30; // 1000000;
        MovingAvgMax movingAvgMax = new MovingAvgMax(10);
        for (int i = 0; i < n; ++i) {
            movingAvgMax.movingAverageMaxPrices(rand.nextInt() % 100);
        }
    }

}
