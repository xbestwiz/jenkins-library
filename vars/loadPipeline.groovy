import com.boulderai.cicd
import com.boulderai.utils

def call(Map params) {
  def utils = new utils()
  pipeline {
    agent any
    
    options {
      skipDefaultCheckout()
    }

    stages {
      stage("初始化步骤") {
        steps {
          script {
            switch(params.env) {
              case "dev":
                
              break
            }
          }
        }
      }
    }
  }
}