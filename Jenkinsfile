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

                  {sh 'ssh rhl02  "echo $wifipassword | sudo -S shutdown -t 300 "'
                            }

                        }    
                   }
        stage('s3') {
            steps {
            
            sh 'pwd'
            
            }
        }
    }
        
  
}
