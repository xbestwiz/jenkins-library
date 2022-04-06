package com.boulderai.cicd

import com.boulderai.globalVars
import com.boulderai.utils

/**
 * 部署docker镜像
 * @param app_code
 * @param branch
 * @param private_docker_url
 * @return
 */
def deployDockerImage(Map params) {
  def utils = new utils()
  utils.printMessage("部署docker镜像", "green")

  sshPublisher(publishers: [
    sshPublisherDesc(
      configName: '172.16.6.170-slave', 
      transfers: [
        sshTransfer(
          cleanRemote: false, 
          excludes: '', 
          execCommand: "kubectl set image deployment/${params.app_code} ${params.app_code}=${globalVars.private_docker_url}/boulder-docker-local/${params.app_code}:${params.branch}.${BUILD_NUMBER} -n ${}",
          execTimeout: 120000,
          flatten: false,
          makeEmptyDirs: false,
          noDefaultExcludes: false,
          patternSeparator: '[, ]+',
          remoteDirectory: '',
          remoteDirectorySDF: false, 
          removePrefix: '', 
          sourceFiles: '/home'
        )
      ],
      usePromotionTimestamp: false,
      useWorkspaceInPromotion: false,
      verbose: false
    )
  ])
}