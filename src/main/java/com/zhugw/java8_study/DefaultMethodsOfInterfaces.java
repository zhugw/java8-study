package com.zhugw.java8_study;

/**
 * from http://winterbe.com/posts/2014/03/16/java-8-tutorial/
 * 
 * @author zhuguowei
 *
 */
public class DefaultMethodsOfInterfaces {
  interface Formula {
    double calculate(int a);

    default double sqrt(int a) {
      return Math.sqrt(a);
    }
  }

  public static void main(String[] args) {
    Formula formula = new Formula() {
      @Override
      public double calculate(int a) {
        return sqrt(a * 100);
      }
    };
    System.out.println(formula.calculate(100)); // 100.0
    System.out.println(formula.sqrt(16));

    Formula formula2 = (a) -> (a * 100);
    System.out.println(formula2.calculate(100));
  }

}

