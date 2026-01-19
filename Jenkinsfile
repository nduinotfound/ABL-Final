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
                    // Mengambil lokasi Maven dari Global Tool Configuration
                    def mvnHome = tool 'maven-3' 
                    
                    // Daftar folder service - PASTIKAN NAMA FOLDER DI REPO SAMA PERSIS
                    def services = ['buku', 'anggota', 'peminjaman', 'pengembalian', 'rabbitmq']
                    
                    for (service in services) {
                        echo "-------------------------------------------"
                        echo "Building JAR for Service: ${service}"
                        echo "-------------------------------------------"
                        dir(service) {
                            // Menjalankan Maven menggunakan path absolut
                            sh "${mvnHome}/bin/mvn clean package -DskipTests"
                        }
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    // Mapping folder ke nama image docker
                    def services = [
                        'buku': 'library-buku',
                        'anggota': 'library-anggota',
                        'peminjaman': 'library-peminjaman',
                        'pengembalian': 'library-pengembalian',
                        'rabbitmq': 'library-rabbitmq'
                    ]
                    
                    services.each { dirName, imageName ->
                        echo "Building Docker Image: ${imageName}"
                        // Build image berdasarkan folder masing-masing
                        sh "docker build -t ${imageName}:${DOCKER_IMAGE_TAG} ./${dirName}"
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo 'Deploying to Docker Containers...'
                    // Mengupdate container menggunakan docker-compose yang ada di root folder
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
            echo '---------------------------------------------------'
            echo 'CI/CD PIPELINE BERHASIL! SEMUA SERVICE TERUPDATE.'
            echo '---------------------------------------------------'
        }
        failure {
            echo 'CI/CD Pipeline Gagal. Periksa detail log di atas.'
        }
    }
}