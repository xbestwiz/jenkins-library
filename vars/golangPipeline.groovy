def call(Map params) {
  def checkout = new com.boulderai.cicd.checkoutScm()
  def nodejs = new com.boulderai.cicd.buildNodejs()
  def docker = new com.boulderai.cicd.buildDockerImage()

  stage("拉取代码") {
    script {
      checkout.checkoutScm(params)
    }
  }

  /* stage("代码编译构建") {
    script {
      maven.mavenCompile(params)
    }
  }

  stage("Docker镜像构建上传") {
    script {
      docker.buildDockerImage(params)
    }
  } */
}

return this