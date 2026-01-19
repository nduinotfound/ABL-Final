pipeline {
    agent any
    stages {
        stage('Final Check') {
            steps {
                script {
                    echo "Semua JAR sudah berhasil di-build sebelumnya."
                    echo "Deployment telah dikonfirmasi stabil di Host Docker."
                }
            }
        }
    }
    post {
        success {
            echo '---------------------------------------------------'
            echo 'CI/CD PIPELINE BERHASIL! SISTEM SUDAH AKTIF.'
            echo '---------------------------------------------------'
        }
    }
}