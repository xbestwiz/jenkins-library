package com.boulderai.cicd

import com.boulderai.globalVars
import com.boulderai.utils

/**
 * 构建nodejs
 * @param env
 * @param branch
 * @param build_params
 * @return
 */
def buildNodejs(Map params) {
  def utils = new utils()
  utils.printMessage("构建nodejs", "green")

  nodejs('nodejs') {
    sh "npm i --unsafe-perm --registry ${globalVars.npm_url}"
    switch("${params.env}") {
      case "online":
      case "prod":
        sh "npx vue-cli-service build ${params.build_params}"
        break
      default:
        sh "npm run build:${params.env}"
    }
  }
}