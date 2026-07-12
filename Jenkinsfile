pipeline {

    agent any

    environment {

        DOCKER="/Users/anuragjain/.rd/bin/docker"

        PATH="/opt/homebrew/bin:/usr/local/bin:$PATH"

        IMAGE_NAME="anuragsinghai2010/ent-gatling-test"

        IMAGE_TAG="4.0.0"

        IMAGE_TAG1="latest"
    }

    stages {

        stage('Checkout') {

            steps {

                checkout scm
            }
        }

        stage('Gradle Build') {

            steps {

                sh '''
                    chmod +x gradlew

                    ./gradlew clean build
                '''
            }
        }

        stage('Run Gatling Tests') {

            steps {

                sh '''
                    ./gradlew gatlingRun --simulation simulations.SecondSimulation
                '''
            }
        }

        stage('Docker Build') {

            steps {

                sh '''
                    $DOCKER build \
                    -t $IMAGE_NAME:$IMAGE_TAG -t $IMAGE_NAME:$IMAGE_TAG1 .
                '''
            }
        }

        stage('Docker Push') {

            steps {

                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {

                    sh '''
                        mkdir -p ${WORKSPACE}/.docker
                        echo '{}' > ${WORKSPACE}/.docker/config.json

                        export DOCKER_CONFIG=${WORKSPACE}/.docker

                        echo "$DOCKER_PASS" | \
                        $DOCKER login \
                        -u "$DOCKER_USER" \
                        --password-stdin

                        $DOCKER push $IMAGE_NAME:$IMAGE_TAG

                        $DOCKER push $IMAGE_NAME:$IMAGE_TAG1
                    '''
                }
            }
        }

        stage('Deploy to Kubernetes') {

            steps {

                withCredentials([
                    file(
                        credentialsId: 'kubeconfig-file',
                        variable: 'KUBECONFIG'
                    )
                ]) {

                    sh '''

                        export KUBECONFIG=$KUBECONFIG

                        kubectl create -f cd/k8s/gatling_job.yaml
                    '''
                }
            }
        }

//         stage('Wait for Rollout') {
//
//             steps {
//
//                 withCredentials([
//                     file(
//                         credentialsId: 'kubeconfig-file',
//                         variable: 'KUBECONFIG'
//                     )
//                 ]) {
//
//                     sh '''
//
//                         export KUBECONFIG=$KUBECONFIG
//
//                         kubectl rollout status job/gatling-test
//                     '''
//                 }
//             }
//         }

        stage('Publish Gatling Report') {

            steps {

                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'build/reports/gatling',
                    reportFiles: 'index.html',
                    reportName: 'Gatling Report'
                ])
            }
        }

    }

    post {

        always {

            archiveArtifacts artifacts: 'build/reports/**'

            junit allowEmptyResults: true,
                  testResults: 'build/test-results/**/*.xml'
        }

        success {

            echo "Pipeline completed successfully."
        }

        failure {

            echo "Pipeline failed."
        }
    }
}