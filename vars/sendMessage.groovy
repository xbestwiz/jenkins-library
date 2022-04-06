def call(String name, Map params, String token) {
  def dingtalk = new com.boulderai.cicd.sendDingtalkMessage()

  dingtalk.sendDingtalkMessage(name, params, token)
}

return