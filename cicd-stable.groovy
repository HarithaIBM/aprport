node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/aprport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/aprport.git'), string(name: 'PORT_DESCRIPTION', value: 'The Apache Portable Runtime Library provides a predictable and consistent interface to underlying platform-specific implementations.' ), string(name: 'BUILD_LINE', value: 'STABLE') ]
  }
}
