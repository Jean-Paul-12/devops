def sonarScan('ServerSonarqube') {
    sh "${scannerHome}/bin/sonar-scanner \
                           -Dsonar.projectKey=crudSpringBoot \
                           -Dsonar.projectName=crudSpringBoot \
                           -Dsonar.sources=src/main/java \
                           -Dsonar.java.binaries=target/classes \
                           -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
    echo "Escaneo SonarQube Verificado"
}