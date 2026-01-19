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
                    echo 'Cleaning up and force deploying...'
                    // down menghapus semua yang lama, up menyalakan yang baru
                    // ini cara paling ampuh untuk menghindari error CONFLICT
                    sh 'docker-compose down || true'
                    sh 'docker-compose up -d --build --remove-orphans --force-recreate'
                }
            }
        }
    }
    post {
        success { echo 'CI/CD BERHASIL TOTAL!' }
    }
}