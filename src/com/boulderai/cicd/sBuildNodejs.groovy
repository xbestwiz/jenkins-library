package com.boulderai.cicd

import com.boulderai.globalVars
import com.boulderai.cicd.utils

/**
 * 构建nodejs
 * @param env
 * @param branch
 * @param build_params
 * @return
 */
def sBuildNodejs(Map params) {
  def utils = new utils()
  utils.printMessage("构建nodejs", "green")
  nodejs('nodejs') {
    sh "npm i --unsafe-perm --registry ${globalVars.npm_url}"
    if (params.bracnh == 'release') {
      sh "npx vue-cli-service build ${params.build_params}"
    } else {
      sh "npm run build:${params.env}"
    }
  }
}