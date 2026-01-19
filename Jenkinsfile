pipeline {
    agent any

    environment {
        DOCKER_IMAGE_TAG = "latest"
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Menarik kode dari repository...'
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                script {
                    // Mendefinisikan lokasi Maven secara manual dari Global Tool Configuration
                    def mvnHome = tool 'maven-3' 
                    
                    def services = ['buku', 'anggota', 'peminjaman', 'pengembalian', 'rabbitmq']
                    for (service in services) {
                        echo "Building service: ${service}"
                        dir(service) {
                            // Menjalankan Maven menggunakan path absolut hasil dari 'tool'
                            sh "${mvnHome}/bin/mvn clean package -DskipTests"
                        }
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    def services = [
                        'buku': 'library-buku',
                        'anggota': 'library-anggota',
                        'peminjaman': 'library-peminjaman',
                        'pengembalian': 'library-pengembalian',
                        'rabbitmq': 'library-rabbitmq'
                    ]
                    
                    services.each { dirName, imageName ->
                        echo "Building Docker Image for: ${imageName}"
                        sh "docker build -t ${imageName}:${DOCKER_IMAGE_TAG} ./${dirName}"
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo 'Deploying to Docker Containers...'
                    // Menjalankan docker-compose untuk mengupdate container
                    sh 'docker-compose up -d'
                }
            }
        }
    }

    post {
        always {
            echo 'Membersihkan workspace...'
            cleanWs()
        }
        success {
            echo 'CI/CD Pipeline Completed Successfully!'
        }
        failure {
            echo 'CI/CD Pipeline Failed! Periksa Console Output untuk detail error.'
        }
    }
}