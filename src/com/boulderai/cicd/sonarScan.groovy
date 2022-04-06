package com.boulderai.cicd

import com.boulderai.globalVars
import com.boulderai.utils

/**
 * Sonar扫描
 * @param app_code
 * @param branch
 * @param language
 * @return
 */
def sonarScan(Map params) {
  def utils = new utils()
  utils.printMessage("代码扫描", "green")

  withSonarQubeEnv('SonarQube') {
    sh "sonar-scanner -Dsonar.projectKey=${params.app_code} -Dsonar.projectName=${params.app_code} -Dsonar.branch.name=${params.branch} -Dsonar.language=${params.language} -Dsonar.sources=. -Dsonar.sourceEncoding=UTF-8"
    sh "python3 ${globalVars.sonarqube} ${params.app_code} ${params.branch} ${env.GIT_COMMIT_EMAIL}"
  }
}