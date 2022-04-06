package com.boulderai.cicd

import com.boulderai.globalVars
import com.boulderai.cicd.utils

/**
 * 部署docker镜像
 * @param app
 * @param docker_url
 * @param branch
 * @return
 */
def sDeployImage(Map params) {
  def utils = new utils()
  utils.printMessage("部署docker镜像", "green")
  sshPublisher(publishers: [
    sshPublisherDesc(
      configName: '172.16.6.170-slave', 
      transfers: [
        sshTransfer(
          cleanRemote: false, 
          excludes: '', 
          execCommand: "kubectl set image deployment/${params.app} ${params.app}=${globalVars.docker_url}/boulder-docker-local/${params.app}:${params.branch}.${env.BUILD_NUMBER} -n cutler",
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
