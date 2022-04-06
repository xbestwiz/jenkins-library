package com.boulderai.cicd

import com.boulderai.globalVars
import com.boulderai.cicd.utils

/**
 * 部署docker镜像
 * @param env
 * @return
 */
def sMavenCompile(Map params) {
  def utils = new utils()
  utils.printMessage("Maven编译", "green")
  withMaven(
    globalMavenSettingsFilePath: '/usr/local/maven/conf/settings.xml', 
    jdk: 'JDK1.8',
    maven: 'maven',
    mavenSettingsFilePath: '/usr/local/maven/conf/settings.xml') {
      sh "mvn clean deploy -P ${params.env} -Dmaven.test.failure.ignore=true"
    }
}