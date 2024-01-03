// File: pipelineGeneral.groovy

// Arreglo
def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"

    pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    script {
                        def checkoutt = new lb_buildartefacto()
                        checkoutt.checkout(scmUrl)
                    }
                }
            }

            stage('Build Application') {
                steps {
                    script {
                        def buildd = new lb_buildartefacto()
                        buildd.build()
                    }
                }
            }

            stage('Test') {
                steps {
                    script {
                        def testt = new lb_buildartefacto() // Ejecuta las pruebas y genera el informe de cobertura con JaCoCo
                        testt.test()
                    }
                }
            }

            stage('Package') {
                steps {
                    script {
                        def packagee = new lb_buildartefacto()
                        packagee.mypackage()
                    }
                }
                post {
                    always {
                        junit 'target/surefire-reports/TEST-*.xml' // Patrón para los archivos XML de pruebas
                    }
                    success {
                        archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false // Archivar el archivo JAR generado
                    }
                }
            }

            stage('SonarQube analysis') {
                steps {
                    withSonarQubeEnv('ServerSonarqube') {
                        script {
                            def sonarQ = new lb_analisissonarqube()
                            sonarQ.mysonarScan()
                        }
                    }
                }

            stage('Build Image'){
                steps{
                    script{
                        def imageExists = sh(script: "docker images -q REJE-crudspringboot-buildimagen", returnStdout: true).trim()
                        if(imageExists){
                            echo "La imagen REJE-crudspringboot-buildimagen ya existe. Descartando construcción..."
                        } else {
                            sh 'docker build -t REJE-crudspringboot-buildimagen -f ../tREJE-crudspringboot/Dockerfile ../REJE-crudspringboot'}
                }
            }
            }
        }
    }
}
