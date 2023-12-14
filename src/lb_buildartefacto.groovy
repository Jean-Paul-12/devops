def checkout(scmUrl) {
    git url: scmUrl
    echo "Checkout Verificado"
}

def build() {
    sh 'mvn clean package'
    echo "Construcción verificada "
}