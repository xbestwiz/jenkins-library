package com.boulderai.cicd

import com.boulderai.globalVars
import com.boulderai.cicd.utils

/**
 * 构建docker镜像
 * @param app
 * @param branch
 * @return
 */
def sBuildDockerImage(Map params) {
  def utils = new utils()
  utils.printMessage("构建docker镜像", "green")
  script {
    sh "docker build -t ${globalVars.private_docker_url}/boulder-docker-local/${params.app}:${params.branch}.${env.BUILD_NUMBER} ."
    sh "docker push ${globalVars.private_docker_url}/boulder-docker-local/${params.app}:${params.branch}.${env.BUILD_NUMBER}"
    if (params.branch == 'relase') {
      sh "docker tag ${globalVars.private_docker_url}/boulder-docker-local/${params.app}:${BRANCH_NAME}.${BUILD_NUMBER} ${globalVars.public_docker_url}/release/${params.app}:${BRANCH_NAME}.${BUILD_NUMBER}"
      sh "docker push ${globalVars.public_docker_url}/release/${params.app}:${BRANCH_NAME}.${BUILD_NUMBER}"
      sh "docker rmi ${globalVars.public_docker_url}/release/${params.app}:${BRANCH_NAME}.${BUILD_NUMBER}"
    }
    sh "docker rmi ${globalVars.private_docker_url}/boulder-docker-local/${params.app}:${params.branch}.${env.BUILD_NUMBER}"
  }
}