def call(Map params) {
  def checkout = new com.boulderai.cicd.checkoutScm()
  def nodejs = new com.boulderai.cicd.buildNodejs()
  def maven = new com.boulderai.cicd.mavenCompile()
  def docker = new com.boulderai.cicd.buildDockerImage()
  def sonar = new com.boulderai.cicd.sonarScan()
  def jemter = new com.boulderai.cicd.jemterTest()

  stage("拉取代码") {
    script {
      checkout.checkoutScm(params)
    }
  }

  stage("代码编译构建") {
    script {
      maven.mavenCompile(params)
    }
  }

  stage("Docker镜像构建上传") {
    script {
      docker.buildDockerImage(params)
    }
  }

  stage("Sonar扫描") {
    script {
      sonar.sonarScan()
    }
  }

  stage("Jemter接口测试") {
    script {
      jemter.jemterTest()
    }
  }
}

return this