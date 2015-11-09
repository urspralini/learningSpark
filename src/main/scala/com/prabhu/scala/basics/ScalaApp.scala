package com.prabhu.scala.basics

/**
 * <p>
 *
 * </p>
 * @author Prabhu R Babu
 *         (11/8/15 6:49 PM)
 */
object ScalaApp {

  def main(args: Array[String]) {
    args.map(_.toUpperCase()).foreach(printf("%s ", _))
    println()
  }

}
