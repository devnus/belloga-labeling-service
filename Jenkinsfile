pipeline {
    agent any
    options {
        timeout(time: 1, unit: 'HOURS') // set timeout 1 hour
    }
    environment {
        TIME_ZONE = 'Asia/Seoul'
        PROFILE = 'local'

        REPOSITORY_CREDENTIAL_ID = 'gitlab-jenkins-key'
        REPOSITORY_URL = 'git@git.swmgit.org:swm-13-main/13_swm56/belloga-labeling-service.git'
        TARGET_BRANCH = 'master'

        CONTAINER_NAME = 'belloga-labeling-service'

        AWS_CREDENTIAL_NAME = 'belloga-aws'
        ECR_PATH = '023778162658.dkr.ecr.ap-northeast-2.amazonaws.com'
        IMAGE_NAME = '023778162658.dkr.ecr.ap-northeast-2.amazonaws.com/belloga-labeling-service'
        REGION = 'ap-northeast-2'
    }
    stages{
        stage('init') {
            steps {
                echo 'init stage'
                deleteDir()
            }
            post {
                success {
                    echo 'success init in pipeline'
                }
                failure {
                    error 'fail init in pipeline'
                }
            }
        }
        stage('clone project') {
            steps {
                git url: "$REPOSITORY_URL",
                    branch: "$TARGET_BRANCH",
                    credentialsId: "$REPOSITORY_CREDENTIAL_ID"
                sh "ls -al"
            }
            post {
                success {
                    echo 'success clone project'
                }
                failure {
                    error 'fail clone project' // exit pipeline
                }
            }
        }
        stage('build project') {
            steps {
                sh '''
        		 ./gradlew bootJar
        		 '''
            }
            post {
                success {
                    echo 'success build project'
                }
                failure {
                    error 'fail build project' // exit pipeline
                }
            }
        }
        stage('generate api docs by spring rest docs and send swagger ui') {
            steps {
                sh '''
        		 ./gradlew openapi3
        		 '''
        		 // swagger UI로 보내는 로직 필요
            }
            post {
                success {
                    echo 'success generate api docs'
                }
                failure {
                    error 'fail generate api docs' // exit pipeline
                }
            }
        }
        stage('dockerizing project') {
            steps {
                sh '''
        		 docker build -t $IMAGE_NAME .
        		 '''
            }
            post {
                success {
                    echo 'success dockerizing project'
                }
                failure {
                    error 'fail dockerizing project' // exit pipeline
                }
            }
        }
        stage('upload aws ECR') {
            steps {
                script{
                    // cleanup current user docker credentials
                    sh 'rm -f ~/.dockercfg ~/.docker/config.json || true'

                    docker.withRegistry("https://${ECR_PATH}", "ecr:${REGION}:${AWS_CREDENTIAL_NAME}") {
                      docker.image("${IMAGE_NAME}:latest").push()
                    }
                }
            }
            post {
                success {
                    echo 'success upload image'
                }
                failure {
                    error 'fail upload image' // exit pipeline
                }
            }
        }
        stage('kubectl apply') { // 지금은 apply로 하는데 나중엔 롤링업데이트 하자
            steps {
                sshagent (credentials: ['bestion-ssh']) { // use SSH Agent
                sh """
                    ssh -o StrictHostKeyChecking=no ubuntu@43.200.60.243 '
                    kubectl apply -f ./belloga-kube/belloga-labeling-service.yaml
                    '
                """
                }
            }
            post {
                success {
                    echo 'success kubectl apply'
                }
                failure {
                    error 'fail kubectl apply' // exit pipeline
                }
            }
        }
    }
}