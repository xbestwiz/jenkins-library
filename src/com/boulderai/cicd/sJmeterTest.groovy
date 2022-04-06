package com.boulderai.cicd

import com.boulderai.globalVars
import com.boulderai.cicd.utils

/**
 * Jmeter接口测试
 * @return
 */
def sJmeterTest(Map params) {
  def utils = new utils()
  utils.printMessage("Jmeter接口测试", "green")
  sh "rm -rf /home/jmeter/${env.JOB_NAME}"
  sh "${globalVars.jmeter} -n -t ./jmeter.jmx  -l /home/jmeter/${env.JOB_NAME}/result.jtl -j /home/jmeter/${env.JOB_NAME}/result.log -e -o /home/jmeter/${env.JOB_NAME}/resultreport"
  publishHTML([
    allowMissing: false,
    alwaysLinkToLastBuild: false,
    keepAll: false,
    reportDir: "/home/jmeter/${env.JOB_NAME}/resultreport",
    reportFiles: 'index.html',
    reportName: 'HTML Report',
    reportTitles: ''
  ])
  perfReport filterRegex: '', modeOfThreshold: true, showTrendGraphs: true, sourceDataFiles: "/home/jmeter/${env.JOB_NAME}/result.jtl"
}