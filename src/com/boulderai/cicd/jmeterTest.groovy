package com.boulderai.cicd

import com.boulderai.globalVars
import com.boulderai.utils

/**
 * Jmeter接口测试
 * @return
 */
def jmeterTest(Map params) {
  def utils = new utils()
  utils.printMessage("Jmeter接口测试", "green")

  def jmeterDir = "/home/jmeter/${JOB_NAME}.${BUILD_NUMBER}"
  // sh "rm -rf ${jmeterDir}"
  sh "${globalVars.jmeter} -n -t ./jmeter.jmx  -l ${jmeterDir}/result.jtl -j ${jmeterDir}/result.log -e -o ${jmeterDir}/resultreport"
  publishHTML([
    allowMissing: false,
    alwaysLinkToLastBuild: false,
    keepAll: false,
    reportDir: "${jmeterDir}/resultreport",
    reportFiles: 'index.html',
    reportName: 'HTML Report',
    reportTitles: ''
  ])
  perfReport filterRegex: '', modeOfThreshold: true, showTrendGraphs: true, sourceDataFiles: "${jmeterDir}/result.jtl"
}