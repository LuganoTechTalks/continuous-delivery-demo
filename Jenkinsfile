#!groovy​


node {

    stage 'checkout project'
    checkout scm

    stage 'compile'
    sh 'gradle compileJava'

    stage 'test'
    sh 'gradle test'

    if (env.BRANCH_NAME == "master") {
        deployDockerImage()
        restartDockerContainer()
    }

}

def deployDockerImage() {

    stage 'build'
    initDocker()
    sh 'gradle clean'
    sh 'gradle build'

    dir('docker') {
        deleteDir()
    }
    sh 'mkdir docker'

    dir('docker') {
        sh "ls ../build/libs/"
        sh "cp ../build/libs/continuous-delivery-demo-*.jar ."
        sh 'cp ../Dockerfile .'
        docker.withRegistry("https://docker-registry.newsriver.io:5000/") {
            stage 'build docker image'
            def image = docker.build("continuous-delivery-demo:latest")
            stage 'upload docker image'
            image.push(env.BUILD_NUMBER)
        }
    }
}

def restartDockerContainer() {
    stage 'deploy application'
    marathon(
            url: "http://46.4.71.105:8080/",
            forceUpdate: true,
            appid: "continuous-delivery-demo",
            docker: "docker-registry.newsriver.io:5000/continuous-delivery-demo:${env.BUILD_NUMBER}"
    )
}


def initDocker() {
    def status = sh(script: 'docker ps', returnStatus: true)
    if (status != 0) {
        sh 'service docker start'
    }
}