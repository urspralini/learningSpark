package com.prabhu.spark.basics

import org.apache.spark.{SparkContext, SparkConf}

/**
 * <p>
 *
 * </p>
 * @author Prabhu R Babu
 *         (11/13/15 5:55 AM)
 */
object AccumulatorExample {

  def main(args: Array[String]) {
    //initialize spark conf
    val sparkConf = new SparkConf().setMaster("local")
      .setAppName(AccumulatorExample.getClass.getSimpleName)
    val sc = new SparkContext(sparkConf)
    val filePath = getClass.getResource("/sample-readme.txt").getPath
    //create an accumulator of type int and set its initial value to 0
    var sparkLines = sc.accumulator(0);
    val lines = sc.textFile(filePath)
    //counting lines with "spark" keyword using accumulators
    lines.map(line => {
      if(line.contains("Spark") || line.contains("spark")) {
        sparkLines += 1
      }
      (line.split(" ")(0), line)
    }).count()
    println("Number of spark lines:" + sparkLines)
  }

}
