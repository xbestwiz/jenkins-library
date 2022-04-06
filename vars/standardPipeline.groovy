import com.boulderai.cicd

def call(Map params) {
  // 拉取代码
  stage("拉取代码") {
    script {
      sCheckoutScm(params)
    }
  }

  // 代码扫描
  stage("代码扫描") {
    script {
      sSnoarScan(params)
    }
  }

  // 构建代码
  stage("构建代码") {
    script {

    }
  }
}

return this