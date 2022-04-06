def call(Map params) {
  def checkout = new com.boulderai.cicd.checkoutScm()
  def sonar = new com.boulderai.cicd.sonarScan()

  stage("拉取代码") {
    script {
      checkout.checkoutScm(params)
    }
  }

  if ("${params.env}" == 'dev') {
    stage("代码扫描") {
      script {
        // sonar.sonarScan(params)
        println "代码扫描"
      }
    }
  } else {
    stage("构建代码") {
      script {
        println "构建代码"
      }
    }
  }
}

return this