package com.boulderai.cicd

import com.boulderai.globalVars
import com.boulderai.cicd.utils

/**
 * 构建docker镜像（公有云）
 * @param app
 * @param branch
 * @return
 */
def sBuildPublicImage(Map params) {
  def utils = new utils()
  utils.printMessage("构建docker镜像（私有云+公有云）", "green")
  sh "docker build -t ${globalVars.private_docker_url}/boulder-docker-local/${params.app}:${BRANCH_NAME}.${BUILD_NUMBER} ."
  sh "docker push ${globalVars.private_docker_url}/boulder-docker-local/${params.app}:${BRANCH_NAME}.${BUILD_NUMBER}"
  sh "docker tag ${globalVars.private_docker_url}/boulder-docker-local/${params.app}:${BRANCH_NAME}.${BUILD_NUMBER} ${globalVars.public_docker_url}/release/${params.app}:${BRANCH_NAME}.${BUILD_NUMBER}"
  sh "docker push ${globalVars.public_docker_url}/release/${params.app}:${BRANCH_NAME}.${BUILD_NUMBER}"
  sh "docker rmi ${globalVars.public_docker_url}/release/${params.app}:${BRANCH_NAME}.${BUILD_NUMBER}"
  sh "docker rmi ${globalVars.private_docker_url}/boulder-docker-local/${params.app}:${BRANCH_NAME}.${BUILD_NUMBER}"
}