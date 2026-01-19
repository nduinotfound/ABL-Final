pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps { checkout scm }
        }
        stage('Build JAR All Services') {
            steps {
                script {
                    def mvnHome = tool 'maven-3' 
                    def services = ['buku', 'anggota', 'peminjaman', 'pengembalian', 'rabbitmq',
                                    'api-gateaway', 'product', 'pelanggan', 'order']
                    for (service in services) {
                        dir(service) {
                            sh "${mvnHome}/bin/mvn clean package -DskipTests"
                        }
                    }
                }
            }
        }
        stage('Docker Build & Deploy') {
            steps {
                script {
                    echo 'Force deploying using absolute path...'
                    // Kita gunakan path lengkap /usr/local/bin/docker-compose 
                    // supaya tidak ada lagi error 'not found'
                    sh '/usr/local/bin/docker-compose down || true'
                    sh '/usr/local/bin/docker-compose up -d --build --remove-orphans --force-recreate'
                }
            }
        }
    }
    post {
        success { echo 'CI/CD BERHASIL TOTAL!' }
    }
}