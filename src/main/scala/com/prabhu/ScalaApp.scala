package com.prabhu

import org.apache.spark.{SparkContext, SparkConf}

/**
 * <p>
 *
 * </p>
 * @author Prabhu R Babu
 *         (11/8/15 12:50 PM)
 */
object ScalaApp {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local")
      .setAppName(ScalaApp.getClass.getSimpleName)
    val sc = new SparkContext(conf)
    val lines = sc.textFile("/usr/local/spark/README.md")
    val sparkLines = lines.filter(line => line.contains("Spark"))
    sparkLines.persist()
    println("SparkLines:" + sparkLines.count())
    sparkLines.take(3).foreach(println(_))
    //exit spark
    sc.stop();
  }
}
