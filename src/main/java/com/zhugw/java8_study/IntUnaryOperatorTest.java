package com.zhugw.java8_study;

import java.util.function.IntUnaryOperator;

public class IntUnaryOperatorTest {

  public static void main(String[] args) {
    BuyerOrderCountStats stats = new BuyerOrderCountStats();
    stats.setOrderCount(1);

    stats.updateOrderCount(count -> count + 1);

    System.out.println(stats.getOrderCount()); // 2

    stats.updateOrderCount(count -> ++count);
    System.out.println(stats.getOrderCount());// 3

    stats.updateOrderCount(count -> count++);
    System.out.println(stats.getOrderCount());// 3

  }

  static class BuyerOrderCountStats {
    private int orderCount;

    public int getOrderCount() {
      return orderCount;
    }

    public void setOrderCount(int orderCount) {
      this.orderCount = orderCount;
    }

    public void updateOrderCount(IntUnaryOperator op) {
      orderCount = op.applyAsInt(orderCount);
    }

  }
}
