package com.aura.util

import com.aura.spark.core._
import com.aura.spark.mllib.{ChannelAnalysis, GenderAnalysis}

object AllAnalysis {

  def main(args: Array[String]): Unit = {

    FlowAnalysis.runAnalysis()
    SearchAnalysis.runAnalysis()
    ProvinceAnalysis.runAnalysis()
    CountryAnalysis.runAnalysis()
    ContentAnalysis.runAnalysis()

    // need to train model before run the following analysis
    GenderAnalysis.runAnalysis()
    ChannelAnalysis.runAnalysis()
  }

}