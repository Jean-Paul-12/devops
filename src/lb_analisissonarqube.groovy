def mysonarScan() {
    environment {
                    scannerHome = tool 'SonarqubeScanner'
                    sh "${scannerHome}/bin/sonar-scanner \
                           -Dsonar.projectKey=crudSpringBoot \
                           -Dsonar.projectName=crudSpringBoot \
                           -Dsonar.sources=. \
                           -Dsonar.java.binaries=. \
                           -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
    echo "Escaneo SonarQube Verificado"
                }
    
}
