def checkout(scmUrl) {
    git url: scmUrl
    echo "Checkout Verificado"
}

def build() {
    sh 'mvn clean package'
    echo "Construcción Verificada"
}

def test() {
    sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent test jacoco:report'
    echo "Test Verificado"
}

def package() {
    sh 'mvn package'
    echo "Package Verificado"
}