package com.boulderai.cicd

import com.boulderai.cicd.utils

/**
 * sonar扫描
 * @param app
 * @param branch
 * @param language
 * @return
 */
def sSonarScan(Map params) {
  def utils = new utils()
  utils.printMessage("代码扫描", "green")
  withSonarQubeEnv('SonarQube') {
    sh "sonar-scanner -Dsonar.projectKey=${params.app} -Dsonar.projectName=${params.app} -Dsonar.branch.name=${params.branch} -Dsonar.language=${params.language} -Dsonar.sources=. -Dsonar.sourceEncoding=UTF-8"
    sh "python3 /home/sonarqube_api.py ${params.app} ${env.BRANCH_NAME} ${env.GIT_COMMIT_EMAIL}"
  }
}
