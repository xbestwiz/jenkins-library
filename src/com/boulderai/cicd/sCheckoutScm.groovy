package com.boulderai.cicd

import com.boulderai.cicd.utils

/**
 * checkout scm
 * @param url
 * @param branch
 * @return
 */
def sCheckoutScm(Map params) {
  def utils = new utils()
  utils.printMessage("拉取代码", "green")
  checkout([
    $class: 'GitSCM',
    doGenerateSubmoduleConfigurations: false,
    submoduleCfg: [],
    extensions: [[$class: 'CloneOption', depth: 1, noTags: false, reference: '', shallow: true]],
    userRemoteConfigs: [[url: "${params.url}", credentialsId: "cicd-pass"]],
    branches: [[name: "${params.branch}"]]
  ])
  script {
    env.GIT_COMMIT_NAME = sh (script: "git --no-pager show -s --format='%an' ${env.GIT_COMMIT}",returnStdout: true).trim()
    echo "Git committer name: ${env.GIT_COMMIT_NAME}"
    env.GIT_COMMIT_EMAIL = sh (script: "git --no-pager show -s --format='%ae' ${env.GIT_COMMIT}",returnStdout: true).trim()
    echo "Git committer email: ${env.GIT_COMMIT_EMAIL}"
  }
}