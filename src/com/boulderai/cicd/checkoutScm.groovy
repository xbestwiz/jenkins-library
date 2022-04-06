package com.boulderai.cicd

/**
 * checkout scm
 * @param url
 * @param branch
 * @return
 */
def checkoutScm(Map params) {
  def utils = new com.boulderai.utils()
  utils.printMessage("拉取代码", "green")

  checkout([
    $class: 'GitSCM',
    doGenerateSubmoduleConfigurations: false,
    submoduleCfg: [],
    extensions: [[$class: 'CloneOption', depth: 1, noTags: false, reference: '', shallow: true]],
    userRemoteConfigs: [[url: "${params.url}", credentialsId: "691bd94b-1f5c-4df6-be40-e6fbc557713f"]],
    branches: [[name: "${params.branch}"]]
  ])
  /* 
    env.GIT_COMMIT_NAME = sh (script: "git --no-pager show -s --format='%an' ${GIT_COMMIT}",returnStdout: true).trim()
    echo "Git committer name: ${env.GIT_COMMIT_NAME}"
    env.GIT_COMMIT_EMAIL = sh (script: "git --no-pager show -s --format='%ae' ${GIT_COMMIT}",returnStdout: true).trim()
    echo "Git committer email: ${env.GIT_COMMIT_EMAIL}"
  */
}