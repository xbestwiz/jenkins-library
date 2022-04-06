package com.boulderai.cicd

import com.boulderai.globalVars
import com.boulderai.utils

/**
 * 发送钉钉消息
 * @param app
 * @param branch
 * @param content
 * @token 
 * @return
 */
def sendDingtalkMessage(String name, Map params, String token) {
  def utils = new utils()
  utils.printMessage("发送「${name}」钉钉消息", "green")

  def requestBody = ["stage_name":"${name}","id":"${params.id}","build_number":"${BUILD_NUMBER}","token":"${token}"]
  def response = httpRequest contentType: 'APPLICATION_JSON',
      httpMode: 'POST',
      requestBody: groovy.json.JsonOutput.toJson(requestBody),
      url: "${globalVars.dingtalk_url}",
      consoleLogResponseBody: true
  if (response.status == 200) {
    env.UAT_REQUESTED = true
  } else {
    println response
    error '发送UAT验证消息接口返回异常'
  }
}