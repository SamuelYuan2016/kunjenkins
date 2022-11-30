import java.time.LocalDateTime

pipeline {
    agent any
    options { timestamps()
                retry(2)
        
    }
    
    stages {
        stage('First') {
            environment{
                wifipassword=credentials('wifisecret')
                
            }  
            steps {
                script {
            //if (1==1) {
            if (LocalDateTime.now().hour > 20) {
            sh "sleep 1s"
            sh "ls -la"
            sh 'ssh ubt28   "echo $wifipassword  | sudo -S shutdown -t 300 "'
            
            }
            else {
                echo 'Production Server can only shutdown at night after 20:00'
            }                        
        }
            }
        }

    
        stage('SecondStage') {
            environment{
                wifipassword=credentials('wifisecret')
                simplepw=credentials('simplesecret')
               
            }
            options{
                timeout(time: 2, unit:"MINUTES")
            }
            
            steps{

                  parallel{
                        stage('shut1') {
                            steps { sh 'ssh ubt26   "echo $simplepw | sudo -S shutdown -t 300 "'
                            }
                        }
                        state('shut2'){
                            steps {sh 'ssh rhl02  "echo $wifipassword | sudo -S shutdown -t 300 "'}

                        }
            
            }
            }
            }
        stage('s3') {
            steps {
            
            sh 'pwd'
            
            }
        }
        
        }
        post {
        always {
            echo 'Always'
        }
        success {
            echo 'Only on SUCCESS'
        }
        failure {
            echo 'Only on Failure'
        }
        unstable {
            echo 'Only if run is unstable'
        }
        changed {
            echo 'Only if status changed from Success to Failure or vice versa w.r.t. last run.'
        }
    }
}
