package com.boulderai.cicd

import com.boulderai.globalVars
import com.boulderai.utils

/**
 * Docker镜像构建上传
 * @param app_code
 * @param branch
 * @param env
 * @return
 */
def buildDockerImage(Map params) {
  def utils = new utils()
  utils.printMessage("Docker镜像构建上传", "green")

  script {
    sh "docker build -t ${globalVars.private_docker_url}/boulder-docker-local/${params.app_code}:${params.branch}.${BUILD_NUMBER} ."
    sh "docker push ${globalVars.private_docker_url}/boulder-docker-local/${params.app_code}:${params.branch}.${BUILD_NUMBER}"
    if ("${params.env}" == 'prod') {
      sh "docker tag ${globalVars.private_docker_url}/boulder-docker-local/${params.app_code}:${params.branch}.${BUILD_NUMBER} ${globalVars.public_docker_url}/release/${params.app_code}:${params.branch}.${BUILD_NUMBER}"
      sh "docker push ${globalVars.public_docker_url}/release/${params.app_code}:${params.branch}.${BUILD_NUMBER}"
      sh "docker rmi ${globalVars.public_docker_url}/release/${params.app_code}:${params.branch}.${BUILD_NUMBER}"
    }
    sh "docker rmi ${globalVars.private_docker_url}/boulder-docker-local/${params.app_code}:${params.branch}.${BUILD_NUMBER}"
  }
}