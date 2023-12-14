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
                    script{
                        def checkoutt= new lb_buildartefacto()
                        checkoutt.checkout(scmUrl)
                        
                    }
                    
                }
            }

            stage('Build Application') {
                steps {
                    script{
                        def buildd = new lb_buildartefacto()
                        buildd.build()
                    }
                    //sh 'mvn clean package'
                }
            }

            /*stage('Test') {
                steps {
                    sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent test jacoco:report' // Ejecuta las pruebas y genera el informe de cobertura con JaCoCo
                }
            }

            stage('Package') {
                steps {
                    sh 'mvn package'
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
                environment {
                    scannerHome = tool 'SonarqubeScanner'
                }
                steps {
                    withSonarQubeEnv('ServerSonarqube') {
                        sh "${scannerHome}/bin/sonar-scanner \
                           -Dsonar.projectKey=crudSpringBoot \
                           -Dsonar.projectName=crudSpringBoot \
                           -Dsonar.sources=src/main/java \
                           -Dsonar.java.binaries=target/classes \
                           -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
                    }
                }
            }*/
        }
    }
}