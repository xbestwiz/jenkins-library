@Library('jenkinslibs@master')_

def randomToken // 随机校验码

pipeline {
  agent any

  environment {
    UAT_REQUESTED = false // 验证请求发送结果
    UAT_VERIFIED = false // UAT环境验证结果
    LEADER_APPROVED = false // LEADER审批结果
    OPS_APPROVED = false // 运维审批结果
    OPERATOR_DEPLOYED = false // 应用发布结果
    ACCEPTOR_ACCEPTED = false // 功能验收结果
  }

  options {
    skipDefaultCheckout()
  }

  stages {
    stage("初始化流水线") {
      steps {
        script {
          /* 基于开发语言选择相应的编译部署流水线 */
          switch("${params.language}") {
            case 'java':
              javaPipeline(params)
              break
            case 'golang':
              golangPipeline(params)
              break
            case 'vue':
              vuePipeline(params)
              break
            default:
              error "暂不支持当前开发语言${params.language}"
          }

          /* 生产发布审批 */
          if ("${params.env}" == 'prod') {
            stage('发送审批消息') {
              script {
                try {
                  // 生成4位随机验证码
                  randomToken = "${Math.abs(new Random().nextInt(8999) + 1000)}"
                  // 发送钉钉消息
                  sendMessage("UAT验证", params, randomToken)
                }
                catch (exc) {
                  error '申请发送未成功，请联系运维'
                }
              }
            }

            if (env.UAT_REQUESTED) {
              stage('UAT验证') {
                script {
                  timeout(time:4, unit:'HOURS') {
                    try {
                      def userInput = input(
                        id: 'UAT验证', message: 'UAT验证', parameters: [
                          [$class: 'StringParameterDefinition', name: 'token', defaultValue: '', description: '校验码']    
                        ]
                      )
                      if (userInput == randomToken) {
                        // 生成4位随机验证码
                        randomToken = "${Math.abs(new Random().nextInt(8999) + 1000)}"
                        // 发送钉钉消息
                        sendMessage("主管审批", params, randomToken)
                      } else {
                        error '校验码错误，任务终止'
                      }
                    }
                    catch (exc) {
                      error 'UAT验证未通过，任务终止'
                    }
                  }
                }
                post {
                  aborted {
                    echo "post condition executed: aborted ..."
                  }
                }
              }
            }
            
            if (env.UAT_VERIFIED) {
              stage('主管审批') {
                script {
                  timeout(time:4, unit:'HOURS') {
                    try {
                      def userInput = input(
                        id: '主管审批', message: '主管审批', parameters: [
                          [$class: 'StringParameterDefinition', name: 'token', defaultValue: '', description: '校验码']    
                        ]
                      )
                      if (userInput == randomToken) {
                        // 生成4位随机验证码
                        randomToken = "${Math.abs(new Random().nextInt(8999) + 1000)}"
                        // 发送钉钉消息
                        sendMessage("运维审批", params, randomToken)
                      } else {
                        error '校验码错误，任务终止'
                      }
                    }
                    catch (exc) {
                      error '审批未通过，任务终止'
                    }
                  }
                }
              }
            } 

            if (env.LEADER_APPROVED) {
              stage('运维审批') {
                script {
                  timeout(time:4, unit:'HOURS') {
                    try {
                      def userInput = input(
                        id: '运维审批', message: '运维审批', parameters: [
                          [$class: 'StringParameterDefinition', name: 'token', defaultValue: '', description: '校验码']    
                        ]
                      )
                      if (userInput == randomToken) {
                        // 生成4位随机验证码
                        randomToken = "${Math.abs(new Random().nextInt(8999) + 1000)}"
                        // 发送钉钉消息
                        sendMessage("应用发布", params, randomToken)
                      } else {
                        error '校验码错误，任务终止'
                      }
                    }
                    catch (exc) {
                      error '审批未通过，任务终止'
                    }
                  }
                }
              }
            }

            if (env.OPS_APPROVED) {
              stage('应用发布') {
                script {
                  timeout(time:4, unit:'HOURS') {
                    try {
                      def userInput = input(
                        id: '应用发布', message: '应用发布', parameters: [
                          [$class: 'StringParameterDefinition', name: 'token', defaultValue: '', description: '校验码']    
                        ]
                      )
                      if (userInput == randomToken) {
                        // 生成4位随机验证码
                        randomToken = "${Math.abs(new Random().nextInt(8999) + 1000)}"
                        // 发送钉钉消息
                        sendMessage("功能验收", params, randomToken)
                      } else {
                        error '校验码错误，任务终止'
                      }
                    }
                    catch (exc) {
                      error '发布未成功，任务终止'
                    }
                  }
                }
              }
            }

            if (env.OPERATOR_DEPLOYED) {
              stage('功能验收') {
                script {
                  timeout(time:4, unit:'HOURS') {
                    try {
                      def userInput = input(
                        id: '功能验收', message: '功能验收', parameters: [
                          [$class: 'StringParameterDefinition', name: 'token', defaultValue: '', description: '校验码']    
                        ]
                      )
                      if (userInput == randomToken) {
                        env.ACCEPTOR_ACCEPTED = true
                      } else {
                        error '校验码错误，任务终止'
                      }
                    }
                    catch (exc) {
                      error '验收未通过，任务终止'
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}