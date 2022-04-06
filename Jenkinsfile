def url = "http://172.16.3.5:8890/v1/openapi/dingtalk/sendmessage" // 发送钉钉消息接口地址
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
      skipDefaultCheckout() // 跳过默认checkout scm操作
  }
  stages {
    stage ("打印构建参数") {
      steps {
        script{
          echo "应用：${params.app}"
          echo "环境：${params.env}"
          echo "分支：${params.branch}"
          echo "内容：${params.content}"
          echo "已完成功能自测和集成测试：${params.tested}"
          if (params.content == '' || params.tested == false) {
            echo "Must be the first build after Pipeline deployment.  Aborting the build"
            return
          }
          echo "Crossed param validation"
        }
      }
    }
  }
}
